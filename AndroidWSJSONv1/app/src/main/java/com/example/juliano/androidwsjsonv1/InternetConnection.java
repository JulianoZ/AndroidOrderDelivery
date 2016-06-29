package com.example.juliano.androidwsjsonv1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Juliano on 12/03/2016.
 */
public class InternetConnection {


    public  static boolean IsOnline(Context ctx) {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }





    //---------Método alternativo para verificação de conexão com a Internet
    public static boolean verificaConexao(Context ctx) {
        boolean StatusRede;

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);//Pego a conectividade do contexto o qual o metodo foi chamado
        NetworkInfo netInfo = cm.getActiveNetworkInfo();//Crio o objeto netInfo que recebe as informacoes da NEtwork
        if((netInfo!=null)&&(netInfo.isConnectedOrConnecting())&&(netInfo.isAvailable()))

        { //Se o objeto for nulo ou nao tem conectividade retorna false
            Log.d("msg10", "Mensagem de debug " + true);
            StatusRede = true;
        }
        else
        {
            Log.d("msg10", "Mensagem de debug " + false);
            StatusRede = false;
        }
        return StatusRede;
    }
    //---------Método alternativo para verificação de conexão com a Internet



}
