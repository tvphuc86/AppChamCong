package com.doan.giaodien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doan.R;
import com.doan.dulieu.NhanVien;
import com.doan.xuly.CongViecAdapter;
import com.doan.xuly.NhanVienApdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ChinhSuaNhanVien extends AppCompatActivity {
    TextInputLayout layoutTen, layoutSdt;
    TextInputEditText edtTen,edtSdt;

    CongViecAdapter congViecAdapter;
    NhanVienApdapter nhanVienApdapter;
    NhanVien nhanVien;
    Spinner spnCongViec;


    String[] tenCongViec;
    Integer maCongViec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_nhan_vien);
        ActionBar actionBar =getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        nhanVien = (NhanVien) bundle.getSerializable("nhanVien");
        congViecAdapter = new CongViecAdapter(this);
        nhanVienApdapter = new NhanVienApdapter(this);

        edtTen = findViewById(R.id.editTenNhanVien);
        edtTen.setText(nhanVien.getTenNhanVien());

        edtSdt = findViewById(R.id.editSdtNhanVien);
        edtSdt.setText("0"+nhanVien.getSdtNhanVien());

        layoutTen = findViewById(R.id.layout_editTenNhanVien);
        layoutSdt = findViewById(R.id.layout_editSdtNhanVien);
        tenCongViec =congViecAdapter.getAllTen();

        spnCongViec = findViewById(R.id.spnEditCongViecNhanVien);
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
    public  void luuNhanVien(View view){
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

                nhanVienApdapter.capNhapNhanVien(nhanVien.getMaNhanVien(),
                        strTen,nhanVien.getTienLuong(),Integer.parseInt(strSdt),maCongViec);

                Toast.makeText(this, "Lưu thành công",
                        Toast.LENGTH_LONG).show();

                Bundle bundle = new Bundle();
                bundle.putSerializable("nhanVien",nhanVienApdapter.getNhanVienByID(nhanVien.getMaNhanVien()));
                Intent intent= new Intent(this,ThongTinNhanVien.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                Bundle bundle = new Bundle();
                bundle.putSerializable("nhanVien",nhanVienApdapter.getNhanVienByID(nhanVien.getMaNhanVien()));
                Intent intent= new Intent(this,ThongTinNhanVien.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}