package com.example.examtimetable.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.examtimetable.R;
import java.util.ArrayList;
import java.util.List;
import com.example.examtimetable.notifications.LongPressHandler;

public class ExamListActivity extends AppCompatActivity {
    private ListView examListView;
    private LongPressHandler longPressHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_list);
        examListView = findViewById(R.id.examListView);
        longPressHandler = new LongPressHandler(this);
        // Sample exam data (replace with actual data loading)
        List<String> examList = new ArrayList<>();
        examList.add("Math Exam - 2024-06-15 10:00");
        examList.add("Science Exam - 2024-06-16 13:00");
        examList.add("History Exam - 2024-06-17 09:00");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, examList);
        examListView.setAdapter(adapter);
        // Set long click listener
        examListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedExam = examList.get(position);
                longPressHandler.handleLongPress(selectedExam);
                return true;
            }
        });
    }
}