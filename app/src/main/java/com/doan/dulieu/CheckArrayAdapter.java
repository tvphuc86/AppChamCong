package com.doan.dulieu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.doan.R;

import java.util.ArrayList;

public class CheckArrayAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int layoutId;
    ArrayList<NhanVien> arrayTenNhanVien;

    public interface ClickEvent{
       public void check(NhanVien nhanVien);
       public void notCheck(NhanVien nhanVien);
    }
    CheckArrayAdapter.ClickEvent clickEvent;
    public CheckArrayAdapter(Activity context, int layoutId,
                                ArrayList<NhanVien> checkArray, CheckArrayAdapter.ClickEvent clickEvent) {
        super(context, layoutId, checkArray);
        this.context = context;
        this.arrayTenNhanVien = checkArray;
        this.layoutId = layoutId;
        this.clickEvent = clickEvent;
    }
    public void setCvArray(ArrayList<NhanVien> ten) {
        this.arrayTenNhanVien = ten;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return arrayTenNhanVien.size();
    }
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if(arrayTenNhanVien.size()>0 && position>=0) {
            final NhanVien nhanVien = arrayTenNhanVien.get(position);

            final CheckBox checkBox = convertView.findViewById(R.id.chkNhanVien);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox.isChecked()){
                        clickEvent.check(nhanVien);
                    }
                    else {
                        clickEvent.notCheck(nhanVien);
                    }
                }
            });

            final TextView textView = convertView.findViewById(R.id.chkTenNhanVien);
            textView.setText(nhanVien.getTenNhanVien());
        }

        return convertView;
    }
}
