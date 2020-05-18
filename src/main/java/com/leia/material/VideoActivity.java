package com.leia.material;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {
    MediaController mediaController;
    VideoView videoView;
    TextView videoname;
    TextView videoinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.video_show);
        videoname=findViewById(R.id.showVideo_name);
        videoinfo=findViewById(R.id.showVideo_info);
        mediaController = new MediaController(this);
        Intent intent = getIntent();
        final String url = intent.getStringExtra("url");
        final String name=intent.getStringExtra("videoname");
        final String info=intent.getStringExtra("videoinfo");
        final String imageurl=intent.getStringExtra("imageurl");
            videoname.setText(name.trim());
            videoinfo.setText(info.trim());

        videoView.setVideoURI(Uri.parse(url));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                videoView.start();
            }
        });

    }

}
