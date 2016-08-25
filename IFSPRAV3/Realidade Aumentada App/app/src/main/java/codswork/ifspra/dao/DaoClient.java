package codswork.ifspra.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import codswork.ifspra.Controller;
import codswork.ifspra.database.Database;
import codswork.ifspra.pojo.Client;

/**
 * Created by Juliano on 16/08/2016.
 */
public class DaoClient {


    View myView;
    Context context;

    private SQLiteDatabase conn;

    public DaoClient(SQLiteDatabase conn){
        this.conn = conn;

    }



public DaoClient(){

}


    public boolean createClient(Context c, Client client){
        Database db = Database.getInstance(c.getApplicationContext());
        String query = "INSERT INTO client(idClient, Name, Email, " +
                "Password, StreetName, Complement, " +
                "Number, ZipCode, NameNeighborhood, " +
                "NameCity, NameState) VALUES('" +
                + client.getIdClient() + "',' "
                + client.getName()+ "',' "
                + client.getEmail() + "',' "
                + client.getPassword() + "',' "
                + client.getStreetName() + "',' "
                + client.getComplement() + "',' "
                + client.getNumber() + "',' "
                + client.getZipCode() + "',' "
                + client.getNameNeighborhood() + "',' "
                + client.getNameCity() + "',' "
                + client.getNameCity()
                + "')";
        //Log.d("DB-Get", query);
        try{
            db.getWritableDatabase().execSQL(query); db.close(); return true;
        }catch(Exception e){
            Log.d(" Erro na inserção SQL: ", e.getLocalizedMessage() + e.getStackTrace() + e.getMessage()+ e.getCause());
            return false;
        }
    }






    public void InserirObjCli(Client objCli){

  //Forma de cadastro usando o método insert e passando os parametros via ContentValues

        try{

            ContentValues values = preencheContentValues(objCli); // ContentValues é uma classe nativa Android
            conn.insertOrThrow(Controller.Par_Client, null, values); // Controller.Par_Client: referente a tabela do banco client

        }catch (Exception e){

            //MessageBox.show(context, "Erro", "Erro ao salvar os dados: " + e.getMessage());
            Log.d(" Erro na inserção: ", e.getLocalizedMessage() + e.getStackTrace() + e.getMessage()+ e.getCause());

        }
    }


    public void DeleteClientData(int id){

        try{

        Log.d("id Delete Method ", String.valueOf(id));

        conn.delete(Controller.Par_Client, "idClient = ?", new String[]{String.valueOf(id)});

        Controller.AuthenticationJsonData = false; //Get data of client
        Controller.idClient = 0;
        Controller.Name = "";
        Controller.Number = "";
        Controller.ZipCode = "";
        Controller.NameNeighborhood = "";
        Controller.NameCity = "";
        Controller.Complement = "";
        Controller.StreetName = "";


        }catch (Exception e){

            //MessageBox.show(context, "Erro", "Erro ao salvar os dados: " + e.getMessage());
            Log.d(" Erro na deleção: ", e.getLocalizedMessage() + e.getStackTrace() + e.getMessage());

        }

    }





    private ContentValues preencheContentValues(Client objCli){
        ContentValues values = new ContentValues();


        //Get the field name of database to generate the insert query: Client.NAME is the real field of Name
        values.put(Client.NAME, objCli.getName());
        values.put(Client.EMAIL, objCli.getEmail());
        values.put(Client.PASSWORD, objCli.getPassword());
        values.put(Client.STREETNAME, objCli.getStreetName());
        values.put(Client.NUMBER, objCli.getNumber());
        values.put(Client.COMPLEMENT, objCli.getComplement());
        values.put(Client.ZIPCODE, objCli.getZipCode());
        values.put(Client.NAMENeighborhood, objCli.getNameNeighborhood());
        values.put(Client.NAMECity, objCli.getNameCity());
        values.put(Client.NAMEState, objCli.getNameState());
        values.put(Client.IDCLIENT, objCli.getIdClient());


        return values;
    }


    public Client GetClient(Context context) {
        Client ObjClient = new Client();

try {

    Cursor cursor = conn.query(Controller.Par_Client, null, null, null, null, null, null);
    if (cursor.getCount() > 0) {
        cursor.moveToFirst();
        do {

            ObjClient.setIdClient(cursor.getInt(cursor.getColumnIndex(Client.IDCLIENT)));
            ObjClient.setName(cursor.getString(cursor.getColumnIndex(Client.NAME)));
            ObjClient.setEmail(cursor.getString(cursor.getColumnIndex(Client.EMAIL)));
            ObjClient.setPassword(cursor.getString(cursor.getColumnIndex(Client.PASSWORD)));
            ObjClient.setStreetName(cursor.getString(cursor.getColumnIndex(Client.STREETNAME)));
            ObjClient.setNumber(cursor.getString(cursor.getColumnIndex(Client.NUMBER)));
            ObjClient.setComplement(cursor.getString(cursor.getColumnIndex(Client.COMPLEMENT)));
            ObjClient.setZipCode(cursor.getString(cursor.getColumnIndex(Client.ZIPCODE)));
            ObjClient.setNameNeighborhood(cursor.getString(cursor.getColumnIndex(Client.NAMENeighborhood)));
            ObjClient.setNameCity(cursor.getString(cursor.getColumnIndex(Client.NAMECity)));
            ObjClient.setNameCity(cursor.getString(cursor.getColumnIndex(Client.NAMEState)));


        } while (cursor.moveToNext());

    }
}catch (Exception e){
    Log.d("DaoClient GetCl Error: ", e.getMessage() + e.getStackTrace());
}
        return ObjClient;
    }









    public static int getClientId(Context context, int id){
        int IdC=0;
        Database db = Database.getInstance(context.getApplicationContext());
        String query = "SELECT * FROM client WHERE idClient=" + id;
        //Log.d("DB-Get", query);
        Cursor c = db.getWritableDatabase().rawQuery(query,null);
        //Client p = new Client();
        if(c!=null && c.moveToFirst()){

            //p.setIdClient(Database.getDataInt("idClient", c));
            IdC = Database.getDataInt("idClient", c);

            c.close();
            db.close();
        }else{
            //p.setIdClient(0);
            IdC = 0;
        }


        return IdC; //Whether IdC worth zero the idClient id don't exists in database
    }






}
