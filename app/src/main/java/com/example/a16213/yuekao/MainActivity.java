package com.example.a16213.yuekao;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a16213.yuekao.Fragment.fragment1.Fragment1;
import com.example.a16213.yuekao.Fragment.fragment1.Fragment2;
import com.example.a16213.yuekao.Fragment.fragment1.Fragment3;
import com.example.a16213.yuekao.Fragment.fragment1.Fragment4;
import com.example.a16213.yuekao.Fragment.fragment1.Fragment5;
import com.example.a16213.yuekao.Login.LoginActivity;
import com.example.a16213.yuekao.Login.LoginService;
import com.example.a16213.yuekao.Login.logout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapter adapter;
    private NavigationView navigation;
    List <Fragment> list = new ArrayList <>();
    List <RadioButton> rlist = new ArrayList <>();
    private ViewPager vp;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private RadioGroup rg;
    private ImageView img;
    private DrawerLayout draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                rlist.get(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb1.getId()) {
                    vp.setCurrentItem(0);
                }
                if (checkedId == rb2.getId()) {
                    vp.setCurrentItem(1);
                }
                if (checkedId == rb3.getId()) {
                    vp.setCurrentItem(2);
                }
                if (checkedId == rb4.getId()) {
                    vp.setCurrentItem(3);
                }
                if (checkedId == rb5.getId()) {
                    vp.setCurrentItem(4);
                }
            }
        });
        vp.setCurrentItem(0);
        rb1.setChecked(true);

    }

    private void initView() {
        navigation = (NavigationView) findViewById(R.id.navigation);
        View headerView = navigation.getHeaderView(0);
        SimpleDraweeView simpleDraweeView = headerView.findViewById(R.id.img);
        simpleDraweeView.setImageResource(R.drawable.tt_ic_launcher);
        vp = (ViewPager) findViewById(R.id.vp);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        rb5 = (RadioButton) findViewById(R.id.rb5);
        list.add(new Fragment1());
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());
        list.add(new Fragment5());
        rlist.add(rb1);
        rlist.add(rb2);
        rlist.add(rb3);
        rlist.add(rb4);
        rlist.add(rb5);
        Menu menu = navigation.getMenu();
        MenuItem item = menu.getItem(5);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.wanandroid.com/user/logout/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                LoginService service = retrofit.create(LoginService.class);
                Call <logout> call = service.getlogout();
                call.enqueue(new Callback <logout>() {
                    @Override
                    public void onResponse(Call <logout> call, Response <logout> response) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call <logout> call, Throwable t) {

                    }
                });

                return true;
            }
        });
        rg = (RadioGroup) findViewById(R.id.rg);
        img = (ImageView) findViewById(R.id.img);
        draw = (DrawerLayout) findViewById(R.id.draw);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (draw.isDrawerOpen(navigation))
                draw.openDrawer(navigation);
            }
        });

    }
}
