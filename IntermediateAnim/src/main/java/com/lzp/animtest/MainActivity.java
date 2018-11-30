package com.lzp.animtest;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.lzp.animtest.two.Circle2ProgressBar;

public class MainActivity extends AppCompatActivity {

    private CircleProgressBar circleProgressBar;
    private ChangeShapeAndColorButton changeShapeAndColorButton;
    private Circle2ProgressBar progressBar2;
    private Button btnTwo;
    private TextView tvHint;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleProgressBar = (CircleProgressBar) findViewById(R.id.circle_progress);
//        circleProgressBar.setIntermediateMode(true);

        changeShapeAndColorButton = (ChangeShapeAndColorButton) findViewById(R.id.change_shape_and_color_btn);
        changeShapeAndColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                changeShapeAndColorButton.setStatus(count++ % 2);
                startAnim();
            }
        });
        progressBar2 = (Circle2ProgressBar) findViewById(R.id.circleProgressBar);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        tvHint = (TextView) findViewById(R.id.tvHint);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTwo();
            }
        });
    }
    int progress = 0;
    private void startTwo(){
//        progressBar2.animate()
//                .translationX(500)
//                .setDuration(500);

        ObjectAnimator animator = ObjectAnimator.ofFloat(
                progressBar2, "translationX", 400);
        animator.setDuration(300);
        animator.start();
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 0.0f, 1.0f, 1.0f,Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
//        ScaleAnimation scaleAnimation = new ScaleAnimation()
                scaleAnimation.setDuration(300);
        tvHint.startAnimation(scaleAnimation);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(30);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    progress++;
//                    progressBar2.update(progress);
//                    if (progress == 360) {
//                        progress = 0;
//                    }
//                }
//            }
//        }).start();
    }

    /**
     * 启动一个动画
     */
    private void startAnim() {
        ValueAnimator valueAnimator = new ValueAnimator().ofInt(0, 360);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int progress = (int) valueAnimator.getAnimatedValue();
//                circleProgressBar.setProgress(progress);
                circleProgressBar.setProgress(progress*100/360);
                progressBar2.update(progress);
            }

        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                int color = circleProgressBar.getProgressBackgroundColor();
                circleProgressBar.setProgressBackgroundColor(circleProgressBar.getProgressColor());
                circleProgressBar.setProgressColor(color);
            }
        });

//        valueAnimator.setRepeatCount(1);
        valueAnimator.start();

    }

}
