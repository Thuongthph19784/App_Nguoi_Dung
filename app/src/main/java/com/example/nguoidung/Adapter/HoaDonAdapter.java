package com.example.nguoidung.Adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguoidung.Object.HoaDon;
import com.example.nguoidung.R;

import java.util.List;

public class HoaDonAdapter extends RecyclerView.Adapter<HoaDonAdapter.ViewHolder>{
    List<HoaDon> ls;
    Context context;
    LayoutInflater inflater;

    public HoaDonAdapter(List<HoaDon> ls, Context context) {
        this.ls = ls;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoa_don,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTenMonAN.setText(ls.get(position).getTenMonAn());
        holder.txtGia.setText(String.valueOf(ls.get(position).getGiaMonAn()));
        holder.txtSoLuong.setText(String.valueOf(ls.get(position).getSoLuong()));
        holder.txtTongGia.setText(String.valueOf(ls.get(position).getTongGia()));
        holder.txtNgayMua.setText(ls.get(position).getNgayMua());
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenMonAN,txtGia,txtSoLuong,txtTongGia,txtNgayMua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenMonAN = itemView.findViewById(R.id.item_hoa_don_ten_mon_an);
            txtGia = itemView.findViewById(R.id.item_hoa_don_gia_mon_an);
            txtSoLuong = itemView.findViewById(R.id.item_hoa_don_soLuong);
            txtTongGia = itemView.findViewById(R.id.item_hoa_don_tong_gia);
            txtNgayMua = itemView.findViewById(R.id.item_hoa_don_ngay_mua
            );
        }
    }
}
