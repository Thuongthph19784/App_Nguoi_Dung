package com.example.nguoidung.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguoidung.Dao.ThanhVienDao;
import com.example.nguoidung.Object.ThanhVien;
import com.example.nguoidung.R;

import java.util.List;

public class fragmentDoiMatKhau extends Fragment {
    View view;
    EditText edtMatKhauCu,edtMatKhauMoi1,edtMatKhauMoi2;
    Button btnDoiMK;
    Context context;
    ThanhVienDao dao;
    List<ThanhVien> ls;
    ThanhVien tv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        edtMatKhauCu = view.findViewById(R.id.fragment_doiMatKhau_edt_matKhauCu);
        edtMatKhauMoi1 = view.findViewById(R.id.fragment_doiMatKhau_edt_matKhauMoi);
        edtMatKhauMoi2 = view.findViewById(R.id.fragment_doiMatKhau_edt_nhapLai_matKhauMoi);
        btnDoiMK = view.findViewById(R.id.fragment_doiMatKhau_btn_doiMatKhau);

        context = view.getContext();
        dao = new ThanhVienDao();
        ls = dao.getAll();
        SharedPreferences pref = context.getSharedPreferences("ID_FILE", Context.MODE_PRIVATE);
        int idTV = pref.getInt("idTV",-1);
        for (ThanhVien tv:ls) {
            if (tv.getIdTV() == idTV){
                this.tv = tv;
            }
        }


        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passCu = edtMatKhauCu.getText().toString();
                String passMoi1 = edtMatKhauMoi1.getText().toString();
                String passMoi2 = edtMatKhauMoi2.getText().toString();
                if (tv.getPassWord().equals(passCu) && passCu.isEmpty() == false){
                    if (passMoi1.equals(passMoi2) && passMoi1.isEmpty() == false && passMoi2.isEmpty() == false){
                        tv.setPassWord(passMoi2);
                        System.out.println(tv.getIdTV());
                        dao.updateRow(tv);
                        Toast.makeText(context, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edtMatKhauCu.setText("");
                        edtMatKhauMoi1.setText("");
                        edtMatKhauMoi2.setText("");
                    }else{
                        Toast.makeText(context, "Nhập lại mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}