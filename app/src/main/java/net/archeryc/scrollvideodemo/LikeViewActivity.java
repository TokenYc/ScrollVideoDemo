package net.archeryc.scrollvideodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.archeryc.scrollvideodemo.widget.LikeView;

public class LikeViewActivity extends AppCompatActivity {

    private LikeView likeView;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like_view);
        likeView = findViewById(R.id.likeView);
        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeView.start();
            }
        });
    }
}
