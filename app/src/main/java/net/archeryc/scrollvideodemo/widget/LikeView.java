package net.archeryc.scrollvideodemo.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import net.archeryc.scrollvideodemo.R;

/**
 * @author ArcherYc
 * @date on 2018/6/28  下午8:57
 * @mail 247067345@qq.com
 */
public class LikeView extends View {
    private final static int BG_COLOR = Color.parseColor("#4b8bc7");


    private Paint mInnerCirclePaint;

    private Paint mOuterCirclePaint;

    private Paint mLinePaint;

    private float progress;

    private float mMaxInnerRadius;

    private float mMaxOuterRadius;

    private float mMaxMotherRadius;

    private float mMaxOuterCircleStrokeWidth;

    private float mMaxProgress;

    private float innerRadius;

    private float outerRadius;

    private float motherRadius;


    private float outerCircleStrokeWidth;

    private Bitmap mBitmapLike;

    private Matrix mLikeMatrix;

    private Paint mBitmapPaint;

    private float bitmapScale;

    private float bitmapRotation;


    private Point[] mStartPoints = new Point[6];

    public LikeView(Context context) {
        this(context, null);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mInnerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerCirclePaint.setStyle(Paint.Style.FILL);
        mInnerCirclePaint.setColor(BG_COLOR);

        mOuterCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOuterCirclePaint.setStyle(Paint.Style.STROKE);
        mOuterCirclePaint.setColor(BG_COLOR);
        mOuterCirclePaint.setStrokeWidth(40);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setColor(BG_COLOR);
        mLinePaint.setStrokeWidth(4);

        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBitmapLike = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_like);

        mLikeMatrix = new Matrix();
        mLikeMatrix.postScale(0, 0);

    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public float getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(float innerRadius) {
        this.innerRadius = innerRadius;
    }

    public float getOuterRadius() {
        return outerRadius;
    }

    public void setOuterRadius(float outerRadius) {
        this.outerRadius = outerRadius;
    }

    public float getOuterCircleStrokeWidth() {
        return outerCircleStrokeWidth;
    }

    public void setOuterCircleStrokeWidth(float outerCircleStrokeWidth) {
        mOuterCirclePaint.setStrokeWidth(outerCircleStrokeWidth);
        this.outerCircleStrokeWidth = outerCircleStrokeWidth;
    }

    public float getMotherRadius() {
        return motherRadius;
    }

    public void setMotherRadius(float motherRadius) {
        this.motherRadius = motherRadius;
    }

    public float getBitmapScale() {
        return bitmapScale;
    }

    public void setBitmapScale(float bitmapScale) {
        mLikeMatrix.reset();
        mLikeMatrix.postTranslate(getWidth() / 2 - mBitmapLike.getWidth() / 2,
                getHeight() / 2 - mBitmapLike.getHeight() / 2);
        mLikeMatrix.postScale(bitmapScale, bitmapScale, getWidth() / 2, getWidth() / 2);
        mLikeMatrix.postRotate(bitmapRotation, getWidth() / 2, getWidth() / 2);
        this.bitmapScale = bitmapScale;
    }

    public float getBitmapRotation() {
        return bitmapRotation;
    }

