package net.archeryc.scrollvideodemo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @author ArcherYc
 * @date on 2018/6/8  上午11:43
 * @mail 247067345@qq.com
 */
public class ScrollableRecyclerView extends RecyclerView implements GestureDetector.OnGestureListener {

    private static final String tag = "ScrollableRecyclerView";

    private GestureDetector gestureDetector;

    private OnLoadMoreListener mLoadMoreListener;

    private float mDownY;

    private final int mMaxScrollY = 400;
    /**
     * negative up ,positive down
     */
    private int mDirection = 0;

    /**
     * prevent RecyclerView  content pull down when RecyclerView is loading more
     */
    private boolean mPreventDown = false;

    /**
     * has once load from ACTION DOWN to UP or CANCEL
     */
    private boolean mIsLoadOnce = false;

    public ScrollableRecyclerView(Context context) {
        this(context, null);
    }

    public ScrollableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        gestureDetector = new GestureDetector(getContext(), this);
    }


    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean consume = gestureDetector.onTouchEvent(e);
        Log.d(tag, "consume--->" + consume);
        if (consume) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownY = e.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mPreventDown = true;

                    float delta = (e.getRawY() - mDownY) * 1f;
                    Log.d(tag, "delta --->" + delta);
                    if (Math.abs(delta) < mMaxScrollY) {
                        setTranslationY(delta);
                    } else {
                        if (!mIsLoadOnce) {
                            if (mLoadMoreListener != null) {
                                mIsLoadOnce = true;
                                mLoadMoreListener.onLoadMore();
                            }
                        }
                    }
                    if (delta >= 0) {
                        setTranslationY(0);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    animate().translationY(0).setDuration(100).start();
                    mPreventDown = false;
                    mIsLoadOnce = false;
                    break;
                default:
                    setTranslationY(0);
                    break;
            }
        } else if (e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_CANCEL) {
            animate().translationY(0).setDuration(100).start();
            mPreventDown = false;
            mIsLoadOnce = false;
        }
        return mPreventDown || consume || super.onTouchEvent(e);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        mDirection = (int) (e2.getRawY() - e1.getRawY());
        if (canScrollVertically(1) || mDirection > 0) {
            Log.d(tag, "onScroll can scroll");
            return !(getTranslationY() == 0);
        } else {
            Log.d(tag, "onScroll");
            return true;
        }
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mLoadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        /**
         * scroll to the bottom of RecyclerView and load more
         */
        void onLoadMore();
    }

}
