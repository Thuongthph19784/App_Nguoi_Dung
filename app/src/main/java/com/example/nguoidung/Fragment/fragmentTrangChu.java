package com.example.nguoidung.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.nguoidung.Adapter.DoAnAdapter;
import com.example.nguoidung.Dao.DoAnDAO;
import com.example.nguoidung.Object.DoAn;
import com.example.nguoidung.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class fragmentTrangChu extends Fragment {
    ImageSlider imageSlider;
    View view;
    RecyclerView rcyViewMonAn,rcyViewDoUong;
    Context context;
    DoAnAdapter adapter;
    DoAnDAO dao;
    List<DoAn> lsMonAn,lsDoUong;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_trang_chu, container, false);
        imageSlider = view.findViewById(R.id.fragment_home_img_slide);
        rcyViewMonAn = view.findViewById(R.id.fragment_home_rcv_mon_an);
        rcyViewDoUong = view.findViewById(R.id.fragment_home_rcv_nuoc_uong);
        context = view.getContext();
        ArrayList<SlideModel> images = new ArrayList<>();
        images.add(new SlideModel(R.drawable.banh_cuon, ScaleTypes.CENTER_CROP));
        images.add(new SlideModel(R.drawable.banh_mi,ScaleTypes.CENTER_CROP));
        images.add(new SlideModel(R.drawable.bun_cha,ScaleTypes.CENTER_CROP));
        images.add(new SlideModel(R.drawable.bun_dau,ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(images);



        return view;
    }
    public void loadData(){
        LinearLayoutManager linearLayout =new LinearLayoutManager(context);
        linearLayout.setOrientation(RecyclerView.HORIZONTAL);
        rcyViewMonAn.setLayoutManager(linearLayout);
        rcyViewDoUong.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        dao = new DoAnDAO();
        lsMonAn = dao.getAll(1);
        lsDoUong = dao.getAll(2);
        if (lsMonAn.isEmpty()){

        }else{
            adapter = new DoAnAdapter(lsMonAn,context);
            rcyViewMonAn.setAdapter(adapter);
        }
        if (lsDoUong.isEmpty()){

        }else{
            adapter = new DoAnAdapter(lsDoUong,context);
            rcyViewDoUong.setAdapter(adapter);
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