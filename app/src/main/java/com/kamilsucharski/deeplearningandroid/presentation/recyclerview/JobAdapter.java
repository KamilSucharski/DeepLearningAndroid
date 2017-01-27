package com.kamilsucharski.deeplearningandroid.presentation.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamilsucharski.deeplearningandroid.R;
import com.kamilsucharski.deeplearningandroid.data.database.JobDataBase;
import com.kamilsucharski.deeplearningandroid.deeplearning.LearnableModel;
import com.kamilsucharski.deeplearningandroid.deeplearning.ProbabilityComparator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobAdapter extends RecyclerView.Adapter<JobViewHolder> {

    private List<LearnableModel> jobs;
    private Map<Integer, LearnableModel> jobsToLearn = new HashMap<>();

    public JobAdapter(List<LearnableModel> jobs) {
        this.jobs = jobs;
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
        JobViewHolder holder = new JobViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        Map<String, Integer> job = jobs.get(position).getValues();
        if (jobsToLearn.containsKey(position)) {
            holder.setValues(decodeType(job.get("type")), job.get("salary"), job.get("probability"), jobsToLearn.get(position).getValues().get("choice"));
        } else {
            holder.setValues(decodeType(job.get("type")), job.get("salary"), job.get("probability"), holder.NO_CHOICE);
        }

        try {
            Class[] parameterTypes = new Class[2];
            parameterTypes[0] = int.class;
            parameterTypes[1] = boolean.class;
            Method method = getClass().getDeclaredMethod("setUserChoice", parameterTypes);
            holder.setUserChoiceCallback(this, method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setJobs(List<LearnableModel> jobs) {
        jobsToLearn = new HashMap<>();
        this.jobs = jobs;
        Collections.sort(jobs, new ProbabilityComparator());
        notifyDataSetChanged();
    }

    public void writeJobs() {
        JobDataBase.writeJobs(new ArrayList<>(jobsToLearn.values()));
    }

    public void setUserChoice(int position, boolean choice) {
        LearnableModel job = jobs.get(position);
        job.setLabels(new int[]{encodeChoice(choice)});
        jobsToLearn.put(position, job);
    }

    private String decodeType(int job) {
        switch (job) {
            case 0: return "Mechanic";
            case 1: return "Programmer";
            case 2: return "Teacher";
            case 3: return "Taxi driver";
            case 4: return "Manager";
            case 5: return "Carpenter";
            default: return "";
        }
    }

    private int encodeChoice(boolean choice) {
        return choice ? 1: 0;
    }
}
