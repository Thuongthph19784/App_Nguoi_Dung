package com.example.nguoidung.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.nguoidung.Fragment.fragmenHoaDon;
import com.example.nguoidung.Fragment.fragmentYeuThich;
import com.example.nguoidung.Fragment.fragmentTrangChu;
import com.example.nguoidung.Fragment.fragmentNguoiDung;
import com.example.nguoidung.R;

import java.io.File;

public class Home extends AppCompatActivity {
    FragmentTransaction transaction;
    AHBottomNavigation bottomNavigation;
    public String tenNguoiDung;
    public int idTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        Intent intent = getIntent();
        tenNguoiDung = intent.getStringExtra("tenNguoiDung");
        idTV = intent.getIntExtra("idTV",-1);
        String[] display = new String[]{
                "Trang chủ",
                "Yêu thích",
                "Hóa đơn",
                "Người dùng"
        };
        initToolBar(display[0]);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_baseline_home_24, R.color.green);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_baseline_favorite_24, R.color.ping);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_baseline_receipt_24, R.color.yello);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_baseline_how_to_reg_24, R.color.blue);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#CDDC39"));

//        bottomNavigation.setColored(true);

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.activity_home_frame_layout, new fragmentTrangChu());
        transaction.addToBackStack(null);
        transaction.commit();

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (position == 0) {
                    initToolBar(display[position]);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.activity_home_frame_layout, new fragmentTrangChu());
                    transaction.addToBackStack(null);
                    transaction.commit();

                } else if (position == 1) {
                    initToolBar(display[position]);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.activity_home_frame_layout, new fragmentYeuThich());
                    transaction.addToBackStack(null);
                    transaction.commit();

                } else if (position == 2) {
                    initToolBar(display[position]);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.activity_home_frame_layout, new fragmenHoaDon());
                    transaction.addToBackStack(null);
                    transaction.commit();


                } else if (position == 3) {
                    initToolBar(display[position]);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.activity_home_frame_layout, new fragmentNguoiDung());
                    transaction.addToBackStack(null);
                    transaction.commit();

                }
                return true;
            }
        });

    }

    private void initToolBar(String s) {
        Toolbar toolbar = findViewById(R.id.activity_home_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(s);

        }
    }
//    public void luuData(){
//        ContextWrapper contextWrapper = new ContextWrapper(
//                getApplicationContext());
////Tạo (Hoặc là mở file nếu nó đã tồn tại) Trong bộ nhớ trong có thư mục là ThuMucCuaToi.
//        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
//        myInternalFile = new File(directory, filename);
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.activity_home_frame_layout);
                if (fragment.toString().indexOf("TrangChu") > 0) {
                    bottomNavigation.setCurrentItem(0);
                } else if (fragment.toString().indexOf("YeuThich") > 0) {
                    bottomNavigation.setCurrentItem(1);
                } else if (fragment.toString().indexOf("HoaDon") > 0) {
                    bottomNavigation.setCurrentItem(2);
                } else if (fragment.toString().indexOf("NguoiDung") > 0) {
                    bottomNavigation.setCurrentItem(3);
                }else{
                    onBackPressed();
                }
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}