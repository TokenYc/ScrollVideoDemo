package net.archeryc.scrollvideodemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danikula.videocache.HttpProxyCacheServer;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import net.archeryc.scrollvideodemo.widget.QfVideo.QfVideoView;

import java.util.ArrayList;
import java.util.List;

public class QFRecyclerActivity extends AppCompatActivity {

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

    private VideoEntity[] videoList = {
            new VideoEntity("https://p3.pstatp.com/large/5f7c000eab738b7dc8cb.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0"),
            new VideoEntity("https://p1.pstatp.com/large/76e10008ac581c53a584.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5a36be93ee5c46b9952f959ba198514b&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/76de00045e6b998349cd.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5d4f6791289645b488b61e41d6db55bd&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/7c15000c2b833ffb7084.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5de5b22a25ef48a78847c26185099bdb&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/6af70002d4c2c66a6595.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=179c5454de014f80b4bb8e54296dee96&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/5f7c000eab738b7dc8cb.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0"),
            new VideoEntity("https://p1.pstatp.com/large/76e10008ac581c53a584.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5a36be93ee5c46b9952f959ba198514b&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/76de00045e6b998349cd.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5d4f6791289645b488b61e41d6db55bd&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/7c15000c2b833ffb7084.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5de5b22a25ef48a78847c26185099bdb&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/6af70002d4c2c66a6595.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=179c5454de014f80b4bb8e54296dee96&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/5f7c000eab738b7dc8cb.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0"),
            new VideoEntity("https://p1.pstatp.com/large/76e10008ac581c53a584.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5a36be93ee5c46b9952f959ba198514b&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/76de00045e6b998349cd.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5d4f6791289645b488b61e41d6db55bd&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/7c15000c2b833ffb7084.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5de5b22a25ef48a78847c26185099bdb&line=0"),
            new VideoEntity("https://p3.pstatp.com/large/6af70002d4c2c66a6595.jpg",
                    "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=179c5454de014f80b4bb8e54296dee96&line=0"),
    };

    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private MyAdapter adapter;
    private HttpProxyCacheServer mProxyCacheServer;
    private QfVideoView mCurrentVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mProxyCacheServer = new HttpProxyCacheServer(this);
        adapter = new MyAdapter();
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                List<MyAdapter.ItemViewHolder> viewHolders = adapter.getHolders();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    if (firstVisibleItemPosition == lastVisibleItemPosition) {
                        for (final MyAdapter.ItemViewHolder holder : viewHolders) {
                            if (holder.equals(recyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition))) {
                                if (!holder.videoView.isPlaying()) {
                                    holder.videoView.setVideoUrl(videoList[firstVisibleItemPosition].getVideo());
                                    holder.videoView.loop();
                                    holder.videoView.prepareAsync();
                                    mCurrentVideoView = holder.videoView;
                                }
                            } else {
                                holder.videoView.reset();
                                holder.videoView.showCover();
                            }
                        }
                    }
//                    Log.d("position", "first---->" + firstVisibleItemPosition + "last---->" + lastVisibleItemPosition);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCurrentVideoView != null && mCurrentVideoView.isPlaying()) {
            mCurrentVideoView.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCurrentVideoView != null && !mCurrentVideoView.isPlaying()) {
            mCurrentVideoView.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (MyAdapter.ItemViewHolder viewHolder : adapter.getHolders()) {
            viewHolder.videoView.release();
        }
    }


    class MyAdapter extends RecyclerView.Adapter {

        private List<MyAdapter.ItemViewHolder> holders = new ArrayList<>();

        private boolean isFirstPlay = true;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(QFRecyclerActivity.this).inflate(R.layout.item_qf_recycler, parent, false);
            return new MyAdapter.ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            MyAdapter.ItemViewHolder itemViewHolder = (MyAdapter.ItemViewHolder) holder;
            itemViewHolder.videoView.setTag("position--->" + position);
            itemViewHolder.sdvCover.setImageURI(videoList[position].getCover());
            itemViewHolder.sdvCover.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
            itemViewHolder.sdvCover.getHierarchy().setFadeDuration(0);
            itemViewHolder.videoView.showCover();
            if (position == 0 && isFirstPlay) {
                itemViewHolder.videoView.setVideoUrl(videoList[position].getVideo());
                itemViewHolder.videoView.loop();
                itemViewHolder.videoView.getMediaPlayer().prepareAsync();
                mCurrentVideoView = itemViewHolder.videoView;
            }
            holders.add(itemViewHolder);
        }

        @Override
        public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
            super.onViewRecycled(holder);
            MyAdapter.ItemViewHolder itemViewHolder = (MyAdapter.ItemViewHolder) holder;
            itemViewHolder.videoView.stop();
            itemViewHolder.videoView.reset();
            holders.remove(itemViewHolder);
        }

        @Override
        public int getItemCount() {
            return videoList.length;
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            QfVideoView videoView;
            SimpleDraweeView sdvCover;

            public ItemViewHolder(View itemView) {
                super(itemView);
                videoView = itemView.findViewById(R.id.videoView);
                sdvCover = new SimpleDraweeView(QFRecyclerActivity.this);
                videoView.setCover(sdvCover);
            }
        }

        public List<MyAdapter.ItemViewHolder> getHolders() {
            return holders;
        }
    }


}
