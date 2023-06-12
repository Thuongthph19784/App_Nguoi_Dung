package com.example.nguoidung.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nguoidung.Dao.ThanhVienDao;
import com.example.nguoidung.Object.ThanhVien;
import com.example.nguoidung.R;
import com.example.nguoidung.SQLsever.SQLsever;

import java.sql.Connection;
import java.util.List;

public class SignIn extends AppCompatActivity {
    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 700;
    public static final int EDITTEXT_DELAY = 250;
    public static final int BUTTON_DELAY = 250;
    public static final int VIEW_DELAY = 300;

    Context context;
    Button btnTroLai,btnDangKy;
    EditText edtTenNguoiDung,edtTaiKhoan,edtMatKhau;
    ThanhVienDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        btnTroLai = findViewById(R.id.SignIn_btn_troLai);
        btnDangKy = findViewById(R.id.SignIn_btn_dangKy);
        edtMatKhau = findViewById(R.id.SignIn_matKhau);
        edtTaiKhoan = findViewById(R.id.SignIn_taiKhoan);
        edtTenNguoiDung = findViewById(R.id.SignIn_tenNguoiDung);
        dao = new ThanhVienDao();
        context = this;
        ///////////////////////
        ImageView logoImageView = findViewById(R.id.SignIn_logo);
        ViewGroup container = findViewById(R.id.SignIn);

        //Code for app logo animation
        ViewCompat.animate(logoImageView)
                .translationY(-250)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                        new DecelerateInterpolator(1.0f)).start();

        //Here we are setting animation on displaying content
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            ViewPropertyAnimatorCompat viewAnimator;

            if (v instanceof EditText) {
                viewAnimator = ViewCompat.animate(v)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((EDITTEXT_DELAY * i) + 500)
                        .setDuration(500);
            } else if (v instanceof Button) {
                viewAnimator = ViewCompat.animate(v)
                        .scaleY(1).scaleX(1)
                        .setStartDelay((BUTTON_DELAY * i) + 500)
                        .setDuration(500);
            } else {
                viewAnimator = ViewCompat.animate(v)
                        .translationY(50).alpha(1)
                        .setStartDelay((VIEW_DELAY * i) + 500)
                        .setDuration(1000);
            }
            viewAnimator.setInterpolator(new DecelerateInterpolator()).start();
        }
        //////////////////////
        btnTroLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,Login.class));
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtTaiKhoan.getText().toString();
                String passWord = edtMatKhau.getText().toString();
                String tenNguoiDung = edtTenNguoiDung.getText().toString();
                if (checkEditText(userName,passWord,tenNguoiDung)){
                    if (checkUserName(userName)){
                        ThanhVien tv = new ThanhVien();
                        tv.setUserName(userName);
                        tv.setPassWord(passWord);
                        tv.setTenNguoiDung(tenNguoiDung);
                        dao.insertRow(tv);
                        Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,Login.class);
                        intent.putExtra("UserName",userName);
                        intent.putExtra("PassWord",passWord);
                        startActivity(intent);
                    }

                }
            }
        });
    }
    public boolean checkEditText(String tk,String mk, String tenND){
        if (tk.isEmpty() || mk.isEmpty() || tenND.isEmpty()){
            Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
    public boolean checkUserName(String user){
        List<ThanhVien> ls = dao.getAll();
        for (ThanhVien tv: ls) {
            if (user.equals(tv.getUserName())){
                Toast.makeText(context, "Tên tài khoản này đã có người sử dụng", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}