package net.archeryc.scrollvideodemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;

import java.util.ArrayList;
import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        Fresco.initialize(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IjkMediaPlayer.native_profileEnd();
    }

    public void jumpViewPager(View view) {
        Intent intent = new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }



    public void jumpSinglePL(View view){
        Intent intent=new Intent(this,SinglePLActivity.class);
        startActivity(intent);
    }

    public void jumpSingleQF(View view){
        Intent intent=new Intent(this,SingleQFActivity.class);
        startActivity(intent);
    }

    public void jumpRecyclerView(View view) {
        Intent intent = new Intent(this, RecyclerViewActivity.class);
        startActivity(intent);
    }

    public void jumpQFRecycler(View view){
        Intent intent=new Intent(this,QFRecyclerActivity.class);
        startActivity(intent);
    }

}