    public void setBitmapRotation(float bitmapRotation) {
        mLikeMatrix.reset();
        mLikeMatrix.postTranslate(getWidth() / 2 - mBitmapLike.getWidth() / 2,
                getHeight() / 2 - mBitmapLike.getHeight() / 2);
        mLikeMatrix.postScale(bitmapScale, bitmapScale, getWidth() / 2, getWidth() / 2);
        mLikeMatrix.postRotate(bitmapRotation, getWidth() / 2, getWidth() / 2);
        this.bitmapRotation = bitmapRotation;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (motherRadius < mMaxMotherRadius) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, motherRadius, mInnerCirclePaint);
        }
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, innerRadius, mInnerCirclePaint);
        if (outerRadius < mMaxOuterRadius) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, outerRadius, mOuterCirclePaint);
        }

        if (mStartPoints.length != 0 && progress > 0) {
            if (progress < mMaxProgress / 2) {
                if (mStartPoints[0] != null) {
                    canvas.drawLine(mStartPoints[0].x, mStartPoints[0].y,
                            mStartPoints[0].x, mStartPoints[0].y - progress,
                            mLinePaint);
                    canvas.drawLine(mStartPoints[1].x, mStartPoints[1].y,
                            (float) (mStartPoints[1].x + progress * 0.5f * Math.sqrt(3)),
                            mStartPoints[1].y - progress * 0.5f,
                            mLinePaint);
                    canvas.drawLine(mStartPoints[2].x, mStartPoints[2].y,
                            (float) (mStartPoints[2].x + progress * 0.5f * Math.sqrt(3)),
                            mStartPoints[2].y + progress * 0.5f,
                            mLinePaint);
                    canvas.drawLine(mStartPoints[3].x, mStartPoints[3].y,
                            mStartPoints[3].x, mStartPoints[3].y + progress,
                            mLinePaint);
                    canvas.drawLine(mStartPoints[4].x, mStartPoints[4].y,
                            (float) (mStartPoints[4].x - progress * 0.5f * Math.sqrt(3)),
                            mStartPoints[4].y + progress * 0.5f,
                            mLinePaint);
                    canvas.drawLine(mStartPoints[5].x, mStartPoints[5].y,
                            (float) (mStartPoints[5].x - progress * 0.5f * Math.sqrt(3)),
                            mStartPoints[5].y - progress * 0.5f,
                            mLinePaint);
                }
            } else {
                if (mStartPoints[0] != null) {
                    canvas.drawLine(mStartPoints[0].x, mStartPoints[0].y - 2 * (progress - mMaxProgress / 2),
                            mStartPoints[0].x, mStartPoints[0].y - progress, mLinePaint);
                    canvas.drawLine((float) (mStartPoints[1].x + 2 * (progress - mMaxProgress / 2) * 0.5f * Math.sqrt(3)),
                            mStartPoints[1].y - 2 * (progress - mMaxProgress / 2) * 0.5f,
                            (float) (mStartPoints[1].x + progress * 0.5f * Math.sqrt(3)),
                            mStartPoints[1].y - progress * 0.5f, mLinePaint);
                    canvas.drawLine((float) (mStartPoints[2].x + 2 * (progress - mMaxProgress / 2) * 0.5f * Math.sqrt(3)),
                            mStartPoints[2].y + 2 * (progress - mMaxProgress / 2) * 0.5f,
                            (float) (mStartPoints[2].x + progress * 0.5f * Math.sqrt(3)),
                            mStartPoints[2].y + progress * 0.5f, mLinePaint);
                    canvas.drawLine(mStartPoints[3].x, mStartPoints[3].y + 2 * (progress - mMaxProgress / 2),
                            mStartPoints[3].x, mStartPoints[3].y + progress, mLinePaint);
                    canvas.drawLine((float) (mStartPoints[4].x - 2 * (progress - mMaxProgress / 2) * 0.5f * Math.sqrt(3)),
                            mStartPoints[4].y + 2 * (progress - mMaxProgress / 2) * 0.5f,
                            (float) (mStartPoints[4].x - progress * 0.5f * Math.sqrt(3)),
                            mStartPoints[4].y + progress * 0.5f, mLinePaint);
                    canvas.drawLine((float) (mStartPoints[5].x - 2 * (progress - mMaxProgress / 2) * 0.5f * Math.sqrt(3)),
                            mStartPoints[5].y - 2 * (progress - mMaxProgress / 2) * 0.5f,
                            (float) (mStartPoints[5].x - progress * 0.5f * Math.sqrt(3)),
                            mStartPoints[5].y - progress * 0.5f, mLinePaint);
                }
            }
        }
        canvas.drawBitmap(mBitmapLike, mLikeMatrix, mBitmapPaint);
    }

    public void start() {
        int width = getWidth();
        int height = getHeight();
        mMaxMotherRadius = width <= height ? width * 0.5f * 0.5f : height * 0.5f * 0.5f;
        mMaxInnerRadius = width <= height ? width * 0.5f * 0.4f : height * 0.5f * 0.4f;
        mMaxOuterRadius = width <= height ? width * 0.5f * 0.6f : height * 0.5f * 0.6f;
        mMaxOuterCircleStrokeWidth = mMaxMotherRadius - mMaxInnerRadius;
        mMaxProgress = width <= height ? width * 0.5f * 0.4f : height * 0.5f * 0.4f;

        mStartPoints[0] = new Point((int) (0.5f * getWidth()), (int) (0.5f * getHeight() - mMaxMotherRadius));
        mStartPoints[1] = new Point((int) (0.5f * getWidth() + mMaxMotherRadius * 0.5f * Math.sqrt(3)),
                (int) (0.5f * getHeight() - mMaxMotherRadius * 0.5f));
        mStartPoints[2] = new Point((int) (0.5f * getWidth() + mMaxMotherRadius * 0.5f * Math.sqrt(3)),
                (int) (0.5f * getHeight() + mMaxMotherRadius * 0.5f));
        mStartPoints[3] = new Point((int) (0.5f * getWidth()), (int) (0.5f * getHeight() + mMaxMotherRadius));
        mStartPoints[4] = new Point((int) (0.5f * getWidth() - mMaxMotherRadius * 0.5f * Math.sqrt(3)),
                (int) (0.5f * getHeight() + mMaxMotherRadius * 0.5f));
        mStartPoints[5] = new Point((int) (0.5f * getWidth() - mMaxMotherRadius * 0.5f * Math.sqrt(3)),
                (int) (0.5f * getHeight() - mMaxMotherRadius * 0.5f));


        ObjectAnimator motherCircleAnimator = ObjectAnimator.ofFloat(this, "motherRadius",
                0, mMaxMotherRadius)
                .setDuration(300);
        motherCircleAnimator.setInterpolator(new LinearInterpolator());
        motherCircleAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator innerCircleAnimator = ObjectAnimator.ofFloat(LikeView.this, "innerRadius",
                        mMaxInnerRadius, mMaxInnerRadius * 0.6f)
                        .setDuration(200);
                innerCircleAnimator.setInterpolator(new LinearInterpolator());

                ObjectAnimator innerBiggerCircleAnimator = ObjectAnimator.ofFloat(LikeView.this, "innerRadius",
                        mMaxInnerRadius * 0.6f, mMaxInnerRadius * 0.65f)
                        .setDuration(300);
                innerBiggerCircleAnimator.setInterpolator(new LinearInterpolator());
                AnimatorSet innerAnimatorSet = new AnimatorSet();
                innerAnimatorSet.playSequentially(innerCircleAnimator,innerBiggerCircleAnimator);
                innerAnimatorSet.start();

                ObjectAnimator outerAnimator = ObjectAnimator.ofFloat(LikeView.this,
                        "outerRadius", mMaxInnerRadius + mMaxOuterCircleStrokeWidth * 0.5f, mMaxOuterRadius)
                        .setDuration(200);
                outerAnimator.setInterpolator(new LinearInterpolator());
                outerAnimator.start();

                ObjectAnimator strokeAnimator = ObjectAnimator.ofFloat(LikeView.this, "progress",
                        0f, mMaxProgress)
                        .setDuration(200);
                strokeAnimator.setInterpolator(new LinearInterpolator());
                strokeAnimator.start();

                ObjectAnimator outerStrokeAnimator = ObjectAnimator.ofFloat(LikeView.this,
                        "outerCircleStrokeWidth", mMaxOuterCircleStrokeWidth, 0)
                        .setDuration(200);
                strokeAnimator.setInterpolator(new LinearInterpolator());
                outerStrokeAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        motherCircleAnimator.start();
        ObjectAnimator bitmapScaleAnimator = ObjectAnimator.ofFloat(LikeView.this,
                "bitmapScale", 0f, mMaxInnerRadius * 0.6f * 0.6f / (mBitmapLike.getWidth() / 2f))
                .setDuration(200);
        bitmapScaleAnimator.start();

        setBitmapRotation(10);
        ObjectAnimator bitmapRotationRightAnimator = ObjectAnimator.ofFloat(LikeView.this,
                "bitmapRotation", 10, 20);
        bitmapRotationRightAnimator.setDuration(280);
        bitmapRotationRightAnimator.setInterpolator(new LinearInterpolator());
        ObjectAnimator bitmapRotationLeftAnimator = ObjectAnimator.ofFloat(LikeView.this,
                "bitmapRotation", 20, -6);
        bitmapRotationLeftAnimator.setDuration(220);
        bitmapRotationLeftAnimator.setInterpolator(new LinearInterpolator());

        ObjectAnimator bitmapRotationCenterAnimator = ObjectAnimator.ofFloat(LikeView.this,
                "bitmapRotation", -15, 0);
        bitmapRotationCenterAnimator.setDuration(300);
        AnimatorSet bitmapAnimatorSet = new AnimatorSet();
        bitmapAnimatorSet.playSequentially(bitmapRotationRightAnimator,
                bitmapRotationLeftAnimator, bitmapRotationCenterAnimator);
        bitmapAnimatorSet.start();

    }

}
