package net.archeryc.scrollvideodemo;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.danikula.videocache.HttpProxyCacheServer;

import net.archeryc.scrollvideodemo.widget.QfVideo.QfVideoView;
import net.archeryc.scrollvideodemo.widget.TextureVideoView;

public class SingleQFActivity extends AppCompatActivity {

    private QfVideoView videoView;
    private HttpProxyCacheServer server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_qf);
        server = new HttpProxyCacheServer(getApplicationContext());
        videoView = findViewById(R.id.videoView);
        videoView.setVideoUrl("https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0");
        videoView.loop();
        videoView.getMediaPlayer()._prepareAsync();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.release();
        videoView.reset();
    }

    public void pause(View view) {
        videoView.pause();
    }

    public void start(View view) {
        videoView.start();
    }
}
