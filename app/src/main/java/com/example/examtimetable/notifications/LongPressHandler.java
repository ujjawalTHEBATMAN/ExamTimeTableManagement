package com.example.examtimetable.notifications;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import com.example.examtimetable.student.ResourceViewActivity;
import com.example.examtimetable.student.RevisionPlanActivity;

public class LongPressHandler {
    private Context context;

    public LongPressHandler(Context context) {
        this.context = context;
    }

    public void handleLongPress(String examDetails) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Exam Options");
        builder.setMessage("What do you want to do with " + examDetails + "?");
        builder.setPositiveButton("View Resources", (dialog, id) -> {
            Intent intent = new Intent(context, ResourceViewActivity.class);
            intent.putExtra("examDetails", examDetails);
            context.startActivity(intent);
        });
        builder.setNegativeButton("Create Revision Plan", (dialog, id) -> {
            Intent intent = new Intent(context, RevisionPlanActivity.class);
            intent.putExtra("examDetails", examDetails);
            context.startActivity(intent);
        });
        builder.setNeutralButton("Cancel", (dialog, id) -> {
            dialog.dismiss();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}