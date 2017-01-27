package com.kamilsucharski.deeplearningandroid.deeplearning;

import java.util.Map;

public interface LearnableModel {
    int[] getFeatures();
    void setFeatures(int[] feautures);
    int[] getLabels();
    void setLabels(int[] labels);
    Map<String, Integer> getValues();
    void setProbability(int probability);
}
