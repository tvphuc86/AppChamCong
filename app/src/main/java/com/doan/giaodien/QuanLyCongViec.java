package com.doan.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.doan.MainActivity;
import com.doan.R;
import com.doan.dulieu.CongViec;
import com.doan.dulieu.CongViecArrayAdapter;
import com.doan.xuly.CongViecAdapter;
import com.doan.xuly.NhanVienApdapter;

import java.util.ArrayList;
import java.util.List;

public class QuanLyCongViec extends AppCompatActivity implements CongViecArrayAdapter.ClickEvent {

    CongViecAdapter cvAdapter;
    CongViecArrayAdapter cvArrayAdapter;
    List<CongViec> lstCongViec;
    ListView lvCongViec;
    TextView txtDemCongViec ;
    NhanVienApdapter nhanVienApdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_cong_viec);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        nhanVienApdapter = new NhanVienApdapter(this);
        cvAdapter = new CongViecAdapter(this);
        lvCongViec = findViewById(R.id.listCongViec);
        lstCongViec = cvAdapter.ListAllCongViec();
        cvArrayAdapter = new CongViecArrayAdapter(this,
                R.layout.danh_sach_cong_viec,
                (ArrayList<CongViec>) lstCongViec,this);
        lvCongViec.setAdapter(cvArrayAdapter);
        txtDemCongViec = findViewById(R.id.demCongViec);
        txtDemCongViec.setText("Tổng số công việc: "+cvArrayAdapter.getCount());


    }
    public void ThemCongViec(View view){
        Intent intent = new Intent(this, ThemCongViec.class);
        startActivity(intent);
    }



    @Override
    public void editClick(int id) {
       CongViec congViec = cvAdapter.getCongViecByID(id);
       Bundle bundle = new Bundle();
       bundle.putSerializable("CongViec",congViec);
       Intent intent = new Intent(this,ChinhSuaCongViec.class);
       intent.putExtras(bundle);
       startActivity(intent);
    }

    @Override
    public void xoaClick(int id) {
        AlertDialog.Builder bdrThongbao = new AlertDialog.Builder(this);
        bdrThongbao.setTitle("Thông báo");
        bdrThongbao.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (nhanVienApdapter.ktCv(id)) {
                    Toast.makeText(QuanLyCongViec.this, " Có nhân viên đang làm công việc này",
                            Toast.LENGTH_LONG).show();
                    dialogInterface.cancel();
                } else {
                    cvAdapter.xoaCongViec(id);
                    Toast.makeText(QuanLyCongViec.this, "Xóa thành công",
                            Toast.LENGTH_LONG).show();
                    lstCongViec = cvAdapter.ListAllCongViec();
                    cvArrayAdapter.setCvArray((ArrayList<CongViec>) lstCongViec);
                    txtDemCongViec.setText("Tổng số công việc: " + cvArrayAdapter.getCount());
                }
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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}
