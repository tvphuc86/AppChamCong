package com.doan.dulieu;

import java.io.Serializable;

public class NhanVien implements Serializable {

    private Integer maNhanVien;

    private  String tenNhanVien;
    private  Integer tienLuong;
    private  Integer sdtNhanVien;
    private Integer maCongViec;

    public Integer getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Integer maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }


    public Integer getTienLuong() {
        return tienLuong;
    }

    public void setTienLuong(Integer tienLuong) {
        this.tienLuong = tienLuong;
    }

    public Integer getSdtNhanVien() {
        return sdtNhanVien;
    }

    public void setSdtNhanVien(Integer sdtNhanVien) {
        this.sdtNhanVien = sdtNhanVien;
    }

    public Integer getMaCongViec() {
        return maCongViec;
    }

    public void setMaCongViec(Integer maCongViec) {
        this.maCongViec = maCongViec;
    }

    public NhanVien(Integer maNhanVien, String tenNhanVien, Integer tienLuong, Integer sdtNhanVien, Integer maCongViec) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.tienLuong = tienLuong;
        this.sdtNhanVien = sdtNhanVien;
        this.maCongViec = maCongViec;
    }

    public NhanVien() {
    }
}
