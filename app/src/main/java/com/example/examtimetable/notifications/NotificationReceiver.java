package com.example.examtimetable.notifications;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.example.examtimetable.R;
import com.example.examtimetable.student.ExamListActivity;
import com.example.examtimetable.utils.Constants;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "exam_notifications";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("NotificationReceiver", "onReceive: Notification received");
        String examId = intent.getStringExtra(Constants.EXAM_ID_KEY);
        String subjectName = intent.getStringExtra(Constants.SUBJECT_NAME_KEY);
        String paperCode = intent.getStringExtra(Constants.PAPER_CODE_KEY);
        String examDate = intent.getStringExtra(Constants.EXAM_DATE_KEY);
        String examTime = intent.getStringExtra(Constants.EXAM_TIME_KEY);
        String duration = intent.getStringExtra(Constants.DURATION_KEY);

        createNotificationChannel(context);

        Intent examListIntent = new Intent(context, ExamListActivity.class);
        examListIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, examListIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Exam Alert")
                .setContentText("Upcoming exam: " + subjectName + " (" + paperCode + ") at " + examTime + " on " + examDate)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.notify(Constants.NOTIFICATION_ID_OFFSET+Integer.parseInt(examId), builder.build());
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Exam Notifications";
            String description = "Channel for exam notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}