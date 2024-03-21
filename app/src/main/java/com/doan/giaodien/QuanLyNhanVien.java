package com.doan.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.doan.MainActivity;
import com.doan.R;
import com.doan.dulieu.CongViec;
import com.doan.dulieu.CongViecArrayAdapter;
import com.doan.dulieu.NhanVien;
import com.doan.dulieu.NhanVienArrayAdapter;
import com.doan.xuly.CongViecAdapter;
import com.doan.xuly.NhanVienApdapter;

import java.util.ArrayList;
import java.util.List;

public class QuanLyNhanVien extends AppCompatActivity implements NhanVienArrayAdapter.ClickEvent{

    NhanVienApdapter nvAdapter;
    NhanVienArrayAdapter nvArrayAdapter;
    List<NhanVien> lstNhanVien;
    ListView lvNhanVien;
    TextView txtDemNhanVien ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        nvAdapter = new NhanVienApdapter(this);
        lvNhanVien = findViewById(R.id.listNhanVien);
        lstNhanVien = nvAdapter.ListAllNhanVien();
        nvArrayAdapter = new NhanVienArrayAdapter(this,
                R.layout.danh_sach_nhan_vien,
                (ArrayList<NhanVien>) lstNhanVien,this);
        lvNhanVien.setAdapter(nvArrayAdapter);
        txtDemNhanVien = findViewById(R.id.demNhanVien);
        txtDemNhanVien.setText("Tổng số nhân viên: "+nvArrayAdapter.getCount());
    }

    @Override
    public void Click(int id) {
        NhanVien nhanVien = nvAdapter.getNhanVienByID(id);
        Bundle bundle = new Bundle();
        bundle.putSerializable("nhanVien",nhanVien);
        Intent intent= new Intent(this,ThongTinNhanVien.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    public void themNhanVien(View view) {
        Intent intent = new Intent(this,ThemNhanVien.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}