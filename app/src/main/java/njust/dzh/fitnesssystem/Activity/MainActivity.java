package njust.dzh.fitnesssystem.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import njust.dzh.fitnesssystem.Adapter.PagerAdapter;
import njust.dzh.fitnesssystem.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private LinearLayout pointLayout;
    private TextView personData, sportEvent;
    private int[] imgIds = {R.drawable.page1, R.drawable.page2, R.drawable.page3};

    List<ImageView> ivList;

    List<ImageView> pointList;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {

                int currentItem = viewPager.getCurrentItem();

                if (currentItem == ivList.size() - 1) {
                    viewPager.setCurrentItem(0);
                } else {
                    viewPager.setCurrentItem(++currentItem);
                }
                handler.sendEmptyMessageDelayed(1, 3000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        personData = findViewById(R.id.person_data);
        sportEvent = findViewById(R.id.sport_event);
        personData.setOnClickListener(this);
        sportEvent.setOnClickListener(this);
        viewPager = findViewById(R.id.viewpager);
        pointLayout = findViewById(R.id.point_layout);
        initPager();
        setVPListener();
        handler.sendEmptyMessageDelayed(1, 3000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.person_data:
                Intent intent = new Intent(MainActivity.this, PersonActivity.class);
                startActivity(intent);
                break;
            case R.id.sport_event:
                Intent intent2 = new Intent(MainActivity.this, SportActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }


    private void setVPListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < pointList.size(); i++) {
                    pointList.get(i).setImageResource(R.drawable.point_normal);
                }
                pointList.get(position).setImageResource(R.drawable.point_focus);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPager() {
        ivList = new ArrayList<>();
        pointList = new ArrayList<>();
        for (int i = 0; i < imgIds.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(imgIds[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);

            ivList.add(iv);

            ImageView piv = new ImageView(this);
            piv.setImageResource(R.drawable.point_normal);
            LinearLayout.LayoutParams plp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            plp.setMargins(20, 0, 0, 0);
            piv.setLayoutParams(plp);

            pointLayout.addView(piv);

            pointList.add(piv);
        }
        pointList.get(0).setImageResource(R.drawable.point_focus);
        pagerAdapter = new PagerAdapter(this, ivList);
        viewPager.setAdapter(pagerAdapter);
    }


}