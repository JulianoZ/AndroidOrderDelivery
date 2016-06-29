package com.example.juliano.androidwsjsonv1;

/**
 * Created by Juliano on 16/02/2016.
 */

import com.example.juliano.androidwsjsonv1.POJO.Flower;
import com.example.juliano.androidwsjsonv1.POJO.Product;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;


public interface RestInterface {

        @GET("/feeds/flowers.json")
        public void getJSON(Callback<List<Flower>> response);



        @GET("/WebService/ProductList")
        public void getWSRestProduct(Callback<List<Product>> response);








        //Send data for the server
        @FormUrlEncoded
        @POST("/Doutorado/insert.php")
        public void insertUser(
                @Field("name") String name,
                @Field("username") String username,
                @Field("password") String password,
                @Field("email") String email,
                Callback<Response> callback);






/*
        @POST("/Doutorado/insertJSON.php")
        public void insertUserJson(@Body JSONObject body, Callback<Response> callBack);
        //Response insertUserJson(@Body TypedString sJsonBody, Callback<Response> callBack) ;
        */



        @FormUrlEncoded
        @POST("/Doutorado/insertJSON.php")
        void insertUserJson(@Field("json") String json, Callback<Response> callBack);








}
