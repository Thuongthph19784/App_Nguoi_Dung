package com.example.nguoidung.Object;

public class ThanhVien {
    private int idTV;
    private String userName;
    private String passWord;
    private String tenNguoiDung;
    private int idQTV = 1;

    public ThanhVien() {
    }

    public int getIdTV() {
        return idTV;
    }

    public void setIdTV(int idTV) {
        this.idTV = idTV;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public int getIdQTV() {
        return idQTV;
    }

    @Override
    public String toString() {
        return "ThanhVien{" +
                "idTV=" + idTV +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", tenNguoiDung='" + tenNguoiDung + '\'' +
                ", idQTV=" + idQTV +
                '}';
    }
}
