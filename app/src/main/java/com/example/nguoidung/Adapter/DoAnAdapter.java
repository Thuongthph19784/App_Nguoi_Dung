package com.example.nguoidung.Adapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguoidung.Activity.Home;
import com.example.nguoidung.Activity.MainActivity;
import com.example.nguoidung.Dao.HoaDonDao;
import com.example.nguoidung.Dao.YeuThichDao;
import com.example.nguoidung.Object.DoAn;
import com.example.nguoidung.Object.HoaDon;
import com.example.nguoidung.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DoAnAdapter extends RecyclerView.Adapter<DoAnAdapter.ViewHolder>{
    List<DoAn> ls;
    Context context;
    LayoutInflater inflater;

    public DoAnAdapter(List<DoAn> ls, Context context) {
        this.ls = ls;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_view_mon_an,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(CovertBitmap(ls.get(position).getImage()));
        holder.txtTenDoAn.setText(ls.get(position).getTenDoAn());
        holder.txtGiaDoAn.setText(String.valueOf(ls.get(position).getGia()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(holder.getAdapterPosition(), Gravity.BOTTOM);
            }
        });
    }
    public void showDialog(int position, int gravity){
        View view = inflater.inflate(R.layout.trang_chu_dialog_mua_do_an, null);
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        ImageView imageView = view.findViewById(R.id.trang_chu_dialog_mua_do_an_img);
        TextView txtTenMonAn = view.findViewById(R.id.trang_chu_dialog_mua_do_an_ten_mon_an);
        TextView txtGiaMonAn = view.findViewById(R.id.trang_chu_dialog_mua_do_an_gia_mon_an);
        EditText edtSoLuong = view.findViewById(R.id.trang_chu_dialog_mua_do_an_edt_soLuong);
        ImageView btnXoa = view.findViewById(R.id.trang_chu_dialog_mua_do_an_btn_xoa);
        ImageView btnThem = view.findViewById(R.id.trang_chu_dialog_mua_do_an_btn_them);
        Button btnYeuThich = view.findViewById(R.id.trang_chu_dialog_mua_do_an_btn_them_yeu_thich);
        Button btnMua = view.findViewById(R.id.trang_chu_dialog_mua_do_an_btn_mua_ngay);

        imageView.setImageBitmap(CovertBitmap(ls.get(position).getImage()));
        txtTenMonAn.setText(ls.get(position).getTenDoAn());
        txtGiaMonAn.setText(String.valueOf(ls.get(position).getGia()));
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

        btnYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = context.getSharedPreferences("ID_FILE", Context.MODE_PRIVATE);
                YeuThichDao dao = new YeuThichDao();
                int idDoAn = ls.get(position).getIdDoAn();
                int idTV = pref.getInt("idTV",-1);
                dao.insertRow(idDoAn,idTV);
                Toast.makeText(context, "Thêm yêu thích thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnMua.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                SharedPreferences pref = context.getSharedPreferences("ID_FILE", Context.MODE_PRIVATE);
                int gia = Integer.parseInt(txtGiaMonAn.getText().toString());
                int soLuong = Integer.parseInt(edtSoLuong.getText().toString());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date date = Calendar.getInstance().getTime();
                String ngayMua = simpleDateFormat.format(date);
                int tongGia = gia*soLuong;
                int idDoAn = ls.get(position).getIdDoAn();
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
        ImageView imageView;
        TextView txtTenDoAn;
        TextView txtGiaDoAn;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_view_mon_an_img);
            txtTenDoAn = itemView.findViewById(R.id.item_view_mon_an_ten_mon_an);
            txtGiaDoAn = itemView.findViewById(R.id.item_view_mon_an_gia_mon_an);
            cardView = itemView.findViewById(R.id.item_view_mon_an_cardView);
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
}
