package com.example.sello;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<sello_model> list;
    Activity context;

    public MyAdapter(Activity context, ArrayList<sello_model> list)
    {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.list_item_view,null,true);
        TextView text = convertView.findViewById(R.id.item_name);
        ImageView img = convertView.findViewById(R.id.image);
        TextView price = convertView.findViewById(R.id.item_price);
        byte[] data = list.get(position).getImage();
//        Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
//        img.setImageBitmap(bmp);
        Drawable d = Drawable.createFromStream(new ByteArrayInputStream(data),null);
        img.setImageDrawable(d);
        text.setText(list.get(position).getProduct_name());
        price.setText(list.get(position).getProduct_price());
        return convertView;

    }
}
