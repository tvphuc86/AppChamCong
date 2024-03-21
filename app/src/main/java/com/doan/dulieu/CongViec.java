package com.doan.dulieu;

import java.io.Serializable;

public class CongViec implements Serializable {

    private Integer maCongViec;

    private String tenCongViec;

    private Integer tienLuong;

    public CongViec() {
    }

    public CongViec(Integer maCongViec, String tenCongViec, Integer tienLuong) {
        this.maCongViec = maCongViec;
        this.tenCongViec = tenCongViec;
        this.tienLuong = tienLuong;
    }

    public Integer getMaCongViec() {
        return maCongViec;
    }

    public void setMaCongViec(Integer maCongViec) {
        this.maCongViec = maCongViec;
    }

    public String getTenCongViec() {
        return tenCongViec;
    }

    public void setTenCongViec(String tenCongViec) {
        this.tenCongViec = tenCongViec;
    }

    public Integer getTienLuongNgay() {
        return tienLuong;
    }

    public void setTienLuongNgay(Integer tienLuong) {
        this.tienLuong = tienLuong;
    }
}
