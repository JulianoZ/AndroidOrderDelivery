package com.example.juliano.androidwsjsonv1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juliano.androidwsjsonv1.POJO.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Juliano on 31/05/2016.
 */
public class adapterProduct extends ArrayAdapter<Product> {



    String url="http://julianoblanco-001-site3.ctempurl.com/Images/Products/";
    private Context context;
    private List<Product> productList;


    public adapterProduct(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.productList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_file,parent,false);
        Product prod = productList.get(position);

        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(prod.getName());

        TextView tv2 = (TextView) view.findViewById(R.id.price);
        tv2.setText(Double.toString(prod.getPrice()));

        ImageView img = (ImageView) view.findViewById(R.id.img);
        Picasso.with(getContext()).load(url+prod.getPicture1()).resize(100,100).into(img);

        return view;
    }



}


