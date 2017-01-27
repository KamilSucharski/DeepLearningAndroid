package com.kamilsucharski.deeplearningandroid.presentation.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kamilsucharski.deeplearningandroid.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JobViewHolder extends RecyclerView.ViewHolder {
    public final int DISLIKE = 0;
    public final int LIKE = 1;
    public final int NO_CHOICE = 2;
    private TextView jobItemName;
    private TextView jobItemSalary;
    private TextView jobItemPercentage;
    private CardView likeButton;
    private CardView dislikeButton;
    private Method userChoiceCallback;
    private Object parent;

    public JobViewHolder(View itemView) {
        super(itemView);
        jobItemName = (TextView) itemView.findViewById(R.id.job_item_name);
        jobItemSalary = (TextView) itemView.findViewById(R.id.job_item_salary);
        jobItemPercentage = (TextView) itemView.findViewById(R.id.job_item_percentage);
        likeButton = (CardView) itemView.findViewById(R.id.job_item_like_button);
        dislikeButton = (CardView) itemView.findViewById(R.id.job_item_dislike_button);
        initializeButtons();
    }

    public void setValues(String name, int salary, int percentage, int choice) {
        setIsRecyclable(false);
        jobItemName.setText(name);
        jobItemSalary.setText(String.valueOf(salary));
        jobItemPercentage.setText(String.valueOf(percentage));
        setPercentageColor(percentage);
        switch (choice) {
            case DISLIKE: setDislikeColors(); break;
            case LIKE: setLikeColors(); break;
            case NO_CHOICE: setNeutralColors(); break;
        }
    }

    public void setUserChoiceCallback(Object parent, Method method) {
        userChoiceCallback = method;
        this.parent = parent;
    }

    private void setPercentageColor(int percentage) {
        if (percentage < 35) {
            jobItemPercentage.setTextColor(Color.RED);
        } else if (percentage < 70) {
            jobItemPercentage.setTextColor(Color.YELLOW);
        } else jobItemPercentage.setTextColor(Color.GREEN);
    }

    private void initializeButtons() {
        likeButton.setOnClickListener(v -> {
            setLikeColors();
            try {
                userChoiceCallback.invoke(parent, getAdapterPosition(), true);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });

        dislikeButton.setOnClickListener(v -> {
            setDislikeColors();
            try {
                userChoiceCallback.invoke(parent, getAdapterPosition(), false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        });
    }

    private void setNeutralColors(){
        likeButton.setCardBackgroundColor(Color.WHITE);
        dislikeButton.setCardBackgroundColor(Color.WHITE);
    }

    private void setLikeColors(){
        likeButton.setCardBackgroundColor(Color.GREEN);
        dislikeButton.setCardBackgroundColor(Color.WHITE);
    }

    private void setDislikeColors(){
        likeButton.setCardBackgroundColor(Color.WHITE);
        dislikeButton.setCardBackgroundColor(Color.RED);
    }
}
