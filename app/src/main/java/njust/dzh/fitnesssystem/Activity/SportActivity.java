package njust.dzh.fitnesssystem.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import njust.dzh.fitnesssystem.Adapter.SportAdapter;
import njust.dzh.fitnesssystem.Bean.Sport;
import njust.dzh.fitnesssystem.R;

public class SportActivity extends AppCompatActivity {
    public static final String DATA = "SportData";
    private Sport[] sports = {new Sport("Ballet", R.drawable.p1),
            new Sport("Pamela", R.drawable.p2),
            new Sport("HIIT 1", R.drawable.p3),
            new Sport("Running", R.drawable.p4),
            new Sport("Arms workout", R.drawable.p5),
            new Sport("HIIT 2", R.drawable.p6),
            new Sport("HIIT 3", R.drawable.p7)
    };
    private List<Sport> sportList = new ArrayList<>();
    private SportAdapter sportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        initView();
    }

    private void initView() {
        initSport();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        sportAdapter = new SportAdapter(sportList);

        recyclerView.setAdapter(sportAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void initSport() {
        sportList.clear();
        Random random = new Random();
        int index = random.nextInt(sports.length);
        for (int i = index; i < sports.length; i++) {
            sportList.add(sports[i]);
        }
        for (int i = 0; i < index; i++) {
            sportList.add(sports[i]);
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