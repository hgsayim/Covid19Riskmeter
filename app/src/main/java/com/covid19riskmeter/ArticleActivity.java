package com.covid19riskmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.covid19riskmeter.Classes.Blog;

import java.util.Collections;

public class ArticleActivity extends AppCompatActivity {
    TextView txtTitle,txtArticle;
    ImageView imgArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        txtTitle = (TextView) findViewById(R.id.txtArticleTitle);
        txtArticle = (TextView) findViewById(R.id.txtArticleText);
        imgArticle = (ImageView) findViewById(R.id.imgArticle);
        Blog b = Blog.list.get(Blog.current);
        txtTitle.setText(b.getTitle());
        txtArticle.setText(b.getContent());
        Glide.with(this).load(b.getCoverPhoto()).into(imgArticle);


    }
}