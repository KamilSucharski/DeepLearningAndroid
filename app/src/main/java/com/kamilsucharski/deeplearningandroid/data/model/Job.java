package com.kamilsucharski.deeplearningandroid.data.model;

import com.kamilsucharski.deeplearningandroid.deeplearning.LearnableModel;

import java.util.HashMap;
import java.util.Map;

public class Job implements LearnableModel {
    private int type;
    private int salary;
    private int choice;
    private int probability;

    public Job(int type, int salary, int choice) {
        this.type = type;
        this.salary = salary;
        this.choice = choice;
    }

    @Override
    public void setProbability(int probability) {
        this.probability = probability;
    }

    @Override
    public int[] getFeatures() {
        return new int[]{type, salary};
    }

    @Override
    public void setFeatures(int[] feautures) {
        type = feautures[0];
        salary = feautures[1];
        choice = feautures[2];
    }

    @Override
    public int[] getLabels() {
        return new int[]{choice};
    }

    @Override
    public void setLabels(int[] labels) {
        this.choice = labels[0];
    }

    @Override
    public Map<String, Integer> getValues() {
        Map<String, Integer> values = new HashMap<>();
        values.put("type", type);
        values.put("salary", salary);
        values.put("choice", choice);
        values.put("probability", probability);
        return values;
    }

}
