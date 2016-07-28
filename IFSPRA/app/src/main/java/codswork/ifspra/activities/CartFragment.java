package codswork.ifspra.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import codswork.ifspra.CartInterface;
import codswork.ifspra.Controller;
import codswork.ifspra.R;
import codswork.ifspra.RestInterface;
import codswork.ifspra.adapters.OrderedProductAdapter;
import codswork.ifspra.pojo.Product;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Juliano on 12/07/2016.
 */
public class CartFragment extends Fragment implements CartInterface {

    View myView;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //if(Controller.isFastBuyChecked)
        //    menu.findItem(R.id.check_buy).setChecked(true);
        //if(Controller.isFastRemChecked)
        //    menu.findItem(R.id.check_rem).setChecked(true);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView  = inflater.inflate(R.layout.cart_layout, container, false);
       // setHasOptionsMenu(true);
        return myView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Carrinho");

        updateCart();

        ((Button)view.findViewById(R.id.btn_clear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controller.Carrinho.clear();
                updateCart();
                Controller.vibrateShort(v.getContext());
            }
        });

        ((Button)view.findViewById(R.id.btn_end)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)
                        getView().getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                new AlertDialog.Builder(getView().getContext())
                        .setTitle("Deseja finalizar o pedido?")
                        .setPositiveButton("Sim",
                                new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Controller.vibrateShort(getView().getContext());
                                        send_json();
                                        Controller.Carrinho.clear();
                                        updateCart();
                                        Controller.vibrateShort(getView().getContext());
                                    }
                                })
                        .setNegativeButton("Não",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .create().show();
            }
        });

    }

    @Override
    public void updateCart() {
        OrderedProductAdapter adapter = new OrderedProductAdapter(
                getActivity(),
                R.layout.ordered_product_item_file,
                Controller.Carrinho.getProducts(),
                this);

        ListView lv = (ListView)getView().findViewById(R.id.list);
        lv.setAdapter(adapter);


        TextView tv = (TextView)getView().findViewById(R.id.tv_value);
        tv.setText("R$ " + Controller.Carrinho.getValue());
    }

    private void send_json(){
        //RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://www.pldlivros.com.br/").build();
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://julianoblanco-001-site3.ctempurl.com/").build();
        RestInterface api = adapter.create(RestInterface.class);
        api.insertUserJson(generate_json(),
                new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        BufferedReader reader = null;
                        String output = "";
                        try{
                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
                            output = reader.readLine();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.d("JSON", output);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                }

        );
    }

    private String generate_json(){

        try{
            JSONObject cart = new JSONObject();

            cart.put("idFinalized", Controller.Carrinho.getId());
            cart.put("GeneralValueTotal", Controller.Carrinho.getValue());
            cart.put("GeneralQuantity", Controller.Carrinho.getQuantity());
            cart.put("Finalized", Controller.Carrinho.isStatusFinalized());
            cart.put("ClientId", 4); //Cod Client Test
            cart.put("StatusOrdered", 0); //Delivery, Produce, Delivery...
            cart.put("StatusOrderedLocal", 1); //Inside or Outside of store
            cart.put("Note", ""); //Note about the ordered
            cart.put("ZipCodeDelivery", 1); //ZipCode Sample to delivery
            cart.put("PayamentId", 1); //1 - Money, 2 - check or 3 - credit card
            cart.put("ValueChange", 0); //Change Value;


            JSONArray products = new JSONArray();
            for (Product prod:Controller.Carrinho.getProducts().keySet()){
                JSONObject p = new JSONObject();
                p.put("quantity", Controller.Carrinho.getProducts().get(prod));
                p.put("product_id", prod.getIdProduct());
                products.put(p);
            }

            cart.put("products", products);
            return cart.toString();
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }





/*
    private String generate_json(){
        JSONObject cart = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try{
            cart.put("id", Controller.Carrinho.getId());
            cart.put("value", Controller.Carrinho.getValue());
            cart.put("quantity", Controller.Carrinho.getQuantity());
            cart.put("finalized", Controller.Carrinho.isStatusFinalized());
            JSONArray products = new JSONArray();
            for (Product prod:Controller.Carrinho.getProducts().keySet()){
                JSONObject p = new JSONObject();
                p.put("quantity", Controller.Carrinho.getProducts().get(prod));
                p.put("product_id", prod.getIdProduct());
                products.put(p);
            }
            cart.put("products", products);
            jsonArray.put(cart);
            return jsonArray.toString();
        }catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
    */









}
