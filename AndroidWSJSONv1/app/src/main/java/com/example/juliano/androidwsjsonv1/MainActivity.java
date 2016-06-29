package com.example.juliano.androidwsjsonv1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.juliano.androidwsjsonv1.POJO.Flower;
import com.example.juliano.androidwsjsonv1.POJO.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;







//public class MainActivity extends ListActivity {
//public class MainActivity extends AppCompatActivity{
public class MainActivity extends Activity {


    private Button btn, btn2, btn3, btn4, btn5, btn6, btnWSRest;
    private ListView listView;
    private static final String TAG = "Manipulação de array "; //manipulação de erro

    String[] mobileArray2 = {"São Paulo", "Corinthians", "Palmeiras", "Santos", "Flamengo", "Vasco", "Botafogo", "Bahia", "Portuguesa", "Ceara"};
    String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    ArrayAdapter adapter;
    ArrayAdapter adapter2;


    public static final String EndPoint = "http://services.hanselandpetal.com";
    public static final String EndPointWsRest = "http://julianoblanco-001-site3.ctempurl.com";
    //public static final String EndPoint = "http://34.60.236.154";

    List<Flower> flowerList;
    List<Product> productList;


    private ArrayAdapter<String> FlowerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mobileArray2);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mobileArray);
        //listView = (ListView) findViewById(R.id.list);
        //adapter = new ArrayAdapter<String>(this, R.layout.activity_main, mobileArray);


        listView = (ListView) findViewById(R.id.list);


        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestData();
            }
        });



        btnWSRest = (Button) findViewById(R.id.btnWSRest);
        btnWSRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requestDataWSRest();
            }
        });


        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listView.setAdapter(adapter2);

            }
        });


        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                listView.setAdapter(adapter);


                if (InternetConnection.verificaConexao(getApplicationContext())) {
                    Toast.makeText(getApplicationContext(), "Você está conectado ", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(getApplicationContext(), "Você NÃO está conectado ", Toast.LENGTH_SHORT).show();
                }


            }
        });



        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                try {
                    GerarJson();

                }catch (IOException e) {


                } catch (JSONException e) {
                    e.printStackTrace();
                }





          /*
                try {
                    GerarJson();
                    //Toast.makeText(getApplicationContext(), "Gerando JSON: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("ErroJson", "Erro :   " + e.getMessage() + e.getCause());
                }
                */


            }
        });






    btn5 = (Button) findViewById(R.id.btn5);
    btn5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent it = new Intent(getApplicationContext(), MainPOSTRetrofit.class);
            //startActivity(it);
            startActivityForResult(it, 0); //Quando o ActCadContato for finalizado é chamado o onActivityResult
            // O 0 é o código do ActCadContatos







        }
    });




        btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent it = new Intent(getApplicationContext(), MainPostJSONRetrofit.class);
                //startActivity(it);
                startActivityForResult(it, 0); //Quando o ActCadContato for finalizado é chamado o onActivityResult
                // O 0 é o código do ActCadContatos







            }
        });


}


    private void requestData() {

       //  if (InternetConnection.IsOnline(this)) { //Verifica se o dispositivo possui acesso a Internet
            final ProgressDialog loading = ProgressDialog.show(this, "Carregando Dados", "Por favor, aguarde...", false, false); //Barra de carregamento
            final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint(EndPoint).build();
            RestInterface flowerapi = restadapter.create(RestInterface.class);

            flowerapi.getJSON(new Callback<List<Flower>>() {
                @Override

                public void success(List<Flower> flowers, Response response) {

                    //Loading
                    //Dismissing the loading progressbar | Utilizando barra de carregamento
                    loading.dismiss();

                    flowerList = flowers;
                    adapter adapt = new adapter(getApplicationContext(), R.layout.item_file, flowerList);

                    listView.setAdapter(adapt);

                    //-----Obtendo os dados da list<Flower> através do modelo.
                    for (int x = 0; x < flowerList.size(); x++) {
                        Flower objFlower = flowerList.get(x);

                        Log.d(TAG, "Mensagem de debug" + objFlower.getName());
                    }
                    //-----Obtendo os dados da list<Flower> através do modelo.

                }

                @Override
                public void failure(RetrofitError error) {
                    //listView.setAdapter(adapter);
                    Log.d(TAG, "Erro | mensagem de debug:   " + error.getCause() + error.getMessage() + error.getUrl());
                    Toast.makeText(getApplicationContext(), "Failed não conseguiu ler os dados " + error.getMessage() + error.getCause(), Toast.LENGTH_SHORT).show();


                }
            });

        //}else{
           // Toast.makeText(getApplicationContext(), "Você não possui acesso a Internet ", Toast.LENGTH_SHORT).show();
       // }


    }







    private void requestDataWSRest() {

        //  if (InternetConnection.IsOnline(this)) { //Verifica se o dispositivo possui acesso a Internet
        final ProgressDialog loading = ProgressDialog.show(this, "Carregando Dados", "Por favor, aguarde...", false, false); //Barra de carregamento
        final RestAdapter restadapter = new RestAdapter.Builder().setEndpoint(EndPointWsRest).build();
        RestInterface productApi = restadapter.create(RestInterface.class);

        productApi.getWSRestProduct(new Callback<List<Product>>() {
            @Override

            public void success(List<Product> products, Response response) {

                //Loading
                //Dismissing the loading progressbar | Utilizando barra de carregamento
                loading.dismiss();

                productList = products;
                adapterProduct adapt = new adapterProduct(getApplicationContext(), R.layout.item_file, productList);

                listView.setAdapter(adapt);

                //-----Obtendo os dados da list<Flower> através do modelo.
                /*
                for (int x = 0; x < flowerList.size(); x++) {
                    Flower objFlower = flowerList.get(x);

                    Log.d(TAG, "Mensagem de debug" + objFlower.getName());
                }
                */
                //-----Obtendo os dados da list<Flower> através do modelo.

            }

            @Override
            public void failure(RetrofitError error) {
                //listView.setAdapter(adapter);
                Log.d(TAG, "Erro | mensagem de debug:   Failed não conseguiu ler os dados "  + error.getCause() + error.getMessage() + error.getUrl() + error.getResponse() + error.getStackTrace()+ error.getKind());
                Toast.makeText(getApplicationContext(), "Failed não conseguiu ler os dados " + error.getMessage() + error.getCause(), Toast.LENGTH_SHORT).show();


            }
        });

        //}else{
        // Toast.makeText(getApplicationContext(), "Você não possui acesso a Internet ", Toast.LENGTH_SHORT).show();
        // }


    }



























    //public JSONObject GerarJson() throws IOException, JSONException {

    public void GerarJson() throws IOException, JSONException {

        /*

        JSONObject json = new JSONObject();
        JSONObject manJson = new JSONObject();

        manJson.put("name", true);
        manJson.put("username", "emil111");
        manJson.put("age", "111");
        json.put("man",manJson);
        */

        JSONArray data = new JSONArray();
        JSONObject tour;

        tour = new JSONObject();
        tour.put("name", true);
        tour.put("username", "emil111");
        tour.put("age", "111");

        data.put(tour);
        String texto = data.toString();
        Toast.makeText(getApplicationContext(), "JSON " + texto, Toast.LENGTH_SHORT).show();
        //Fonte: http://www.lynda.com/Android-tutorials/Creating-reading-JSON-data-files/112584/121170-4.html


        //return tour;

    }








