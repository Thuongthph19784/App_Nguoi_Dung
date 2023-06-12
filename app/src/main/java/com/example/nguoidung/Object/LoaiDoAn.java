package com.example.nguoidung.Object;

public class LoaiDoAn {
    private int idLoaiDoAn;
    private String tenDoAn;
    private int idQTV;

    public LoaiDoAn() {
    }

    public int getIdLoaiDoAn() {
        return idLoaiDoAn;
    }

    public void setIdLoaiDoAn(int idLoaiDoAn) {
        this.idLoaiDoAn = idLoaiDoAn;
    }

    public String getTenDoAn() {
        return tenDoAn;
    }

    public void setTenDoAn(String tenDoAn) {
        this.tenDoAn = tenDoAn;
    }

    public int getIdQTV() {
        return idQTV;
    }

    public void setIdQTV(int idQTV) {
        this.idQTV = idQTV;
    }

    @Override
    public String toString() {
        return "LoaiDoAn{" +
                "idLoaiDoAn=" + idLoaiDoAn +
                ", tenDoAn='" + tenDoAn + '\'' +
                ", idQTV=" + idQTV +
                '}';
    }
}
