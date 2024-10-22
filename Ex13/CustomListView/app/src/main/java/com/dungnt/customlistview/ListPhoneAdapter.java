package com.dungnt.customlistview;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListPhoneAdapter extends ArrayAdapter<Phone> {
    Activity context;
    int idLayout;
    ArrayList<Phone> listPhone;

    public ListPhoneAdapter(Activity context, int idLayout, ArrayList<Phone> listPhone) {
        super(context, idLayout, listPhone);
        this.context = context;
        this.idLayout = idLayout;
        this.listPhone = listPhone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout, null);

        Phone phone = listPhone.get(position);

        ImageView imgphone = convertView.findViewById(R.id.img_phone);
        TextView txtnamephone = convertView.findViewById(R.id.tv_phoneName);

        imgphone.setImageResource(phone.getImage());
        txtnamephone.setText(phone.getName());

        return convertView;
    }
}