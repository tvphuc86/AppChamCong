package com.doan.dulieu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "DoAn.db";
    private static final int DB_VERSION = 2;
    public static final String TABLE_CONGVIEC = "congviec";
    public static final String CV_MACV = "maCongViec";
    public static final String CV_TENCV = "tenCongViec";
    public static final String CV_TLCV = "tienLuong";

    public static final String TABLE_NHANVIEN = "nhanvien";
    public static final String NV_MANV = "maNhanVien";
    public static final String NV_TENNV = "tenNhanVien";
    public static final String NV_TIENLUONG ="tienLuong";
    public static final String NV_SDTNV ="soDienThoai";

    private static final String CREATE_TABLE_NHANVIEN
            = "Create Table " + TABLE_NHANVIEN + "("
            + NV_MANV + " INTEGER Primary Key Autoincrement, "
            + NV_TENNV + " TEXT, " + NV_TIENLUONG + " INTEGER, "+ NV_SDTNV+ " INTEGER," +CV_MACV+" INTEGER);";

    public static final String CREATE_TABLE_CONGVIEC =
            "Create Table " + TABLE_CONGVIEC + "("
                    + CV_MACV + " INTEGER Primary Key Autoincrement, "
                    + CV_TENCV + " TEXT, " + CV_TLCV + " REAL);";
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NHANVIEN);
        db.execSQL(CREATE_TABLE_CONGVIEC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table If Exists " + TABLE_CONGVIEC);
        db.execSQL("Drop Table If Exists " + TABLE_NHANVIEN);
        onCreate(db);
    }
}
