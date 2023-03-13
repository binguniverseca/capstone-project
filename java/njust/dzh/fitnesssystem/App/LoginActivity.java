package njust.dzh.fitnesssystem.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import njust.dzh.fitnesssystem.Activity.MainActivity;
import njust.dzh.fitnesssystem.DataBase.MD5Utils;
import njust.dzh.fitnesssystem.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etAccount;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private CheckBox rememberPass;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        rememberPass = findViewById(R.id.rememberPass);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);

        pref = getSharedPreferences("data", MODE_PRIVATE);
        boolean isRemember = pref.getBoolean("remember", false);

        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");

            etAccount.setText(account);
            etPassword.setText(password);
            rememberPass.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                String acc = etAccount.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();

                String account = pref.getString("account", "");
                String password = pref.getString("password", "");

                if (TextUtils.isEmpty(acc) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "Account ID and password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (acc.equals(account) && (MD5Utils.md5(pass).equals(password) || pass.equals(password))){
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember", true);
                    } else {
                        editor.putBoolean("remember", false);
                    }
                    editor.apply();
                    Toast.makeText(LoginActivity.this, "Log in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Account ID or password not correct", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_register:

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String acc = data.getStringExtra("acc");
                    String pass = data.getStringExtra("pass");

                    etAccount.setText(acc);
                    etPassword.setText(pass);
                }
                break;
            default:
                break;
        }

    }
}