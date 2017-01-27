package com.kamilsucharski.deeplearningandroid.presentation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.kamilsucharski.deeplearningandroid.R;
import com.kamilsucharski.deeplearningandroid.data.database.JobDataBase;
import com.kamilsucharski.deeplearningandroid.deeplearning.BinaryClassifier;
import com.kamilsucharski.deeplearningandroid.deeplearning.LearnableModel;
import com.kamilsucharski.deeplearningandroid.data.mocking.MockData;
import com.kamilsucharski.deeplearningandroid.data.mocking.RandomizeData;
import com.kamilsucharski.deeplearningandroid.presentation.recyclerview.JobAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private BinaryClassifier binaryClassifier;
    private int inputs = 2;
    private int outputs = 1;
    RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private Button mockDataButton;
    private Button learnButton;
    private Button refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binaryClassifier = new BinaryClassifier(inputs, outputs);
        initializeButtons();
        initializeList();
    }

    private void initializeButtons() {
        mockDataButton = (Button) findViewById(R.id.mock_data_button);
        learnButton = (Button) findViewById(R.id.learn_button);
        refreshButton = (Button) findViewById(R.id.refresh_button);

        mockDataButton.setOnClickListener(v -> train(MockData.mockSet()));

        learnButton.setOnClickListener(v -> writeJobs());

        refreshButton.setOnClickListener(v -> refreshList());
    }

    private void train(List<LearnableModel> trainSet) {
        disableButtons();
        Observable.just(trainSet)
            .doOnNext(binaryClassifier::train)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(aVoid -> {}, bVoid -> {}, this::enableButtons);
    }

    private void writeJobs() {
        Observable.just(jobAdapter)
            .doOnNext(JobAdapter::writeJobs)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(aVoid -> {}, bVoid -> {}, () -> train(JobDataBase.readJobs()));
    }

    private void initializeList() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        jobAdapter = new JobAdapter(RandomizeData.randomizeJobs(20));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(jobAdapter);
    }

    private void refreshList() {
        jobAdapter.setJobs(binaryClassifier.predict(RandomizeData.randomizeJobs(20)));
        recyclerView.scrollToPosition(0);
    }

    private void disableButtons() {
        mockDataButton.setBackgroundColor(Color.RED);
        learnButton.setBackgroundColor(Color.RED);
        refreshButton.setBackgroundColor(Color.RED);
        mockDataButton.setClickable(false);
        learnButton.setClickable(false);
        refreshButton.setClickable(false);
    }

    private void enableButtons() {
        mockDataButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        learnButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        refreshButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        mockDataButton.setClickable(true);
        learnButton.setClickable(true);
        refreshButton.setClickable(true);
    }
}
