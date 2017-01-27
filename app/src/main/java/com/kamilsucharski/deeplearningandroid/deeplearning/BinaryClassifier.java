package com.kamilsucharski.deeplearningandroid.deeplearning;

import android.util.Log;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.preprocessor.DataNormalization;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.List;

public class BinaryClassifier {
    private final String TAG = "LOG/BinaryClassifier";
    private DataNormalization normalizer;
    private MultiLayerNetwork multiLayerNetwork;

    private int featuresCount;
    private int labelsCount;

    public BinaryClassifier(int featuresCount, int labelsCount) {
        this.featuresCount = featuresCount;
        this.labelsCount = labelsCount;
    }

    public void train(List<LearnableModel> trainSet){
        Log.d(TAG, "Building a model...");
        DataSet trainingData = createTrainingData(trainSet);
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
            .seed(6)
            .iterations(500)
            .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
            .activation(Activation.TANH)
            .weightInit(WeightInit.XAVIER)
            .learningRate(0.01)
            .regularization(true)
            .l2(1e-4)
            .list()
            .layer(0, new DenseLayer.Builder().nIn(featuresCount).nOut(3).build())
            .layer(1, new DenseLayer.Builder().nIn(3).nOut(3).build())
            .layer(2, new OutputLayer.Builder(LossFunctions.LossFunction.XENT).activation(Activation.SIGMOID).nIn(3).nOut(labelsCount).build())
            .backprop(true).pretrain(false)
            .build();

        multiLayerNetwork = new MultiLayerNetwork(conf);
        multiLayerNetwork.init();
        multiLayerNetwork.fit(trainingData);
        Log.d(TAG, "Model built!");
    }

    public List<LearnableModel> predict(List<LearnableModel> predictSet){
        if (multiLayerNetwork == null) {
            Log.e(TAG, "Train a model first!");
            return predictSet;
        }
        INDArray predictionData = createPredictionData(predictSet);
        INDArray output = multiLayerNetwork.output(predictionData);
        return returnPredictions(predictSet, output);
    }

    private DataSet createTrainingData(List<LearnableModel> list) {
        int size = list.size();
        double[][] inputData = new double[size][featuresCount];
        double[][] outputData = new double[size][labelsCount];
        for (int i = 0;i<list.size();i++) {
            int[] features = list.get(i).getFeatures();
            if (features.length!= featuresCount) {
                Log.e(TAG, "Mismatched number of features! Got: "+features.length+", expected: "+ featuresCount);
            }
            for (int j = 0; j < features.length ; j++) {
                inputData[i][j] = features[j];
            }

            int[] labels = list.get(i).getLabels();
            if (labels.length!= labelsCount) {
                Log.e(TAG, "Mismatched number of labels! Got: "+labels.length+", expected: "+ labelsCount);
            }
            for (int j = 0; j < labels.length ; j++) {
                outputData[i][j] = labels[j];
            }
        }

        INDArray trainingFeatures = new NDArray(inputData);
        INDArray trainingLabels   = new NDArray(outputData);
        DataSet trainingData = new DataSet(trainingFeatures, trainingLabels);
        normalizer = new NormalizerStandardize();
        normalizer.fit(trainingData);
        normalizer.transform(trainingData);
        return trainingData;
    }

    private INDArray createPredictionData(List<LearnableModel> list) {
        int size = list.size();
        double[][] data = new double[size][featuresCount];
        for (int i = 0;i<list.size();i++) {
            int[] features = list.get(i).getFeatures();
            for (int j = 0; j < features.length ; j++) {
                data[i][j] = features[j];
            }
        }
        INDArray predictionData = new NDArray(data);
        normalizer.transform(predictionData);
        return predictionData;
    }

    private List<LearnableModel> returnPredictions(List<LearnableModel> list, INDArray output){
        for (int i = 0; i < list.size() ; i++) {
            int probability = (int) Math.round(output.slice(i).getDouble(0)*100);;
            list.get(i).setProbability(probability);
        }
        return list;
    }
}