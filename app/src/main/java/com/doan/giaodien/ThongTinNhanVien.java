package com.doan.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.doan.R;
import com.doan.dulieu.CongViec;
import com.doan.dulieu.NhanVien;
import com.doan.xuly.CongViecAdapter;
import com.doan.xuly.NhanVienApdapter;

import java.util.ArrayList;

public class ThongTinNhanVien extends AppCompatActivity {
    TextView txtTen,txtSdt,txtMaNhanVien,txtCongViec,txtTienLuong;
    CongViecAdapter congViecAdapter;
    NhanVien nhanVien;
    NhanVienApdapter nhanVienApdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_nhan_vien);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        nhanVienApdapter = new NhanVienApdapter(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        nhanVien = (NhanVien) bundle.getSerializable("nhanVien");
        congViecAdapter = new CongViecAdapter(this);
        txtTen = findViewById(R.id.ttTenNhanVien);
        txtTen.setText(nhanVien.getTenNhanVien());
        
        txtSdt = findViewById(R.id.ttSdt);
        txtSdt.setText("0"+nhanVien.getSdtNhanVien());

        txtMaNhanVien = findViewById(R.id.ttMaCongNhanVien);
        txtMaNhanVien.setText(""+nhanVien.getMaNhanVien());

        txtTienLuong = findViewById(R.id.ttTienLuongNhanVien);
        txtTienLuong.setText(""+nhanVien.getTienLuong());

        txtCongViec = findViewById(R.id.ttCongViecNhanVien);

        String tenCongViec=congViecAdapter.getCongViecByID(nhanVien.getMaCongViec()).getTenCongViec();
        txtCongViec.setText(tenCongViec);
    }
    public void chinhSuaNhanVien(View view){
        Bundle bundle = new Bundle();
        bundle.putSerializable("nhanVien",nhanVien);
        Intent intent = new Intent(this,ChinhSuaNhanVien.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public  void xoaNhanVien(View view){
        AlertDialog.Builder bdrThongbao = new AlertDialog.Builder(this);
        bdrThongbao.setTitle("Xác nhận xóa");
        bdrThongbao.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nhanVienApdapter.xoaNhanVien(nhanVien.getMaNhanVien());
                Toast.makeText(ThongTinNhanVien.this, "Xóa thành công",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ThongTinNhanVien.this,QuanLyNhanVien.class);
                startActivity(intent);
            }
        });
        bdrThongbao.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        bdrThongbao.setMessage("Bạn thật sự muốn xóa");
        bdrThongbao.create().show();
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