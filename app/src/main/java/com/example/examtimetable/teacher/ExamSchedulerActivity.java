package com.example.examtimetable.teacher;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;

import com.example.examtimetable.R;
import java.util.Calendar;
import java.util.Locale;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import com.example.examtimetable.notifications.NotificationReceiver;
import com.example.examtimetable.utils.Constants;

public class ExamSchedulerActivity extends AppCompatActivity {
    private EditText examIdEditText, subjectNameEditText, paperCodeEditText, examDateEditText, examTimeEditText, durationEditText;
    private Button setNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_scheduler);

        examIdEditText = findViewById(R.id.examIdEditText);
        subjectNameEditText = findViewById(R.id.subjectNameEditText);
        paperCodeEditText = findViewById(R.id.paperCodeEditText);
        examDateEditText = findViewById(R.id.examDateEditText);
        examTimeEditText = findViewById(R.id.examTimeEditText);
        durationEditText = findViewById(R.id.durationEditText);
        setNotificationButton = findViewById(R.id.setNotificationButton);

        examDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        examTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        setNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleNotification();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                examDateEditText.setText(String.format(Locale.getDefault(), "%02d-%02d-%d", dayOfMonth, monthOfYear + 1, year));
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                examTimeEditText.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void scheduleNotification() {
        String examId = examIdEditText.getText().toString();
        String subjectName = subjectNameEditText.getText().toString();
        String paperCode = paperCodeEditText.getText().toString();
        String examDate = examDateEditText.getText().toString();
        String examTime = examTimeEditText.getText().toString();
        String duration = durationEditText.getText().toString();

        Intent notificationIntent = new Intent(this, NotificationReceiver.class);
        notificationIntent.putExtra(Constants.EXAM_ID_KEY, examId);
        notificationIntent.putExtra(Constants.SUBJECT_NAME_KEY, subjectName);
        notificationIntent.putExtra(Constants.PAPER_CODE_KEY, paperCode);
        notificationIntent.putExtra(Constants.EXAM_DATE_KEY, examDate);
        notificationIntent.putExtra(Constants.EXAM_TIME_KEY, examTime);
        notificationIntent.putExtra(Constants.DURATION_KEY, duration);

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(examTime.split(":")[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(examTime.split(":")[1]));
        calendar.set(Calendar.SECOND, 0);
        String[] dateParts = examDate.split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]) - 1; // Month is 0-indexed
        int year = Integer.parseInt(dateParts[2]);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);
    }
}