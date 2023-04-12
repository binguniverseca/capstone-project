package njust.dzh.fitnesssystem.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import njust.dzh.fitnesssystem.R;
import njust.dzh.fitnesssystem.Bean.Sport;

public class ExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView videoView;
    private MediaController controller;
    private ImageView sportImage;
    private Button btnBegin, btnPause, btnContinue, btnStop;
    private TextView tvTime;
    private int second = 0;
    private Sport sport;
    private static final int BEGIN_EXERCISE = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BEGIN_EXERCISE:
                    Message message = new Message();
                    message.what = BEGIN_EXERCISE;
                    second++;
                    int min = second / 60;
                    int sec = second % 60;
                    String str = Integer.toString(min) + ":" + Integer.toString(sec);
                    tvTime.setText(str);

                    handler.sendMessageDelayed(message, 1000);
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initView();
    }

    private void initView() {
        sport = (Sport) getIntent().getSerializableExtra(SportActivity.DATA);
        getInstance();

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.exercise);
        videoView.setVideoURI(uri);
        controller = new MediaController(this);
        videoView.setMediaController(controller);

        if (!videoView.isPlaying()) {
            videoView.start();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_begin:
                btnBegin.setVisibility(View.INVISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                btnContinue.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Message message = new Message();
                        message.what = BEGIN_EXERCISE;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.btn_pause:
                btnPause.setVisibility(View.INVISIBLE);
                btnContinue.setVisibility(View.VISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.removeMessages(BEGIN_EXERCISE);
                    }
                }).start();
                break;
            case R.id.btn_continue:
                btnPause.setVisibility(View.VISIBLE);
                btnContinue.setVisibility(View.INVISIBLE);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Message message = new Message();
                        message.what = BEGIN_EXERCISE;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            case R.id.btn_stop:
                btnStop.setVisibility(View.INVISIBLE);
                btnBegin.setVisibility(View.VISIBLE);
                handler.removeMessages(BEGIN_EXERCISE);
                second = 0;
                tvTime.setText("0:0");
                break;
            default:
                break;
        }
    }

    private void getInstance() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitle(sport.getName());

        videoView = findViewById(R.id.videoview);
        sportImage = findViewById(R.id.sport_image);
        btnBegin = findViewById(R.id.btn_begin);
        btnPause = findViewById(R.id.btn_pause);
        btnContinue = findViewById(R.id.btn_continue);
        btnStop = findViewById(R.id.btn_stop);
        tvTime = findViewById(R.id.tv_time);

        Glide.with(this).load(sport.getImageId()).into(sportImage);

        btnBegin.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}