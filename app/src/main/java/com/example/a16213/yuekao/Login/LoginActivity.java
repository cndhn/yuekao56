package com.example.a16213.yuekao.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a16213.yuekao.MainActivity;
import com.example.a16213.yuekao.R;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button dl;
    private Button zc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        dl = (Button) findViewById(R.id.dl);
        zc = (Button) findViewById(R.id.zc);

        dl.setOnClickListener(this);
        zc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dl:
                String dlusername = username.getText().toString().trim();
                String dlpassword = this.password.getText().toString().trim();
                loginRetrofit(dlusername,dlpassword);
                break;
            case R.id.zc:
                String zcusername = username.getText().toString().trim();
                String zcpassword = this.password.getText().toString().trim();
                registerRetrofit(zcusername,zcpassword);
                break;
        }
    }


    public void registerRetrofit(String zcusername, String zcpassword){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/user/")
                .build();
        LoginService service = retrofit.create(LoginService.class);
        Call <ResponseBody> register = service.getRegister(zcusername, zcpassword,zcpassword);
        register.enqueue(new Callback <ResponseBody>() {
            @Override
            public void onResponse(Call <ResponseBody> call, Response<ResponseBody> response) {
                Log.i("我",response.code()+"注册");
                Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call <ResponseBody> call, Throwable t) {

            }
        });
    }


    public void loginRetrofit(String dlusername, String dlpassword){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/user/")
                .build();
        LoginService service = retrofit.create(LoginService.class);
        Call <ResponseBody> login = service.getLogin(dlusername, dlpassword);
        login.enqueue(new Callback <ResponseBody>() {
            @Override
            public void onResponse(Call <ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call <ResponseBody> call, Throwable t) {

            }
        });
    }



}
