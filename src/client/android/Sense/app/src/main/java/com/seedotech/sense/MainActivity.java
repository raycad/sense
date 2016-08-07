package com.seedotech.sense;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String URI_DEFAULT = "rtsp://192.168.100.6:8554/data/6.aac";

    private VideoView mVideoView = null;
    private MediaPlayer mMediaPlayer = null;
    private EditText mContentUriEditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void createVideoView() {
        if (mVideoView != null)
            return;

        mVideoView = (VideoView)findViewById(R.id.videoView);
        /*MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(mVideoView);
        mVideoView.setMediaController(vidControl);*/
    }

    protected void initUI() {
        mContentUriEditText = (EditText)findViewById(R.id.contentUriEditText);
        mContentUriEditText.setText(URI_DEFAULT);

        Button playBtn = (Button)findViewById(R.id.play);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = mContentUriEditText.getText().toString();
                play(uri);
            }
        });

        Button stopBtn = (Button)findViewById(R.id.stop);
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.stopPlayback();
            }
        });
    }

    public void play(String uri) {
        // Create a video view if needed
        createVideoView();

        Uri srcUri = Uri.parse(uri);
        mVideoView.setVideoURI(srcUri);
        mVideoView.start();
    }
}
