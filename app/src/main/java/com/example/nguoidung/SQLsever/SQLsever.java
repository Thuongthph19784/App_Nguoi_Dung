package com.example.nguoidung.SQLsever;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLsever {
    Connection connection;
    final String TAG = "zzzzzz";

    public Connection openConnect(){
        //10.24.13.4
        //192.168.0.17
        String ip = "192.168.0.4", port = "1433", user = "sa", pass = "003127", db = "DuAn1";
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            String connectUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db +";user=" + user +";password=" + pass +";";
            this.connection = DriverManager.getConnection(connectUrl);
            Log.d(TAG, "openConnect: OK");
        } catch (Exception e) {
            Log.e(TAG, "getCollection: Loi ket noi CSDL" );
            e.printStackTrace();
        }
        return connection;
    }
}
