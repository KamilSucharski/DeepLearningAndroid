package com.kamilsucharski.deeplearningandroid.deeplearning;

import java.util.Comparator;

public class ProbabilityComparator implements Comparator<LearnableModel> {
    @Override
    public int compare(LearnableModel o1, LearnableModel o2) {
        return o2.getValues().get("probability").compareTo(o1.getValues().get("probability"));
    }
}
