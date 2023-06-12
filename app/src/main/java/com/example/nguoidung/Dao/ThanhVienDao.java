package com.example.nguoidung.Dao;

import android.util.Log;

import com.example.nguoidung.Object.ThanhVien;
import com.example.nguoidung.SQLsever.SQLsever;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThanhVienDao {
    Connection connection;
    public ThanhVienDao(){
        // hàm khởi tạo để mở kết nối
        SQLsever db = new SQLsever();
        connection = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }
    public List<ThanhVien> getAll(){
        List<ThanhVien> list = new ArrayList<ThanhVien>();

        try {
            if (this.connection != null) {

                String sqlQuery = "SELECT * FROM ThanhVien ";

                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    ThanhVien tv = new ThanhVien();
                    tv.setIdTV(resultSet.getInt("idTV")); // truyền tên cột dữ liệu
                    tv.setUserName(resultSet.getString("userName"));// tên cột dữ liệu là name
                    tv.setPassWord(resultSet.getString("passWord"));
                    tv.setTenNguoiDung(resultSet.getString("tenNguoiDung"));
                    list.add(tv);
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng



        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }

        return  list;
    }

    public void insertRow (ThanhVien obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String insertSQL = "INSERT INTO ThanhVien (userName,passWord,tenNguoiDung,idQTV)" +
                        " VALUES ('" + obj.getUserName() + "','" + obj.getPassWord() + "',N'" + obj.getTenNguoiDung() + "',1)";

                String generatedColumns[] = { "idTV" };
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

    public void updateRow(ThanhVien obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "UPDATE ThanhVien " +
                        "SET userName= '" + obj.getUserName() + "'," +
                        "passWord= '" + obj.getPassWord() + "'," +
                        "tenNguoiDung= '" + obj.getTenNguoiDung() + "'," +
                        "idQTV= 1" +
                        " WHERE idTV = "+obj.getIdTV()+"";


                PreparedStatement stmt = this.connection.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "updateRow: finish Update");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "updateRow: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }
}
