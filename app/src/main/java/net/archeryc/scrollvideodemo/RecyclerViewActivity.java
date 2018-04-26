package net.archeryc.scrollvideodemo;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnSeekCompleteListener;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private String CACHE_DIR = Environment.getExternalStorageDirectory().getPath() + File.separator + "qf_cache" + File.separator;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        Fresco.initialize(this);
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
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    List<MyAdapter.ItemViewHolder> viewHolders = adapter.getHolders();

//                    for (PLVideoTextureView videoView : holders) {
//                        int[] location = new int[2];
//                        videoView.getLocationInWindow(location);
//
//                    }
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    if (firstVisibleItemPosition == lastVisibleItemPosition) {
                        for (final MyAdapter.ItemViewHolder holder : viewHolders) {
                            if (holder.equals(recyclerView.findViewHolderForAdapterPosition(firstVisibleItemPosition))) {
                                holder.videoView.setOnSeekCompleteListener(null);
                                if (!holder.videoView.isPlaying()) {
                                    holder.videoView.start();
                                }
                            } else {
                                if (holder.videoView.isPlaying()) {
                                    holder.videoView.seekTo(0);
                                    holder.videoView.setOnSeekCompleteListener(new PLOnSeekCompleteListener() {
                                        @Override
                                        public void onSeekComplete() {
                                            if (holder.videoView.isPlaying()) {
                                                holder.videoView.pause();
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }

                    Log.d("position", "first---->" + firstVisibleItemPosition + "last---->" + lastVisibleItemPosition);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.clean();
    }


    class MyAdapter extends RecyclerView.Adapter {

        List<ItemViewHolder> holders = new ArrayList<>();

        AVOptions options = new AVOptions();

        public MyAdapter() {
            File file = new File(CACHE_DIR);
            if (!file.exists()) {
                boolean success=file.mkdir();
                Log.d("file","创建文件--->"+success);
            }
//            options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_AUTO);
            options.setInteger(AVOptions.KEY_FAST_OPEN, 1);
            options.setInteger(AVOptions.KEY_PREFER_FORMAT, 2);
            options.setString(AVOptions.KEY_CACHE_DIR, CACHE_DIR);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.item_recycler, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            if (itemViewHolder.videoView.isPlaying()) {
                itemViewHolder.videoView.stopPlayback();
            }
            itemViewHolder.videoView.setVideoPath(videoList[position].getVideo());
            itemViewHolder.videoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);
            itemViewHolder.videoView.setAVOptions(options);
//            itemViewHolder.videoView.setLooping(true);
            itemViewHolder.videoView.start();
            holders.add(itemViewHolder);
        }

        @Override
        public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
            super.onViewRecycled(holder);
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.videoView.stopPlayback();
            holders.remove(itemViewHolder);
        }

        @Override
        public int getItemCount() {
            return videoList.length;
        }

        public void clean() {
            for (ItemViewHolder viewHolder : holders) {
                if (viewHolder.videoView.isPlaying()) {
                    viewHolder.videoView.stopPlayback();
                }
            }
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            PLVideoTextureView videoView;

            public ItemViewHolder(View itemView) {
                super(itemView);
                videoView = itemView.findViewById(R.id.videoView);
            }
        }

        public List<ItemViewHolder> getHolders() {
            return holders;
        }
    }

}
