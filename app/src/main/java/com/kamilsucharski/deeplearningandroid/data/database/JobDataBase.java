package com.kamilsucharski.deeplearningandroid.data.database;

import com.kamilsucharski.deeplearningandroid.deeplearning.LearnableModel;

import java.util.LinkedList;
import java.util.List;

public class JobDataBase {
    private static List<LearnableModel> savedModels = new LinkedList<>();

    public static void writeJobs(List<LearnableModel> models) {
        savedModels.addAll(models);
    }

    public static List<LearnableModel> readJobs() {
        return savedModels;
    }
}
