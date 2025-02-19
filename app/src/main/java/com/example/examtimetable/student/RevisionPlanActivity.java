package com.example.examtimetable.student;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.examtimetable.R;

public class RevisionPlanActivity extends AppCompatActivity {
    private TextView examDetailsTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_plan);
        examDetailsTextView = findViewById(R.id.examDetailsTextView);
        String examDetails = getIntent().getStringExtra("examDetails");
        examDetailsTextView.setText(examDetails);
    }
}