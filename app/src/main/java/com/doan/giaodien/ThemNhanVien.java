package com.doan.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.doan.R;
import com.doan.dulieu.CongViec;
import com.doan.dulieu.NhanVien;
import com.doan.xuly.CongViecAdapter;
import com.doan.xuly.NhanVienApdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class ThemNhanVien extends AppCompatActivity {

    TextInputLayout layoutTen, layoutSdt;
    TextInputEditText edtTen,edtSdt;

    Spinner spnCongViec;

    CongViecAdapter congViecAdapter;
    String[] tenCongViec;
    Integer maCongViec;

    NhanVienApdapter nhanVienApdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        layoutTen=findViewById(R.id.layout_themTenNhanVien);
        layoutSdt=findViewById(R.id.layout_themSdtNhanVien);

        edtTen = findViewById(R.id.themTenNhanVien);
        edtSdt = findViewById(R.id.themSdtNhanVien);

        nhanVienApdapter = new NhanVienApdapter(this);

        spnCongViec = findViewById(R.id.spnCongViecNhanVien);
        congViecAdapter = new CongViecAdapter(this);
        tenCongViec =congViecAdapter.getAllTen();

       if (tenCongViec.length==0){
           Toast.makeText(this,"Chưa có công việc. Vui lòng thêm công việc trước.",Toast.LENGTH_LONG).show();
           Intent intent = new Intent(this,ThemCongViec.class);
           startActivity(intent);
       }

        ArrayAdapter<String> adapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item,tenCongViec);
        adapter.setDropDownViewResource( android.R.layout.simple_list_item_single_choice);
        spnCongViec.setAdapter(adapter);
        spnCongViec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               maCongViec =  congViecAdapter.getCongViecByTen( tenCongViec[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void themNhanVien(View view){
        String strTen, strSdt;
        strSdt = edtSdt.getText().toString();
        strTen = edtTen.getText().toString().trim();



        if(strTen.length()==0){
            layoutTen.setError("Tên  không được để trống");
            edtTen.requestFocus();
        }else {
            layoutTen.setError(null);
            if (strSdt.length() == 0) {
                layoutSdt.setError("Chưa số điện thoại");
                edtSdt.requestFocus();
            } else

            if (Integer.parseInt(strSdt) < 100000000) {
                layoutSdt.setError("số điện thoại không đúng");
                edtSdt.requestFocus();
            } else{
                layoutSdt.setError(null);
                NhanVien nhanVien = new NhanVien();

                nhanVien.setTenNhanVien(strTen);

                nhanVien.setSdtNhanVien(Integer.parseInt(strSdt));

                if (nhanVienApdapter.ListAllNhanVien().size()==0){
                    nhanVien.setMaNhanVien(1);
                }else
                    nhanVien.setMaNhanVien(nhanVienApdapter.getLastMaNV()+1);

                nhanVien.setTienLuong(0);
                nhanVien.setMaCongViec(maCongViec);
                nhanVienApdapter.themNhanVien(nhanVien);

                Toast.makeText(this, "Thêm thành công!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, QuanLyNhanVien.class);
                startActivity(intent);
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent = new Intent(this,QuanLyNhanVien.class);
                startActivity(intent);
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}