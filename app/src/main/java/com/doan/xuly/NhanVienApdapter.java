package com.doan.xuly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.doan.dulieu.DbHelper;
import com.doan.dulieu.NhanVien;


import java.util.ArrayList;
import java.util.List;

public class NhanVienApdapter {
    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = { DbHelper.NV_MANV,
            DbHelper.NV_TENNV, DbHelper.NV_TIENLUONG,DbHelper.NV_SDTNV, DbHelper.CV_MACV};

    public NhanVienApdapter(Context context) {
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }
    public long themNhanVien(NhanVien nhanVien) {
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.NV_MANV, nhanVien.getMaNhanVien());
        values.put(DbHelper.NV_TENNV, nhanVien.getTenNhanVien());
        values.put(DbHelper.NV_TIENLUONG, nhanVien.getTienLuong());
        values.put(DbHelper.NV_SDTNV, nhanVien.getSdtNhanVien());
        values.put(DbHelper.CV_MACV, nhanVien.getMaCongViec());

        return db.insert(DbHelper.TABLE_NHANVIEN, null, values);
    }
    public int capNhapNhanVien(int maNhanVien, String tenNhanVien,int tienLuong,int sdtNhanVien, int maCongViec){
        ContentValues values = new ContentValues();
        values.put(DbHelper.NV_TENNV, tenNhanVien);
        values.put(DbHelper.NV_TIENLUONG, tienLuong);
        values.put(DbHelper.NV_SDTNV, sdtNhanVien);
        values.put(DbHelper.CV_MACV, maCongViec);


        return db.update(DbHelper.TABLE_NHANVIEN, values,
                DbHelper.NV_MANV + " = " + maNhanVien, null);
    }
    public int xoaNhanVien(int maNhanVien) {
        return db.delete(DbHelper.TABLE_NHANVIEN,
                DbHelper.NV_MANV + " = " + maNhanVien, null);
    }
    private NhanVien cursorToNhanVien(Cursor cursor) {
        NhanVien values = new NhanVien();
        values.setMaNhanVien(cursor.getInt(0));
        values.setTenNhanVien(cursor.getString(1));
        values.setTienLuong(cursor.getInt(2));
        values.setSdtNhanVien(cursor.getInt(3));
        values.setMaCongViec(cursor.getInt(4));
        return values;
    }
    public List<NhanVien> ListAllNhanVien() {
        List<NhanVien> lstNhanVien= new ArrayList<NhanVien>();
        Cursor cursor = db.query(DbHelper.TABLE_NHANVIEN,
                allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                NhanVien values = cursorToNhanVien(cursor);
                lstNhanVien.add(values);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return lstNhanVien;
    }
    public Integer getLastMaNV(){
        NhanVien values = new NhanVien();
        Cursor cursor = db.query(DbHelper.TABLE_NHANVIEN,
                allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            values = cursorToNhanVien(cursor);
            cursor.close();

        }
        return values.getMaNhanVien();
    }
    public  NhanVien getNhanVienByID(int id){
        NhanVien values = new NhanVien();
        Cursor cursor = db.rawQuery("Select * From "+DbHelper.TABLE_NHANVIEN+ " where " + DbHelper.NV_MANV+"="+id,null);
        cursor.moveToFirst();
        values = cursorToNhanVien(cursor);
        return  values;
    }
    public boolean ktCv(int id){
        NhanVien values = new NhanVien();
        Cursor cursor = db.rawQuery("Select * From "+DbHelper.TABLE_NHANVIEN+ " where " + DbHelper.CV_MACV+"="+id,null);
        if (cursor.getCount()>0)
            return true;
        else return false;
    }

    public void close(){
        db.close();
        myDbHelper.close();
    }
}
