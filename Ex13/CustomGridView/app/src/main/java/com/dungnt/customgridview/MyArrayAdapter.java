package com.dungnt.customgridview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Image> {
    Activity context;
    int layoutId;
    ArrayList<Image> images;

    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Image> images) {
        super(context, layoutId, images);
        this.context = context;
        this.layoutId = layoutId;
        this.images = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);

        ImageView imgView = convertView.findViewById(R.id.imageView);
        TextView txtView = convertView.findViewById(R.id.textView);

        imgView.setImageResource(images.get(position).getImg());
        txtView.setText(images.get(position).getName());

        return convertView;
    }
}
