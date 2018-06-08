package net.archeryc.scrollvideodemo.widget.QfVideo;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;

import com.danikula.videocache.HttpProxyCacheServer;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * @author ArcherYc
 * @date on 2018/4/24  下午3:14
 * @mail 247067345@qq.com
 */
public class QfVideoView extends FrameLayout implements TextureView.SurfaceTextureListener,
        IMediaPlayer.OnPreparedListener, IMediaPlayer.OnInfoListener {

    private final String tag = "QfVideoView";
    private Context mContext;
    private TextureView mTextureView;
    private IjkMediaPlayer mMediaPlayer;
    private HttpProxyCacheServer mProxyCacheServer;
    private View mViewCover;
    private Surface mSurface;


    public QfVideoView(@NonNull Context context) {
        this(context, null);
    }

    public QfVideoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        mTextureView = new TextureView(mContext);
        mTextureView.setSurfaceTextureListener(this);
        mMediaPlayer = new IjkMediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnInfoListener(this);
        mProxyCacheServer = new HttpProxyCacheServer(mContext);
        addView(mTextureView);
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        Log.d(tag, "surface state------>onSurfaceTextureAvailable:" + getTag());
        mSurface = new Surface(surfaceTexture);
        mMediaPlayer.setSurface(mSurface);
        adjustSize();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        Log.d(tag, "surface state------>onSurfaceTextureDestroyed:" + getTag());
        mSurface = null;
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        Log.d(tag, "prepared----->" + getTag());
        adjustSize();
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
        Log.d(tag, "onInfo i----->" + i + "\t" + getTag());

        if (i == IMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED) {
            //这里返回了视频旋转的角度，根据角度旋转视频到正确的画面
            if (mTextureView != null) {
                mTextureView.setRotation(i1);
            }
        } else if (i == IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
            mViewCover.animate().alpha(0).setDuration(200).start();
        }
        return false;
    }


    public void setVideoUrl(String url) {
        try {
            mMediaPlayer.setDataSource(mProxyCacheServer.getProxyUrl(url));
        } catch (Exception e) {
            e.printStackTrace();
            reset();
            try {
                mMediaPlayer.setDataSource(mProxyCacheServer.getProxyUrl(url));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void setCover(View view) {
        this.mViewCover = view;
        addView(view);
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        lp.gravity = Gravity.CENTER;
//        mViewCover.setVisibility(GONE);
    }

    public void loop() {
        mMediaPlayer.setLooping(true);
    }

    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    /**
     * prepare后在回调中调用start开始播放
     */
    public void prepareAsync() {
        if (mSurface != null) {
            mMediaPlayer.setSurface(mSurface);
        }
        mMediaPlayer.prepareAsync();
    }


    public void start() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }
    }

    public void pause() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public void showCover() {
        if (mViewCover != null) {
            mViewCover.animate().setDuration(200).alpha(1).start();
        }
    }

    public void pauseWithCover() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
        showCover();
    }

    public void stop() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    public void release() {
        mMediaPlayer.release();
    }

    public void reset() {
        mMediaPlayer.reset();
    }


    public IjkMediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    private void adjustSize() {
        LayoutParams lp = (LayoutParams) mTextureView.getLayoutParams();
        if (getMeasuredWidth() > 0 && getMeasuredHeight() > 0
                && mMediaPlayer.getVideoWidth() > 0 && mMediaPlayer.getVideoHeight() > 0
                && lp.width <= 0 && lp.height <= 0) {
            Pair<Integer, Integer> fitSize = VideoUtils.getFitSize(
                    new Pair<>(getMeasuredWidth(), getMeasuredHeight()),
                    new Pair<>(mMediaPlayer.getVideoWidth(), mMediaPlayer.getVideoHeight())
            );
            lp.gravity = Gravity.CENTER;
            lp.width = fitSize.first;
            lp.height = fitSize.second;
            mTextureView.setLayoutParams(lp);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        reset();
//        release();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        System.out.println("videoView onAttachedToWindow");
    }
}
