package njust.dzh.fitnesssystem.App;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import njust.dzh.fitnesssystem.DataBase.MD5Utils;
import njust.dzh.fitnesssystem.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etAccount, etPassword, etConfirm;
    private Button btnRegister;
    private TextView tvReturn, tvMember;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        etAccount = findViewById(R.id.et_account);
        etPassword = findViewById(R.id.et_password);
        etConfirm = findViewById(R.id.et_confirm);
        btnRegister = findViewById(R.id.btn_register);
        tvReturn = findViewById(R.id.tv_return);
        tvMember = findViewById(R.id.tv_member);
        btnRegister.setOnClickListener(this);
        tvReturn.setOnClickListener(this);
        tvMember.setOnClickListener(this);
        pref = getSharedPreferences("data",MODE_PRIVATE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                String account = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirm = etConfirm.getText().toString().trim();
                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm)) {
                    Toast.makeText(RegisterActivity.this, "Account or password cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirm)) {
                    Toast.makeText(RegisterActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                } else if (account.length() < 6 || password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Account or password should be longer than 6 ", Toast.LENGTH_SHORT).show();
                } else {
                    String mps = MD5Utils.md5(password);
                    editor = pref.edit();
                    editor.putString("account", account);
                    editor.putString("password", mps);
                    editor.apply();

                    Intent intent = new Intent();
                    intent.putExtra("acc", account);
                    intent.putExtra("pass", password);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.tv_return:
                finish();
                break;
            case R.id.tv_member:
                Toast.makeText(this, "You have joined", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}