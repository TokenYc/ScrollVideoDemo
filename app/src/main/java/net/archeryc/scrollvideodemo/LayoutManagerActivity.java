package net.archeryc.scrollvideodemo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import net.archeryc.scrollvideodemo.layoutmanager.OnViewPagerListener;
import net.archeryc.scrollvideodemo.layoutmanager.ViewPagerLayoutManager;
import net.archeryc.scrollvideodemo.widget.QfVideo.QfVideoView;
import net.archeryc.scrollvideodemo.widget.ScrollableRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LayoutManagerActivity extends AppCompatActivity implements OnViewPagerListener, ScrollableRecyclerView.OnLoadMoreListener {

    static List<VideoEntity> mMockData = new ArrayList<>();

    static {
        mMockData.add(new VideoEntity("https://p3.pstatp.com/large/5f7c000eab738b7dc8cb.jpg",
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=f1a73c4927e64d2e84b56563134141b8&line=0"));
        mMockData.add(new VideoEntity("https://p3.pstatp.com/large/8a350006cffb0056e596.jpg",
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200ff50000bc9ter8858lvukr08fk0&line=0"));
        mMockData.add(new VideoEntity("https://p3.pstatp.com/large/76de00045e6b998349cd.jpg",
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5d4f6791289645b488b61e41d6db55bd&line=0"));
        mMockData.add(new VideoEntity("https://p3.pstatp.com/large/7c15000c2b833ffb7084.jpg",
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=5de5b22a25ef48a78847c26185099bdb&line=0"));
        mMockData.add(new VideoEntity("https://p3.pstatp.com/large/6af70002d4c2c66a6595.jpg",
                "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=179c5454de014f80b4bb8e54296dee96&line=0"));
    }

    private ScrollableRecyclerView recyclerView;

    private ViewPagerLayoutManager layoutManager;

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager);
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new ViewPagerLayoutManager(this, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setOnLoadMoreListener(this);
        layoutManager.setOnViewPagerListener(this);

        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        getData();
    }

    private void getData() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int insertPosition = adapter.getItemCount();
                adapter.addData(mMockData);
                adapter.notifyItemRangeInserted(insertPosition, mMockData.size());
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(this, "onLoadMore", Toast.LENGTH_SHORT).show();
        getData();
    }

    @Override
    public void onPageRelease(boolean isNext, int position) {
        releaseVideo(position);
    }

    @Override
    public void onPageSelected(int position, boolean isBottom) {
        playVideo(position);
    }

    @Override
    public void onInitComplete() {
        playVideo(0);
    }

    private void playVideo(int position) {
        MyAdapter.ItemViewHolder itemViewHolder = (MyAdapter.ItemViewHolder) recyclerView.findViewHolderForLayoutPosition(position);
        if (itemViewHolder.videoView != null && !itemViewHolder.videoView.isPlaying()) {
            itemViewHolder.videoView.setVideoUrl(adapter.getDatas().get(position).getVideo());
            itemViewHolder.videoView.loop();
            itemViewHolder.videoView.prepareAsync();
        }
    }

    private void releaseVideo(int position) {
        MyAdapter.ItemViewHolder itemViewHolder = (MyAdapter.ItemViewHolder) recyclerView.findViewHolderForLayoutPosition(position);
        if (itemViewHolder != null) {
            itemViewHolder.videoView.stop();
//        itemViewHolder.videoView.reset();
            itemViewHolder.videoView.showCover();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        int snapPosition = layoutManager.findSnapPosition();
        if (snapPosition >= 0) {
            releaseVideo(snapPosition);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int snapPosition = layoutManager.findSnapPosition();
        if (snapPosition >= 0) {
            playVideo(snapPosition);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class MyAdapter extends RecyclerView.Adapter {

        private List<VideoEntity> mDatas = new ArrayList<>();


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(LayoutManagerActivity.this).inflate(R.layout.item_qf_recycler, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.videoView.setTag("position--->" + position);
            itemViewHolder.sdvCover.setImageURI(mDatas.get(position).getCover());
            itemViewHolder.sdvCover.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
            itemViewHolder.sdvCover.getHierarchy().setFadeDuration(0);
            itemViewHolder.videoView.showCover();
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        public void addData(List<VideoEntity> videoList) {
            mDatas.addAll(videoList);
        }

        public List<VideoEntity> getDatas() {
            return mDatas;
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            QfVideoView videoView;
            SimpleDraweeView sdvCover;

            public ItemViewHolder(View itemView) {
                super(itemView);
                videoView = itemView.findViewById(R.id.videoView);
                sdvCover = new SimpleDraweeView(LayoutManagerActivity.this);
                videoView.setCover(sdvCover);
            }
        }

    }
}
