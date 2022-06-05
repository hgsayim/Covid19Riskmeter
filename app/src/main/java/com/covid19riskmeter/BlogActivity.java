package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BlogActivity extends AppCompatActivity {
    LinearLayout blogLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        blogLayout = findViewById(R.id.blogLayout);
        addBlogs();
    }

    public void addBlogs(){


            View view = getLayoutInflater().inflate(R.layout.blog,null);
            TextView title = (TextView) view.findViewById(R.id.txtBlog);
            title.setText("ASdasfsdfsf");
            blogLayout.addView(view);


    }
}