package com.example.nguoidung.Object;

public class DoAn {
    private int idDoAn;
    private String tenDoAn;
    private int gia;
    private String image;
    private int idLoaiDoAn;

    public DoAn() {
    }

    public int getIdDoAn() {
        return idDoAn;
    }

    public void setIdDoAn(int idDoAn) {
        this.idDoAn = idDoAn;
    }

    public String getTenDoAn() {
        return tenDoAn;
    }

    public void setTenDoAn(String tenDoAn) {
        this.tenDoAn = tenDoAn;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdLoaiDoAn() {
        return idLoaiDoAn;
    }

    public void setIdLoaiDoAn(int idLoaiDoAn) {
        this.idLoaiDoAn = idLoaiDoAn;
    }

    @Override
    public String toString() {
        return "DoAn{" +
                "idDoAn=" + idDoAn +
                ", tenDoAn='" + tenDoAn + '\'' +
                ", gia=" + gia +
                ", image='" + image + '\'' +
                ", idLoaiDoAn=" + idLoaiDoAn +
                '}';
    }
}
