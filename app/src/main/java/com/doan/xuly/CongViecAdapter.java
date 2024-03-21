package com.doan.xuly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.doan.dulieu.CongViec;
import com.doan.dulieu.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class CongViecAdapter {
    private DbHelper myDbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = { DbHelper.CV_MACV,
            DbHelper.CV_TENCV, DbHelper.CV_TLCV,};
    public CongViecAdapter(Context context) {
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }




    public long themCongViec(CongViec congViec) {
        db = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.CV_MACV, congViec.getMaCongViec());
        values.put(DbHelper.CV_TENCV, congViec.getTenCongViec());
        values.put(DbHelper.CV_TLCV, congViec.getTienLuongNgay());

        return db.insert(DbHelper.TABLE_CONGVIEC, null, values);
    }
    public int capNhapCongViec(int maCongViec, String tenConghViec,int tienLuong){
        ContentValues values = new ContentValues();
        values.put(DbHelper.CV_TENCV, tenConghViec);
        values.put(DbHelper.CV_TLCV, tienLuong);


        return db.update(DbHelper.TABLE_CONGVIEC, values,
                DbHelper.CV_MACV + " = " + maCongViec, null);
    }
    public int xoaCongViec(int maCongViec) {
        return db.delete(DbHelper.TABLE_CONGVIEC,
                DbHelper.CV_MACV + " = " + maCongViec, null);
    }
    private CongViec cursorToCongViec(Cursor cursor) {
        CongViec values = new CongViec();
        values.setMaCongViec(cursor.getInt(0));
        values.setTenCongViec(cursor.getString(1));
        values.setTienLuongNgay(cursor.getInt(2));
        return values;
    }
    public List<CongViec> ListAllCongViec() {
        List<CongViec> lstCongViec = new ArrayList<CongViec>();
        Cursor cursor = db.query(DbHelper.TABLE_CONGVIEC,
                allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                CongViec values = cursorToCongViec(cursor);
                lstCongViec.add(values);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return lstCongViec;
    }
    public Integer getLastMaCV(){
        CongViec values = new CongViec();
        Cursor cursor = db.query(DbHelper.TABLE_CONGVIEC,
                allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            values = cursorToCongViec(cursor);

            cursor.close();

        }
        return values.getMaCongViec();
    }
    public  CongViec getCongViecByID(int id){
        CongViec values = new CongViec();
        Cursor cursor = db.rawQuery("Select * From "+DbHelper.TABLE_CONGVIEC+ " where " + DbHelper.CV_MACV+"="+id,null);
        cursor.moveToFirst();
        values = cursorToCongViec(cursor);
        return  values;
    }
    public  Integer getCongViecByTen(String ten){
        CongViec values = new CongViec();
        Cursor cursor = db.rawQuery("Select * From "+DbHelper.TABLE_CONGVIEC+ " where " + DbHelper.CV_TENCV+"= '"+ten+"'",null);
        cursor.moveToFirst();
        values = cursorToCongViec(cursor);
        return  values.getMaCongViec();
    }
    public String[] getAllTen(){
        String[] Ten;
        int i=0;
        Cursor cursor = db.rawQuery("Select " + DbHelper.CV_TENCV+ " From "+DbHelper.TABLE_CONGVIEC,null);
        Ten = new String[cursor.getCount()];
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Ten[i] = cursor.getString(0);
                cursor.moveToNext();
                i++;
            }
            cursor.close();
        }
        return Ten;
    }

    public void close(){
        db.close();
        myDbHelper.close();
    }
}

