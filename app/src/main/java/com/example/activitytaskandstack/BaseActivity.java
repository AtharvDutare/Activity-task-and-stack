package com.example.activitytaskandstack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected static ActivityManager activityManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(activityManager==null){
            activityManager=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
        }

    }

    protected void startActivity(Activity activity, Class targetActivityClass){
        Intent intent=new Intent(activity,targetActivityClass);
        startActivity(intent);
    }

    protected static int getNumberOfTasks(){
        return activityManager.getAppTasks().size();
    }

    protected static String getAppTaskState(){

        StringBuilder stringBuilder=new StringBuilder();
        int totalNumberOfTasks=activityManager.getRunningTasks(10).size();//Returns total number of tasks - stacks
        stringBuilder.append("\nTotal Number of Tasks: "+totalNumberOfTasks+"\n\n");

        List<ActivityManager.RunningTaskInfo> taskInfo =activityManager.getRunningTasks(10);//returns List of RunningTaskInfo - corresponding to tasks - stacks

        for(ActivityManager.RunningTaskInfo info:taskInfo){
            stringBuilder.append("Task Id: "+info.id+", Number of Activities : "+info.numActivities+"\n");//Number of Activities in task - stack

            // Display the root Activity of task-stack
            stringBuilder.append("TopActivity: "+info.topActivity.getClassName().
                    toString().replace("lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo.","")+"\n");

            // Display the top Activity of task-stack
            stringBuilder.append("BaseActivity:"+info.baseActivity.getClassName().
                    toString().replace("lauchmodesdemo.youtube.codetutor.com.activitylauchmodesdemo.","")+"\n");
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
