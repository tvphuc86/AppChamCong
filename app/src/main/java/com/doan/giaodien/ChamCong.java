package com.doan.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.doan.MainActivity;
import com.doan.R;
import com.doan.dulieu.CheckArrayAdapter;
import com.doan.dulieu.NhanVien;
import com.doan.xuly.CongViecAdapter;
import com.doan.xuly.NhanVienApdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ChamCong extends AppCompatActivity implements CheckArrayAdapter.ClickEvent{
    CheckArrayAdapter checkArrayAdapter;
    ListView listView;
    ArrayList<NhanVien> nhanVienArrayList;
    TextInputEditText txtNgayCong;
    TextInputLayout layoutNgayCong;
    NhanVienApdapter nhanVienApdapter;
    CongViecAdapter congViecAdapter;
    int tienLuong;
    ArrayList<NhanVien> lstCheck = new  ArrayList<NhanVien> ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cham_cong);
        nhanVienApdapter=new NhanVienApdapter(this);
        nhanVienArrayList =(ArrayList<NhanVien>) nhanVienApdapter.ListAllNhanVien();
        congViecAdapter = new CongViecAdapter(this);
        txtNgayCong = findViewById(R.id.soNgayCong);

        layoutNgayCong = findViewById(R.id.layout_soNgayCong);
        checkArrayAdapter = new CheckArrayAdapter(this,R.layout.chk_nhan_vien,nhanVienArrayList,this);
        listView =findViewById(R.id.lstCheckNhanvien);
        listView.setAdapter(checkArrayAdapter);

        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void check(NhanVien nhanVien) {
        lstCheck.add(nhanVien);
    }

    @Override
    public void notCheck(NhanVien nhanVien) {
        lstCheck.remove(nhanVien);
    }

    public void luu(View view) {
        if(txtNgayCong.length()==0){
            layoutNgayCong.setError("Cần nhập số ngày công");
            layoutNgayCong.requestFocus();
        }
        else {
            if (Integer.parseInt(txtNgayCong.getText().toString()) <= 0)
            {
                layoutNgayCong.setError("Số ngày công lớn hơn 0");
                layoutNgayCong.requestFocus();
            }
            else {
                layoutNgayCong.setError(null);
                if(lstCheck.size()==0){
                    Toast.makeText(this,"Chưa chọn nhân viên.",Toast.LENGTH_LONG).show();
                }
                else {
                    for (NhanVien e : lstCheck) {
                        tienLuong = e.getTienLuong()+Integer.parseInt(txtNgayCong.getText().toString())*
                                congViecAdapter.getCongViecByID(e.getMaCongViec()).getTienLuongNgay();
                        nhanVienApdapter.capNhapNhanVien(e.getMaNhanVien(),e.getTenNhanVien(),tienLuong,e.getSdtNhanVien(),e.getMaCongViec());
                    }
                    Toast.makeText(this,"Cập nhập thành công",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }

            }

        }
    }
}