/*
    public static boolean isReachable() throws IOException
    {

        String targetUrl = "http://www.google.com";
        HttpURLConnection httpUrlConnection = (HttpURLConnection) new URL(
                targetUrl).openConnection();
        httpUrlConnection.setRequestMethod("HEAD");

        try
        {
            int responseCode = httpUrlConnection.getResponseCode();

            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (UnknownHostException noInternetConnection)
        {
            return false;
        }
    }





        public boolean CheckServer() {
            String urlPath = "http://www.terra.com.br/";
            boolean reachable = true;
            try {
                Log.d("ErroServer", "Erro | mensagem de debug1:   " + InetAddress.getByName(urlPath).isReachable(2000));
                reachable = InetAddress.getByName(urlPath).isReachable(2000);
            } catch (UnknownHostException e) {
                Log.d("ErroServer", "Erro | mensagem de debug2:   " + e.getMessage());

            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.d("ErroServer", "Erro | mensagem de debug3:   " + e.getMessage());
            }

            if (reachable == true) {
                return reachable;
            } else {
                return reachable;
            }
        }




    public boolean isConnectedToServer() {
        try{
            URL myUrl = new URL("http://www.google.com");
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(15000);
            connection.connect();
            return true;
        } catch (Exception e) {
            Log.d("ErroServer1", "Erro | mensagem de debug3:   " + e.getMessage() + e.getCause());
            return false;
        }
    }






    public boolean CheckServer2() {
        boolean error = false;
        int errorcode;
        StringBuilder sbParams = new StringBuilder();
        try {
            URL urlObj = new URL("http://google.com.br");

            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

            conn.setDoOutput(true);

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Accept-Charset", "UTF-8");

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            conn.connect();

            String paramsString = sbParams.toString();

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(paramsString);
            wr.flush();
            wr.close();

            Log.d("ErroServer", "Erro | 12:   " + paramsString);
            error = true;

        } catch (IOException e) {
            e.printStackTrace();

            error = false;
            errorcode = 1;
            Log.d("ErroServer", "Erro | 13:   " + e.getMessage());

        }
        return  error;


    }



    public JSONObject makeHttpRequest(String url, String method, HashMap<String, String> params) {

        sbParams = new StringBuilder();
        boolean error = false;
        errorcode = 0;


        int i = 0;


        for (String key : params.keySet()) {
            try {
                if (i != 0) {
                    sbParams.append("&");
                }
                sbParams.append(key).append("=")
                        .append(URLEncoder.encode(params.get(key), charset));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
            i++;
        }























    public static String readStream (InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

*/





}
