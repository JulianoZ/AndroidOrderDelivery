package codswork.ifspra.pojo;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import codswork.ifspra.Controller;
import codswork.ifspra.R;

/**
 * Created by Felipe on 16/07/2016.
 */
public class Ordered {

    public static int id_count;
    private int id;

    private double value = 0;

    private int quantity = 0;

    private Calendar OrderedTime;

    private boolean StatusFinalized;

    private HashMap<Product, Integer> Products = new HashMap<>();

    public Ordered(){
        id_count++;
        this.id = id_count;
        this.OrderedTime = Calendar.getInstance();
        this.StatusFinalized = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Product, Integer> getProducts() {
        return Products;
    }

    public double getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
    }

    public Calendar getOrderedTime() {
        return OrderedTime;
    }
    public boolean isStatusFinalized() {
        return StatusFinalized;
    }

    public void setStatusFinalized(boolean statusFinalized) {
        StatusFinalized = statusFinalized;
    }

    public void add(Product p, int quantity){//, View v){
        if(!Products.containsKey(p)) {
            Products.put(p, quantity);
        }else{
            int q = Products.get(p);
            Products.remove(p);
            Products.put(p, quantity + q);
        }
        value += p.getPrice() * quantity;
        this.quantity += quantity;
        //((MenuItem)v.findViewById(R.id.total_value)).setTitle("R$ " + value);
    }
    public void subtract(Product p, int quantity){//, View v){
        if(Products.containsKey(p)) {
            int q = Products.get(p);
            Products.remove(p);
            value -= p.getPrice() * quantity;
            this.quantity -= quantity;
            if(q-quantity<0) {
                this.quantity += q-quantity;
            }
            if(q-quantity>0) {
                Products.put(p, q - quantity);
            }
            //((MenuItem)v.findViewById(R.id.total_value)).setTitle("R$ " + value);
        }
    }

    public void remove(Product p){ Products.remove(p);}
    public void clear(){Products.clear(); value=0; quantity=0;}
}
