package com.doan.dulieu;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.doan.R;
import com.doan.giaodien.ThongTinNhanVien;
import com.doan.xuly.CongViecAdapter;

import java.util.ArrayList;
import java.util.List;

public class NhanVienArrayAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int layoutId;

    ArrayList<NhanVien> nhanVienArrayList;
    public interface ClickEvent{
        public void Click(int id);

    }
    NhanVienArrayAdapter.ClickEvent clickEvent;
    public NhanVienArrayAdapter(Activity context, int layoutId,
                                ArrayList<NhanVien> nvArray, NhanVienArrayAdapter.ClickEvent clickEvent) {
        super(context, layoutId, nvArray);
        this.context = context;
        this.nhanVienArrayList = nvArray;
        this.layoutId = layoutId;
        this.clickEvent = clickEvent;
    }

    public void setnvArray(ArrayList<NhanVien> nvArray) {
        this.nhanVienArrayList = nvArray;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return nhanVienArrayList.size();
    }
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if(nhanVienArrayList.size()>0 && position>=0) {
            final NhanVien nv = nhanVienArrayList.get(position);
            final TextView txtTenNhanVien = convertView.findViewById(R.id.tenNhanVien);
            txtTenNhanVien.setText(nv.getTenNhanVien());
            final TextView txtTienLuong = convertView.findViewById(R.id.tienLuongNhanVien);
            txtTienLuong.setText("" + nv.getTienLuong()+" vnđ");
            final LinearLayout linearLayout = convertView.findViewById(R.id.layoutItemNhanVien);
           linearLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   clickEvent.Click(nv.getMaNhanVien());
               }
           });


        }

        return convertView;
    }
}
