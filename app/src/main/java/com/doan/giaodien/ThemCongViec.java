package com.doan.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.doan.R;
import com.doan.dulieu.CongViec;
import com.doan.dulieu.CongViecArrayAdapter;
import com.doan.xuly.CongViecAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class ThemCongViec extends AppCompatActivity {
    CongViecAdapter cvAdapter;
    TextInputEditText edtThemTenCongViec, edtThemTienLuongCongViec;
    TextInputLayout layoutThemTenCongViec, layoutTienLuongCongViec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_cong_viec);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        cvAdapter = new CongViecAdapter(this);
        edtThemTenCongViec = findViewById(R.id.themTenCongViec);
        edtThemTienLuongCongViec = findViewById(R.id.themTienLuongCongviec);
        edtThemTenCongViec.requestFocus();
        layoutThemTenCongViec = findViewById(R.id.layout_themTenCongviec);
        layoutTienLuongCongViec = findViewById(R.id.layout_themTienLuongCongviec);

    }
    public  void themCongViec(View view){
        String strTenCV, stringTienLuong;
        stringTienLuong = edtThemTienLuongCongViec.getText().toString();
        strTenCV = edtThemTenCongViec.getText().toString().trim();



        if(strTenCV.length()==0){
            layoutThemTenCongViec.setError("Tên công việc không được để trống");
            edtThemTenCongViec.requestFocus();
        }else {
            layoutThemTenCongViec.setError(null);
            if (stringTienLuong.length() == 0) {
                layoutTienLuongCongViec.setError("Chưa nhập tiền lương");
                edtThemTienLuongCongViec.requestFocus();
            } else

                if (Integer.parseInt(stringTienLuong) < 5000) {
                    layoutTienLuongCongViec.setError("Tiền lương công việc không nhỏ hơn 5000");
                    edtThemTienLuongCongViec.requestFocus();
                } else{
                    layoutTienLuongCongViec.setError(null);
                CongViec congViec = new CongViec();

                congViec.setTenCongViec(strTenCV);

                congViec.setTienLuongNgay(Integer.parseInt(stringTienLuong));

                if (cvAdapter.ListAllCongViec().size()==0){
                    congViec.setMaCongViec(1);
                }else
                    congViec.setMaCongViec(cvAdapter.getLastMaCV()+1);


                cvAdapter.themCongViec(congViec);

                Toast.makeText(this, "Thêm thành công!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, QuanLyCongViec.class);
                startActivity(intent);
            }
        }



    }

    public  void troVe(View view){
        Intent intent = new Intent(this, QuanLyCongViec.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent = new Intent(this,QuanLyCongViec.class);
                startActivity(intent);
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}