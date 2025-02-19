package com.example.examtimetable.teacher;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.examtimetable.R;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.examtimetable.utils.ScheduleGenerator;
import java.util.List;

public class AutoSchedulerActivity extends AppCompatActivity {
    private ScheduleGenerator scheduleGenerator;
    private TextView generatedScheduleTextView;
    private Button generateScheduleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_scheduler);
        scheduleGenerator = new ScheduleGenerator();
        generatedScheduleTextView = findViewById(R.id.generatedScheduleTextView);
        generateScheduleButton = findViewById(R.id.generateScheduleButton);
        generateScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateAndDisplaySchedule();
            }
        });
    }
    private void generateAndDisplaySchedule() {
        List<String> schedule = scheduleGenerator.generateSchedule();
        // Display the schedule
        String scheduleText = "";
        for (String item : schedule) {
            scheduleText += item + "\n";
        }
        generatedScheduleTextView.setText(scheduleText);
    }
}