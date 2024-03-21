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
import com.doan.xuly.CongViecAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ChinhSuaCongViec extends AppCompatActivity {

    CongViecAdapter cvAdapter;
    TextInputEditText txtTenCongViec, txtTienLuong;
    TextInputLayout layoutTen,layoutTienLuong;
    CongViec congViec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_cong_viec);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        cvAdapter = new CongViecAdapter(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        congViec = (CongViec) bundle.getSerializable("CongViec");

        txtTenCongViec = findViewById(R.id.editTenCongViec);
        txtTenCongViec.setText(congViec.getTenCongViec());

        txtTienLuong = findViewById(R.id.editTienLuongCongviec);
        txtTienLuong.setText(""+congViec.getTienLuongNgay());

        layoutTen= findViewById(R.id.layout_eitTenCongviec);
        layoutTienLuong = findViewById(R.id.layout_editTienLuongCongviec);
    }
    public void luuCongViec(View view){
        String strTenCV, stringTienLuong;
        stringTienLuong = txtTienLuong.getText().toString();
        strTenCV = txtTenCongViec.getText().toString().trim();
        int tienLuong = Integer.parseInt(stringTienLuong);

        if(strTenCV.length()==0){
            layoutTen.setError("Tên công việc không được để trống");
            txtTenCongViec.requestFocus();
        }else {
            layoutTen.setError(null);
            if (stringTienLuong.length() == 0) {
                layoutTienLuong.setError("Chưa nhập tiền lương");
                txtTienLuong.requestFocus();
            } else

            if (Integer.parseInt(stringTienLuong) < 5000) {
                layoutTienLuong.setError("Tiền lương công việc không nhỏ hơn 5000");
                txtTienLuong.requestFocus();
            } else{
                layoutTienLuong.setError(null);

                cvAdapter.capNhapCongViec(congViec.getMaCongViec(),strTenCV,tienLuong);

                Toast.makeText(this, "Lưu thành công!",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, QuanLyCongViec.class);
                startActivity(intent);
            }
        }
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