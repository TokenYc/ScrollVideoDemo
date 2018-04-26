package net.archeryc.scrollvideodemo;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {


    VerticalViewPager viewPager;

    private int lastPage = -1;

    private String[] videoUrls = {
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5a36be93ee5c46b9952f959ba198514b&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5d4f6791289645b488b61e41d6db55bd&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5de5b22a25ef48a78847c26185099bdb&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=179c5454de014f80b4bb8e54296dee96&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5a36be93ee5c46b9952f959ba198514b&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5d4f6791289645b488b61e41d6db55bd&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5de5b22a25ef48a78847c26185099bdb&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=179c5454de014f80b4bb8e54296dee96&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5a36be93ee5c46b9952f959ba198514b&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5d4f6791289645b488b61e41d6db55bd&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5de5b22a25ef48a78847c26185099bdb&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=179c5454de014f80b4bb8e54296dee96&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5a36be93ee5c46b9952f959ba198514b&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5d4f6791289645b488b61e41d6db55bd&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5de5b22a25ef48a78847c26185099bdb&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=179c5454de014f80b4bb8e54296dee96&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5a36be93ee5c46b9952f959ba198514b&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5d4f6791289645b488b61e41d6db55bd&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5de5b22a25ef48a78847c26185099bdb&line=0",
            "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=179c5454de014f80b4bb8e54296dee96&line=0",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);
        final ViewPagerActivity.MyPagerAdapter pagerAdapter = new ViewPagerActivity.MyPagerAdapter();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                List<View> views = pagerAdapter.getViews();
//                if (lastPage >= 0) {
//                    PLVideoTextureView videoView = views.get(lastPage).findViewById(R.id.videoView);
//                    if (videoView != null&&videoView.isPlaying()) {
//                        videoView.seekTo(0);
//                        videoView.pause();
//                    }
//                }
//                lastPage = position;
                if (position >= 1) {
                    PLVideoTextureView videoView = views.get(position - 1).findViewById(R.id.videoView);
                    if (videoView.isPlaying()) {
                        videoView.seekTo(0);
                        videoView.pause();
                    }
                }
                if (position < videoUrls.length - 1) {
                    PLVideoTextureView videoView = views.get(position + 1).findViewById(R.id.videoView);
                    if (videoView.isPlaying()) {
                        videoView.seekTo(0);
                        videoView.pause();
                    }
                }


                PLVideoTextureView videoView = views.get(position).findViewById(R.id.videoView);
                videoView.setVideoPath(videoUrls[position]);
                videoView.setLooping(true);
                videoView.start();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
    }

    class MyPagerAdapter extends PagerAdapter {

        List<View> views;
        AVOptions options;

        public MyPagerAdapter() {
            options = new AVOptions();
            options.setInteger(AVOptions.KEY_FAST_OPEN, 1);

            views = new ArrayList();
            for (int i = 0; i < videoUrls.length; i++) {
                View view = LayoutInflater.from(ViewPagerActivity.this).inflate(R.layout.item_pager, null, false);
                view.setBackgroundColor(Color.BLACK);
                views.add(view);
            }
        }

        @Override
        public int getCount() {
            return videoUrls.length;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            PLVideoTextureView videoView = views.get(position).findViewById(R.id.videoView);
            videoView.setAVOptions(options);
            container.addView(views.get(position));
            videoView.setVideoPath(videoUrls[position]);
            videoView.setLooping(true);
            videoView.start();
            videoView.pause();
            return views.get(position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            PLVideoTextureView videoView = views.get(position).findViewById(R.id.videoView);
            videoView.stopPlayback();
            container.removeView(views.get(position));
        }

        public List<View> getViews() {
            return views;
        }
    }
}
