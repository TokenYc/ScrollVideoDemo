package net.archeryc.scrollvideodemo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
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

    private float mDownY;
    private float mLastDelta;
    /**
     * negative up ,positive down
     */
    private int mDirection = 0;

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
        if (consume) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownY = e.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float delta = e.getY() - mDownY;
                    if (mDirection < 0) {
                        if (Math.abs(delta) > Math.abs(mLastDelta)) {
                            setTranslationY(delta);
                            mLastDelta = delta;
                        }
                    } else {
                        if (Math.abs(delta) < Math.abs(mLastDelta)) {
                            setTranslationY(delta);
                            Log.d(tag, "down delta---->" + delta);
                            mLastDelta = delta;
                        }
                    }
                    Log.d(tag, "delta---->" + delta);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    animate().translationY(0).setDuration(100).start();
                    mLastDelta = 0;
                    break;
                default:
                    setTranslationY(0);
                    mLastDelta = 0;
                    break;
            }
        } else if (e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_CANCEL) {
            animate().translationY(0).setDuration(100).start();
            mLastDelta = 0;
        }
        return consume || super.onTouchEvent(e);
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
        mDirection = (int) (e2.getY() - e1.getY());
        if (canScrollVertically(1) || e1.getY() - e2.getY() < 0) {
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
        if (canScrollVertically(1) || e1.getY() - e2.getY() < 0) {
            return !(getTranslationY() == 0);
        } else {
            Log.d(tag, "onFling");
            return true;
        }
    }

}
