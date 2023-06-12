package com.example.nguoidung.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nguoidung.Activity.Login;
import com.example.nguoidung.R;



public class fragmentNguoiDung extends Fragment {
    Button btnThongTin, btnDoiMatKhau,btnDangXuat;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_nguoi_dung, container, false);
        btnThongTin = v.findViewById(R.id.fragment_nguoiDung_btn_thongTin);
        btnDangXuat = v.findViewById(R.id.fragment_nguoiDung_btn_dangXuat);
        btnDoiMatKhau = v.findViewById(R.id.fragment_nguoiDung_btn_doiMatKhau);

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.activity_home_frame_layout, new fragmentDoiMatKhau());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Login.class));
            }
        });


        return v;
    }
}