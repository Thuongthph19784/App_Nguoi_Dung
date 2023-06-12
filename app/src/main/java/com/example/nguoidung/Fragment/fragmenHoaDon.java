package com.example.nguoidung.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguoidung.Adapter.HoaDonAdapter;
import com.example.nguoidung.Adapter.YeuThichAdapter;
import com.example.nguoidung.Dao.HoaDonDao;
import com.example.nguoidung.Object.HoaDon;
import com.example.nguoidung.R;

import java.util.List;


public class fragmenHoaDon extends Fragment {
    View view;
    Context context;
    RecyclerView recyclerView;
    HoaDonDao dao;
    HoaDonAdapter adapter;
    List<HoaDon> ls;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragmen_hoa_don, container, false);
        context = view.getContext();
        recyclerView = view.findViewById(R.id.fragment_hoa_don_rcv);
        loadData();








        return view;
    }
    public void loadData(){
        SharedPreferences pref = context.getSharedPreferences("ID_FILE", Context.MODE_PRIVATE);
        int idTV = pref.getInt("idTV",-1);
        LinearLayoutManager linearLayout =new LinearLayoutManager(context);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        dao = new HoaDonDao();
        ls = dao.getAll(idTV);
        if (ls.isEmpty()){

        }else{
            adapter = new HoaDonAdapter(ls,context);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
}