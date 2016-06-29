package com.example.juliano.androidwsjsonv1;

/**
 * Created by Juliano on 04/03/2016.
 */


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juliano.androidwsjsonv1.POJO.Flower;
import com.squareup.picasso.Picasso;

import java.util.List;

//import com.aucupa.retroflower.R;



public class adapter extends ArrayAdapter<Flower> {



    String url="http://services.hanselandpetal.com/photos/";
    private Context context;
    private List<Flower> flowerList;


    public adapter(Context context, int resource, List<Flower> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_file,parent,false);
        Flower flower = flowerList.get(position);

        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(flower.getName());

        TextView tv2 = (TextView) view.findViewById(R.id.price);
        tv2.setText(Double.toString(flower.getPrice()));

        ImageView img = (ImageView) view.findViewById(R.id.img);
        Picasso.with(getContext()).load(url+flower.getPhoto()).resize(100,100).into(img);

        return view;
    }
}