package com.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.doan.dulieu.ImageAdapter;
import com.doan.giaodien.ChamCong;
import com.doan.giaodien.QuanLyCongViec;
import com.doan.giaodien.QuanLyNhanVien;

public class MainActivity extends AppCompatActivity {

    private Integer[] Images = {R.mipmap.nhanvien, R.mipmap.congviec,
            R.mipmap.check, R.mipmap.exit};
    Class[] arrClasses = {QuanLyNhanVien.class, QuanLyCongViec.class,
            ChamCong.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gdvMenu = findViewById(R.id.gdv_menu);
        ImageAdapter adapter = new ImageAdapter(this, Images);
        gdvMenu.setAdapter(adapter);
        gdvMenu.setOnItemClickListener(new ChonCongViec());

    }

    private class ChonCongViec implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (i == 3)
                finish();
            else {
                Intent intent = new Intent(MainActivity.this,
                        arrClasses[i]);
                startActivity(intent);
            }
        }
    }
}