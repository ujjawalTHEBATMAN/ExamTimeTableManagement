package com.example.examtimetable.student;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examtimetable.R;

public class ResourceViewActivity extends AppCompatActivity {
    private WebView resourceWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_view);
        resourceWebView = findViewById(R.id.resourceWebView);
        resourceWebView.getSettings().setJavaScriptEnabled(true);
        resourceWebView.setWebViewClient(new android.webkit.WebViewClient());
        String examDetails = getIntent().getStringExtra("examDetails");
        loadResourceForExam(examDetails);
    }

    private void loadResourceForExam(String examDetails) {
        String url = "https://www.example.com/";
        // Basic exam-specific content loading.
        if (examDetails.contains("Math")) {
            url = "https://www.math-resources.com/";
        } else if (examDetails.contains("Science")) {
            url = "https://www.science-resources.com/";
        } else if (examDetails.contains("History")) {
            url = "https://www.history-resources.com/";
        } else if (examDetails.contains("English")){
            url = "https://www.english-resources.com/";
        }
        resourceWebView.loadUrl(url);
    }
}

```
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/examDetailsTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="Exam Details Here" />

</RelativeLayout>