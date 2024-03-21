package com.doan.dulieu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.doan.R;

import java.util.ArrayList;

public class CongViecArrayAdapter extends ArrayAdapter<CongViec> {
    Activity context;
    int layoutId;
    ArrayList<CongViec> congViecArrayListArray;
    public interface ClickEvent{
        public void editClick(int id);
        public void xoaClick(int id);
    }
    ClickEvent clickEvent;
    public CongViecArrayAdapter(Activity context, int layoutId,
                                 ArrayList<CongViec> cvArray,ClickEvent clickEvent) {
        super(context, layoutId, cvArray);
        this.context = context;
        this.congViecArrayListArray = cvArray;
        this.layoutId = layoutId;
        this.clickEvent = clickEvent;
    }

    public void setCvArray(ArrayList<CongViec> cvArray) {
        this.congViecArrayListArray = cvArray;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return congViecArrayListArray.size();
    }
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if(congViecArrayListArray.size()>0 && position>=0) {
            final CongViec cv = congViecArrayListArray.get(position);
            final TextView txtIdCongViec = convertView.findViewById(R.id.idCongViec);
            txtIdCongViec.setText(""+(position + 1));
            final TextView txtTenCongViec = convertView.findViewById(R.id.tenCongViec);
            txtTenCongViec.setText(cv.getTenCongViec());
            final TextView txtTienLuong = convertView.findViewById(R.id.tienLuong);
            txtTienLuong.setText(""+cv.getTienLuongNgay());
            final ImageButton btnEdit = convertView.findViewById(R.id.btnEditCongViec);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickEvent.editClick(cv.getMaCongViec());
                }
            });
            final ImageButton btnXoa = convertView.findViewById(R.id.btnXoaCongViec);
            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickEvent.xoaClick(cv.getMaCongViec());
                }
            });
        }

        return convertView;
    }
}
