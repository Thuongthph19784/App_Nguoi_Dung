package com.example.nguoidung.Dao;

import android.util.Log;

import com.example.nguoidung.Object.DoAn;
import com.example.nguoidung.SQLsever.SQLsever;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DoAnDAO {
    Connection connection;
    public DoAnDAO(){
        // hàm khởi tạo để mở kết nối
        SQLsever db = new SQLsever();
        connection = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }
    public List<DoAn> getAll(int loaiDoAn){
        List<DoAn> list = new ArrayList<DoAn>();

        try {
            if (this.connection != null) {

                String sqlQuery = "SELECT * FROM DoAn WHERE idLoaiDoAn=" + loaiDoAn;

                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    DoAn obj = new DoAn();
                    obj.setIdDoAn(resultSet.getInt("idDoAn")); // truyền tên cột dữ liệu
                    obj.setImage(resultSet.getString("image"));// tên cột dữ liệu là name
                    obj.setTenDoAn(resultSet.getString("tenDoAn"));
                    obj.setGia(resultSet.getInt("gia"));
                    obj.setIdLoaiDoAn(loaiDoAn);
                    list.add(obj);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  list;
    }

}
