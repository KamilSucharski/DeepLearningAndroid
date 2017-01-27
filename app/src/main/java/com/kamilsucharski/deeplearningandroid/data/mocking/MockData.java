package com.kamilsucharski.deeplearningandroid.data.mocking;

import com.kamilsucharski.deeplearningandroid.data.model.Job;
import com.kamilsucharski.deeplearningandroid.deeplearning.LearnableModel;

import java.util.LinkedList;
import java.util.List;

public class MockData {
    public static List<LearnableModel> mockSet() {
        List<LearnableModel> trainSet = new LinkedList<LearnableModel>();

        //Mechanic likes from 6 up
        trainSet.add(new Job(0,5,0));
        trainSet.add(new Job(0,6,1));
        trainSet.add(new Job(0,7,1));
        trainSet.add(new Job(0,8,1));
        trainSet.add(new Job(0,9,1));
        trainSet.add(new Job(0,10,1));
        trainSet.add(new Job(0,11,1));
        trainSet.add(new Job(0,12,1));
        trainSet.add(new Job(0,13,1));
        trainSet.add(new Job(0,14,1));
        trainSet.add(new Job(0,15,1));
        trainSet.add(new Job(0,16,1));
        trainSet.add(new Job(0,17,1));
        trainSet.add(new Job(0,18,1));
        trainSet.add(new Job(0,19,1));
        trainSet.add(new Job(0,20,1));

        //Programmer likes from 8 up
        trainSet.add(new Job(1,5,0));
        trainSet.add(new Job(1,6,0));
        trainSet.add(new Job(1,7,0));
        trainSet.add(new Job(1,8,1));
        trainSet.add(new Job(1,9,1));
        trainSet.add(new Job(1,10,1));
        trainSet.add(new Job(1,11,1));
        trainSet.add(new Job(1,12,1));
        trainSet.add(new Job(1,13,1));
        trainSet.add(new Job(1,14,1));
        trainSet.add(new Job(1,15,1));
        trainSet.add(new Job(1,16,1));
        trainSet.add(new Job(0,17,1));
        trainSet.add(new Job(0,18,1));
        trainSet.add(new Job(0,19,1));
        trainSet.add(new Job(0,20,1));

        //Teacher likes from 10 up
        trainSet.add(new Job(2,5,0));
        trainSet.add(new Job(2,6,0));
        trainSet.add(new Job(2,7,0));
        trainSet.add(new Job(2,8,0));
        trainSet.add(new Job(2,9,0));
        trainSet.add(new Job(2,10,1));
        trainSet.add(new Job(2,11,1));
        trainSet.add(new Job(2,12,1));
        trainSet.add(new Job(2,13,1));
        trainSet.add(new Job(2,14,1));
        trainSet.add(new Job(2,15,1));
        trainSet.add(new Job(2,16,1));
        trainSet.add(new Job(0,17,1));
        trainSet.add(new Job(0,18,1));
        trainSet.add(new Job(0,19,1));
        trainSet.add(new Job(0,20,1));

        //Taxi driver likes from 12 up
        trainSet.add(new Job(3,5,0));
        trainSet.add(new Job(3,6,0));
        trainSet.add(new Job(3,7,0));
        trainSet.add(new Job(3,8,0));
        trainSet.add(new Job(3,9,0));
        trainSet.add(new Job(3,10,0));
        trainSet.add(new Job(3,11,0));
        trainSet.add(new Job(3,12,1));
        trainSet.add(new Job(3,13,1));
        trainSet.add(new Job(3,14,1));
        trainSet.add(new Job(3,15,1));
        trainSet.add(new Job(3,16,1));
        trainSet.add(new Job(0,17,1));
        trainSet.add(new Job(0,18,1));
        trainSet.add(new Job(0,19,1));
        trainSet.add(new Job(0,20,1));

        //Manager likes likes from 14 up
        trainSet.add(new Job(4,5,0));
        trainSet.add(new Job(4,6,0));
        trainSet.add(new Job(4,7,0));
        trainSet.add(new Job(4,8,0));
        trainSet.add(new Job(4,9,0));
        trainSet.add(new Job(4,10,0));
        trainSet.add(new Job(4,11,0));
        trainSet.add(new Job(4,12,0));
        trainSet.add(new Job(4,13,0));
        trainSet.add(new Job(4,14,1));
        trainSet.add(new Job(4,15,1));
        trainSet.add(new Job(4,16,1));
        trainSet.add(new Job(0,17,1));
        trainSet.add(new Job(0,18,1));
        trainSet.add(new Job(0,19,1));
        trainSet.add(new Job(0,20,1));

        //Carpenter likes from 16 up
        trainSet.add(new Job(5,5,0));
        trainSet.add(new Job(5,6,0));
        trainSet.add(new Job(5,7,0));
        trainSet.add(new Job(5,8,0));
        trainSet.add(new Job(5,9,0));
        trainSet.add(new Job(5,10,0));
        trainSet.add(new Job(5,11,0));
        trainSet.add(new Job(5,12,0));
        trainSet.add(new Job(5,13,0));
        trainSet.add(new Job(5,14,0));
        trainSet.add(new Job(5,15,0));
        trainSet.add(new Job(5,16,1));
        trainSet.add(new Job(0,17,1));
        trainSet.add(new Job(0,18,1));
        trainSet.add(new Job(0,19,1));
        trainSet.add(new Job(0,20,1));

        return trainSet;
    }
}
