package com.example.nguoidung.Adapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguoidung.Dao.HoaDonDao;
import com.example.nguoidung.Dao.YeuThichDao;
import com.example.nguoidung.Object.HoaDon;
import com.example.nguoidung.Object.YeuThich;
import com.example.nguoidung.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class YeuThichAdapter extends RecyclerView.Adapter<YeuThichAdapter.ViewHolder>{
    List<YeuThich> ls;
    Context context;
    LayoutInflater inflater;
    YeuThichDao dao;
    public YeuThichAdapter(List<YeuThich> ls, Context context) {
        this.ls = ls;
        this.context = context;
        notifyDataSetChanged();
        dao = new YeuThichDao();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_yeu_thich,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(CovertBitmap(ls.get(position).getImage()));
        holder.txtTenDoAn.setText(ls.get(position).getTenMonAn());
        holder.txtGiaDoAn.setText(String.valueOf(ls.get(position).getGiaMonAn()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(holder.getAdapterPosition(), Gravity.BOTTOM);
            }
        });
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(holder.getAdapterPosition());
            }
        });
    }
    public void Delete(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.deleteRow(ls.get(position));
                loadData();
                Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void showDialog(int position, int gravity){
        View view = inflater.inflate(R.layout.yeu_thich_dialog_mua_do_an, null);
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        ImageView imageView = view.findViewById(R.id.yeu_thich_dialog_mua_do_an_img);
        TextView txtTenMonAn = view.findViewById(R.id.yeu_thich_dialog_mua_do_an_ten_mon_an);
        TextView txtGiaMonAn = view.findViewById(R.id.yeu_thich_dialog_mua_do_an_gia_mon_an);
        EditText edtSoLuong = view.findViewById(R.id.yeu_thich_dialog_mua_do_an_edt_soLuong);
        ImageView btnXoa = view.findViewById(R.id.yeu_thich_dialog_mua_do_an_btn_xoa);
        ImageView btnThem = view.findViewById(R.id.yeu_thich_dialog_mua_do_an_btn_them);
        Button btnMua = view.findViewById(R.id.yeu_thich_dialog_mua_do_an_btn_mua_ngay);

        imageView.setImageBitmap(CovertBitmap(ls.get(position).getImage()));
        txtTenMonAn.setText(ls.get(position).getTenMonAn());
        txtGiaMonAn.setText(String.valueOf(ls.get(position).getGiaMonAn()));
        edtSoLuong.setText("1");

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuong = Integer.parseInt(edtSoLuong.getText().toString());
                if (soLuong > 1){
                    soLuong-=1;
                    edtSoLuong.setText(String.valueOf(soLuong));
                }
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuong = Integer.parseInt(edtSoLuong.getText().toString());
                    soLuong+=1;
                    edtSoLuong.setText(String.valueOf(soLuong));
            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = context.getSharedPreferences("ID_FILE", Context.MODE_PRIVATE);
                int gia = Integer.parseInt(txtGiaMonAn.getText().toString());
                int soLuong = Integer.parseInt(edtSoLuong.getText().toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date date = Calendar.getInstance().getTime();
                String ngayMua = simpleDateFormat.format(date);
                int tongGia = gia*soLuong;
                int idDoAn = ls.get(position).getIdDoAN();
                int idTV = pref.getInt("idTV",-1);
                HoaDon hoaDon = new HoaDon();
                hoaDon.setSoLuong(soLuong);
                hoaDon.setTongGia(tongGia);
                hoaDon.setNgayMua(ngayMua);
                hoaDon.setIdTV(idTV);
                hoaDon.setIdDoAn(idDoAn);
                insertDoAn(hoaDon);
                Toast.makeText(context, "Mua thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        if (window == null){
            return;
        }
        // set kích thước dialog
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // set vị trí dialog
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        dialog.show();

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public void insertDoAn(HoaDon obj){
        HoaDonDao dao = new HoaDonDao();
        dao.insertRow(obj);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,imgXoa;
        TextView txtTenDoAn;
        TextView txtGiaDoAn;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_yeu_thich_img);
            txtTenDoAn = itemView.findViewById(R.id.item_yeu_thich_ten_mon_an);
            txtGiaDoAn = itemView.findViewById(R.id.item_yeu_thich_gia);
            cardView = itemView.findViewById(R.id.item_yeu_thich_cardView);
            imgXoa = itemView.findViewById(R.id.item_yeu_thich_img_xoa);
        }
    }
    public Bitmap CovertBitmap(String path) {
        Bitmap mbitmap = null;
        try {
            URL url = new URL(path);
            InputStream inputStream = url.openConnection().getInputStream();

            mbitmap = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mbitmap;
    }
    public void loadData() {
        SharedPreferences pref = context.getSharedPreferences("ID_FILE", Context.MODE_PRIVATE);
        int idTV = pref.getInt("idTV",-1);
        ls.clear();
        ls = dao.getAll(idTV);
        notifyDataSetChanged();
    }
}
