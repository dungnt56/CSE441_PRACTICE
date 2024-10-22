package com.dungnt.tabselector_customlistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    Activity context;
    int layoutId;
    ArrayList<Item> myArray;

    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Item> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);

        TextView txtmaso = convertView.findViewById(R.id.txtmaso);
        TextView txttieude = convertView.findViewById(R.id.txttieude);
        ImageButton btnlike = convertView.findViewById(R.id.btnlike);

        Item item = myArray.get(position);

        txtmaso.setText(item.getMaso());
        txttieude.setText(item.getTieude());

        int thich = item.getThich();
        if (thich == 1) {
            btnlike.setImageResource(R.drawable.ic_heart);
        } else {
            btnlike.setImageResource(R.drawable.ic_unlike);
        }
        btnlike.setMaxHeight(16);
        btnlike.setMaxWidth(16);

        return convertView;
    }
}

