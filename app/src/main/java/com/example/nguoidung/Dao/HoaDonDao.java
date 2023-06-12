package com.example.nguoidung.Dao;

import android.util.Log;

import com.example.nguoidung.Object.HoaDon;
import com.example.nguoidung.SQLsever.SQLsever;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    Connection connection;
    public HoaDonDao(){
        // hàm khởi tạo để mở kết nối
        SQLsever db = new SQLsever();
        connection = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }
    public List<HoaDon> getAll(int idTV){
        List<HoaDon> list = new ArrayList<HoaDon>();

        try {
            if (this.connection != null) {

                String sqlQuery = "select an.tenDoAn, an.gia, hd.soLuong, hd.tongGia,hd.ngayMua from HoaDon hd \n" +
                        "inner join DoAn an on hd.idDoAn = an.idDoAn\n" +
                        "where idTV = "+idTV+"" ;

                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    HoaDon obj = new HoaDon();
                    obj.setTenMonAn(resultSet.getString("tenDoAn"));
                    obj.setGiaMonAn(resultSet.getInt("gia"));
                    obj.setSoLuong(resultSet.getInt("soLuong"));
                    obj.setTongGia(resultSet.getInt("tongGia"));
                    obj.setNgayMua(resultSet.getString("NgayMua"));
                    obj.setIdTV(idTV);
                    list.add(obj);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu  " );
            e.printStackTrace();
        }

        return  list;
    }
    public void insertRow (HoaDon obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String insertSQL = "insert into HoaDon (soLuong,ngayMua,tongGia,idDoAn,idTV) values ("+obj.getSoLuong()+",'"+obj.getNgayMua()+"',"+obj.getTongGia()+","+obj.getIdDoAn()+","+obj.getIdTV()+")";

                String generatedColumns[] = { "idHoaDon" };
                PreparedStatement stmtInsert = this.connection.prepareStatement(insertSQL, generatedColumns);
                stmtInsert.execute();
                Log.d("zzzzz", "insertRow: finish insert");
                // lấy ra ID cột tự động tăng
                ResultSet rs = stmtInsert.getGeneratedKeys();


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "insertRow: Có lỗi thêm dữ liệu " );
            e.printStackTrace();
        }
    }
}
