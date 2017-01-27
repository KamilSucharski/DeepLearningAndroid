package com.kamilsucharski.deeplearningandroid.data.mocking;

import com.kamilsucharski.deeplearningandroid.data.model.Job;
import com.kamilsucharski.deeplearningandroid.deeplearning.LearnableModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomizeData {

    public static Job randomizeJob() {
        return new Job(randomNumber(0,5),randomNumber(5,20),0);
    }

    public static List<LearnableModel> randomizeJobs(int number) {
        List<LearnableModel> jobs = new LinkedList<>();
        for (int i=0;i<number;i++) {
            jobs.add(randomizeJob());
        }
        return jobs;
    }

    private static int randomNumber(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
