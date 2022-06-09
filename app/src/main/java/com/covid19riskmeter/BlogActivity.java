package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.covid19riskmeter.Classes.Blog;
import com.covid19riskmeter.Classes.Operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

            for(Blog b: Blog.list){
                View view = getLayoutInflater().inflate(R.layout.blog,null);
                TextView title = (TextView) view.findViewById(R.id.txtBlog);
                ImageView img = (ImageView) view.findViewById(R.id.imgBlog2);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Blog.current=Blog.list.indexOf(b);
                        Operations.moveToPage(BlogActivity.this,ArticleActivity.class,false);
                    }
                });
                Glide.with(view).load(b.getCoverPhoto()).into(img);
                title.setText(b.getTitle());
                blogLayout.addView(view);
            }






    }
}