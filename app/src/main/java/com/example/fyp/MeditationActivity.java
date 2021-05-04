package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.Vector;

public class MeditationActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<Video> youtubeVideos = new Vector<Video>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        youtubeVideos.add( new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/TP2gb2fSYXY\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/ZToicYcHIOU\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/inpok4MKVLM\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1ZYbU82GVz4\" frameborder=\"0\" allowfullscreen></iframe>") );
        youtubeVideos.add( new Video("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jeGT1VXwfx4\" frameborder=\"0\" allowfullscreen></iframe>") );

        Adapter videoAdapter = new Adapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
    }
}
