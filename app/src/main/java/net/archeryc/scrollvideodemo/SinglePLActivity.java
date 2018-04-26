package net.archeryc.scrollvideodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pili.pldroid.player.widget.PLVideoTextureView;

public class SinglePLActivity extends AppCompatActivity {

    private PLVideoTextureView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pl);
        videoView=findViewById(R.id.videoView);
        videoView.setVideoPath("https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0");
        videoView.setLooping(true);
        videoView.start();
    }
}
