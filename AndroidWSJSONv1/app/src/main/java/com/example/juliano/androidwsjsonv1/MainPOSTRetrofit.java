package com.example.juliano.androidwsjsonv1;


/*
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
*/


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainPOSTRetrofit extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private Button buttonRegister;
    public static final String ROOT_URL =  "http://www.pldlivros.com.br/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_postretrofit);






        //Initializing Views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        //Adding listener to button
        buttonRegister.setOnClickListener(this);










    }





    @Override
    public void onClick(View v) {

        Log.d("Erro4", "Erro | " + editTextName.getText().toString());
        insertUser();

    }




    private void insertUser(){

        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ROOT_URL).build();  //Setting the Root URL        //Finally building the adapter

        //Creating object for our interface
        RestInterface api = adapter.create(RestInterface.class);

        //Defining the method insertuser of our interface

        Log.d("Erro3", "Erro | " + editTextName.getText().toString());


        api.insertUser(

                //Passing the values by getting it from editTexts
                editTextName.getText().toString(),
                editTextUsername.getText().toString(),
                editTextPassword.getText().toString(),
                editTextEmail.getText().toString(),



                //Creating an anonymous callback
                new Callback<Response>() { //metodo usado para fazer a leitura do retorno do servidor após realizar a ação.
                    @Override
                    public void success(Response result, Response response) {

                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = "";
                        Log.d("Erro1", "Erro | ");

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));


                            //Reading the output in the string
                            output = reader.readLine();
                            Log.d("Erro2", "Erro | ");
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d("Erro5", "Erro | " + e.getCause() + e.getMessage());
                        }

                        //Displaying the output as a toast
                        Toast.makeText(MainPOSTRetrofit.this," Dados salvos com sucesso no servidor" +  output, Toast.LENGTH_LONG).show();
                        Log.d("Erro6", "Erro | " + output);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(MainPOSTRetrofit.this, error.toString(),Toast.LENGTH_LONG).show();
                        Log.d("Erro6", "Erro | " + error.getMessage() + error.getCause());

                    }
                }
        );
    }


}
