package com.example.nguoidung.Dao;

import android.util.Log;

import com.example.nguoidung.Object.YeuThich;
import com.example.nguoidung.SQLsever.SQLsever;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class YeuThichDao {
    Connection connection;
    public YeuThichDao(){
        // hàm khởi tạo để mở kết nối
        SQLsever db = new SQLsever();
        connection = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL
    }
    public List<YeuThich> getAll(int idTV){
        List<YeuThich> list = new ArrayList<YeuThich>();

        try {
            if (this.connection != null) {

                String sqlQuery = "select yt.idYeuThich,da.image,da.tenDoAn, da.gia,yt.idDoAn from yeuThich yt \n" +
                        "inner join DoAn da on yt.idDoAn = da.idDoAn\n" +
                        "inner join ThanhVien tv on yt.idTV = tv.idTV\n" +
                        "where yt.idTV = " + idTV;

                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    YeuThich obj = new YeuThich();
                    obj.setIdYeuThich(resultSet.getInt("idYeuThich"));
                    obj.setImage(resultSet.getString("image"));
                    obj.setTenMonAn(resultSet.getString("tenDoAn"));
                    obj.setGiaMonAn(resultSet.getInt("gia"));
                    obj.setIdDoAN(resultSet.getInt("idDoAn"));
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
    public void insertRow (int idDoAN,int idTV){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String insertSQL = "insert into yeuThich values ("+idDoAN +"," + idTV +")";

                String generatedColumns[] = { "idYeuThich" };
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
    public void deleteRow(YeuThich obj){

        try {
            if (this.connection != null) {
                // ghép chuỗi SQL
                String sqlUpdate = "delete from yeuThich where idYeuThich = "+obj.getIdYeuThich()+"";


                PreparedStatement stmt = this.connection.prepareStatement(sqlUpdate);
                stmt.execute(); // thực thi câu lệnh SQL

                Log.d("zzzzz", "delete: finish delete");


            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng


        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "delete: Có lỗi sửa dữ liệu " );
            e.printStackTrace();
        }
    }

}
