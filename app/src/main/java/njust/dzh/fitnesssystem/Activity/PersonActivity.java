package njust.dzh.fitnesssystem.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;

import java.io.IOException;

import njust.dzh.fitnesssystem.Bean.Aphorism;
import njust.dzh.fitnesssystem.Http.HttpUtil;
import njust.dzh.fitnesssystem.R;
import njust.dzh.fitnesssystem.Http.URLUtils;
import njust.dzh.fitnesssystem.Bean.User;
import njust.dzh.fitnesssystem.DataBase.UserDao;
import okhttp3.Call;
import okhttp3.Response;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener{
    // 声明变量
    private EditText etName, etAge, etGender, etNation, etHeight, etWeight;
    private Button btnModify, btnConfirm;
    private UserDao userDao;
    private User user;
    private TextView tvSaying;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        initView();
    }

    private void initView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getInstance();

        HttpUtil.sendOkHttpRequest(URLUtils.index_url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                return;
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                parseShowData(result);
            }
        });


        userDao = new UserDao(this);
        userDao.open();
        User user = userDao.getInformation("8888abc");
        if (user != null) {
            etName.setText(user.getName());
            etAge.setText(user.getAge());
            etGender.setText(user.getGender());
            etNation.setText(user.getNation());
            etHeight.setText(user.getHeight());
            etWeight.setText(user.getWeight());
        }
        userDao.close();
    }


    private void parseShowData(String result) {

        Aphorism aphorism = new Gson().fromJson(result, Aphorism.class);
        Aphorism.NewslistDTO newslistDTO = aphorism.getNewslist().get(0);
        String saying = newslistDTO.getSaying();
        String transl = newslistDTO.getTransl();
        String source = newslistDTO.getSource();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tvSaying.setText(saying + "————" + source);
            }
        });

    }


    private void getInstance() {

        etName = findViewById(R.id.et_name);
        etAge = findViewById(R.id.et_age);
        etGender = findViewById(R.id.et_gender);
        etNation = findViewById(R.id.et_nation);
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        tvSaying = findViewById(R.id.tv_saying);

        btnModify = findViewById(R.id.btn_modify);
        btnConfirm = findViewById(R.id.btn_confirm);
        btnModify.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);

        etName.setEnabled(false);
        etAge.setEnabled(false);
        etGender.setEnabled(false);
        etNation.setEnabled(false);
        etHeight.setEnabled(false);
        etWeight.setEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_modify:
                modify();
                break;
            case R.id.btn_confirm:
                confirm();
                break;
            default:
                break;
        }
    }


    private void confirm() {

        etName.setEnabled(false);
        etAge.setEnabled(false);
        etGender.setEnabled(false);
        etNation.setEnabled(false);
        etHeight.setEnabled(false);
        etWeight.setEnabled(false);

        userDao.open();
        String name = etName.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String gender = etGender.getText().toString().trim();
        String nation = etNation.getText().toString().trim();
        String height = etHeight.getText().toString().trim();
        String weight = etWeight.getText().toString().trim();
        user = new User("8888abc", name, age, gender, nation, height, weight);


        userDao.modifyUser(user);
        userDao.close();


        btnConfirm.setVisibility(View.INVISIBLE);
        btnModify.setVisibility(View.VISIBLE);
    }


    private void modify() {

        etName.setEnabled(true);
        etAge.setEnabled(true);
        etGender.setEnabled(true);
        etNation.setEnabled(true);
        etHeight.setEnabled(true);
        etWeight.setEnabled(true);

        btnModify.setVisibility(View.INVISIBLE);
        btnConfirm.setVisibility(View.VISIBLE);
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