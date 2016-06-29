package com.example.juliano.androidwsjsonv1;

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

import com.example.juliano.androidwsjsonv1.POJO.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainPostJSONRetrofit extends AppCompatActivity  implements View.OnClickListener{



    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private Button buttonRegister;
    public static final String ROOT_URL =  "http://www.pldlivros.com.br/";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_post_jsonretrofit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);









    }

    @Override
    public void onClick(View v) {


        String json = GerarJSON().toString();
        Log.d("JSONForm", "JSON FORM | " + json);

        insertUserPOST();



    }









    private void insertUserPOST(){

        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ROOT_URL).build();  //Setting the Root URL        //Finally building the adapter

        //Creating object for our interface
        RestInterface api = adapter.create(RestInterface.class);

        //Defining the method insertuser of our interface

        Log.d("Erro3", "Erro | " + editTextName.getText().toString());


        /*api.insertUser(

                //Passing the values by getting it from editTexts
                editTextName.getText().toString(),
                editTextUsername.getText().toString(),
                editTextPassword.getText().toString(),
                editTextEmail.getText().toString(),



                //Creating an anonymous callback
                new Callback<Response>() {
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
                        Toast.makeText(MainPostJSONRetrofit.this, " Dados salvos com sucesso no servidor" + output, Toast.LENGTH_LONG).show();
                        Log.d("Erro6", "Erro | " + output);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(MainPostJSONRetrofit.this, error.toString(),Toast.LENGTH_LONG).show();

                    }
                }
        );
        */


        api.insertUserJson(

                //GerarJSON(), //Envia o JSON como parametro no formato JSonObject
                GerarJSONV2(), //Enviando JSON no formato de string




                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {

                        //On success we will read the server's output using bufferedreader
                        //Creating a bufferedreader object
                        BufferedReader reader = null;

                        //An string to store output from the server
                        String output = ""; //Return a error from php file. The first msg create bu the server
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
                        Toast.makeText(MainPostJSONRetrofit.this, " Dados salvos com sucesso no servidor" + output, Toast.LENGTH_LONG).show();
                        Log.d("Erro6", "Erro | Pegando o retorno da página:   " + output);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //If any error occured displaying the error as toast
                        Toast.makeText(MainPostJSONRetrofit.this, error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("Erro7", "Erro | " + error.toString() + error.getMessage());

                    }
                }
        );





    }









    private JSONObject GerarJSON(){

        Log.d("JSONteste", " JSON 6:  ");




        //Obtendo dados do formulário
        User user = new User();
        user.setName(editTextName.getText().toString());
        user.setUserName(editTextUsername.getText().toString());
        user.setPassword(editTextPassword.getText().toString());
        user.setEmail(editTextEmail.getText().toString());






        JSONObject student = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        //for (int x=0; x<10;x++) { Caso queira utilizar um laço para gerar o JSON
            try {

                student.put("name", user.getName());
                student.put("username", user.getUserName());
                student.put("password", user.getPassword());
                student.put("email", user.getEmail());

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("JSONteste", " JSON 4:  " + e.getMessage() + e.getCause());
            }
            jsonArray.put(student);
        //}

        /*
        JSONObject student2 = new JSONObject();
        try {
            student2.put("id", "2");
            student2.put("name", "NAME OF STUDENT2");
            student2.put("year", "4rd");
            student2.put("curriculum", "scicence");
            student2.put("birthday", "5/5/1993");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("JSONteste", " JSON 5:  " + e.getMessage() + e.getCause());
        }
        */

        Log.d("JSONteste", " JSON 1:  ");
        //JSONArray jsonArray = new JSONArray();


        //jsonArray.put(student1);
        //jsonArray.put(student2);


//-----Utilizando a classe JSONObject para serializar e desserializar o JSON
        JSONObject studentsObj = new JSONObject();
        try {
            studentsObj.put("user", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("JSONteste", " JSON 3:  " + e.getMessage() + e.getCause());
        }
//-----Utilizando a classe JSONObject para serializar e desserializar o JSON


        //-----Utilizando a classe GSON para serializar e desserializar o JSON
        Gson gson = new Gson();
        String str_json = gson.toJson(jsonArray);
        //-----Utilizando a classe GSON para serializar e desserializar o JSON


        String jsonStr = studentsObj.toString();


        //System.out.println("jsonString: "+jsonStr);

        Log.d("JSONteste", " JSON 2:  " + jsonStr);
        Log.d("JSONteste", " JSON 2.A:  " + str_json.toString());

        return studentsObj;

    }











    private String GerarJSONV2(){

        Log.d("JSONteste", " JSON 6:  ");




        //Obtendo dados do formulário
        User user = new User();
        user.setName(editTextName.getText().toString());
        user.setUserName(editTextUsername.getText().toString());
        user.setPassword(editTextPassword.getText().toString());
        user.setEmail(editTextEmail.getText().toString());






        JSONObject student = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        //for (int x=0; x<10;x++) { Caso queira utilizar um laço para gerar o JSON
        try {

            student.put("name", user.getName());
            student.put("username", user.getUserName());
            student.put("password", user.getPassword());
            student.put("email", user.getEmail());

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("JSONteste", " JSON 4:  " + e.getMessage() + e.getCause());
        }
        jsonArray.put(student);
        //}

        /*
        JSONObject student2 = new JSONObject();
        try {
            student2.put("id", "2");
            student2.put("name", "NAME OF STUDENT2");
            student2.put("year", "4rd");
            student2.put("curriculum", "scicence");
            student2.put("birthday", "5/5/1993");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("JSONteste", " JSON 5:  " + e.getMessage() + e.getCause());
        }
        */

        Log.d("JSONteste", " JSON 1:  ");
        //JSONArray jsonArray = new JSONArray();


        //jsonArray.put(student1);
        //jsonArray.put(student2);


//-----Utilizando a classe JSONObject para serializar e desserializar o JSON
        JSONObject studentsObj = new JSONObject();
        try {
            studentsObj.put("user", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("JSONteste", " JSON 3:  " + e.getMessage() + e.getCause());
        }
//-----Utilizando a classe JSONObject para serializar e desserializar o JSON


        //-----Utilizando a classe GSON para serializar e desserializar o JSON
        Gson gson = new Gson();
        String str_json = gson.toJson(jsonArray);
        //-----Utilizando a classe GSON para serializar e desserializar o JSON


        String jsonStr = studentsObj.toString();


        //System.out.println("jsonString: "+jsonStr);

        Log.d("JSONteste", " JSON 2:  " + jsonStr);
        Log.d("JSONteste", " JSON 2.A:  " + str_json.toString());

        return jsonStr;

    }













    private JSONObject GerarJSONTeste(){


        Log.d("JSONteste", " JSON 6:  ");

        JSONObject student = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (int x=0; x<10;x++) {
            try {
                student.put("id", "3 "+x);
                student.put("name", "NAME OF STUDENT "+x);
                student.put("year", x + "rd "+x);
                student.put("curriculum", "Arts "+x);
                student.put("birthday", "5/5/1993 "+x);

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("JSONteste", " JSON 4:  " + e.getMessage() + e.getCause());
            }
            jsonArray.put(student);
        }

        /*
        JSONObject student2 = new JSONObject();
        try {
            student2.put("id", "2");
            student2.put("name", "NAME OF STUDENT2");
            student2.put("year", "4rd");
            student2.put("curriculum", "scicence");
            student2.put("birthday", "5/5/1993");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("JSONteste", " JSON 5:  " + e.getMessage() + e.getCause());
        }
        */

        Log.d("JSONteste", " JSON 1:  ");
        //JSONArray jsonArray = new JSONArray();


        //jsonArray.put(student1);
        //jsonArray.put(student2);

        JSONObject studentsObj = new JSONObject();
        try {
            studentsObj.put("Students", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("JSONteste", " JSON 3:  " + e.getMessage() + e.getCause());
        }


        //-----Utilizando a classe GSON para serializar e desserializar o JSON
        Gson gson = new Gson();
        String str_json = gson.toJson(jsonArray);
        //-----Utilizando a classe GSON para serializar e desserializar o JSON


        String jsonStr = studentsObj.toString();


        //System.out.println("jsonString: "+jsonStr);

        Log.d("JSONteste", " JSON 2:  " + jsonStr);
        Log.d("JSONteste", " JSON 2.A:  " + str_json.toString());

        return studentsObj;

    }













}
