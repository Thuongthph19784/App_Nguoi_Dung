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

import com.example.nguoidung.Adapter.DoAnAdapter;
import com.example.nguoidung.Adapter.YeuThichAdapter;
import com.example.nguoidung.Dao.DoAnDAO;
import com.example.nguoidung.Dao.YeuThichDao;
import com.example.nguoidung.Object.YeuThich;
import com.example.nguoidung.R;

import java.util.List;


public class fragmentYeuThich extends Fragment {
    YeuThichDao dao;
    List<YeuThich> ls;
    Context context;
    View v;
    RecyclerView recyclerView;
    YeuThichAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_yeu_thich, container, false);
        context = v.getContext();
        recyclerView = v.findViewById(R.id.fragment_favorite_rcv);
        loadData();


        return v;
    }
    public void loadData(){
        SharedPreferences pref = context.getSharedPreferences("ID_FILE", Context.MODE_PRIVATE);
        int idTV = pref.getInt("idTV",-1);
        LinearLayoutManager linearLayout =new LinearLayoutManager(context);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        dao = new YeuThichDao();
        ls = dao.getAll(idTV);
        if (ls.isEmpty()){

        }else{
            adapter = new YeuThichAdapter(ls,context);
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