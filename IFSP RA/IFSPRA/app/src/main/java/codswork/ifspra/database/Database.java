package codswork.ifspra.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;

import codswork.ifspra.dao.DAOProduct;
import codswork.ifspra.pojo.Product;

/**
 * Created by Felipe on 12/07/2016.
 */
public class Database extends SQLiteOpenHelper{

    private static final int db_version = 1;

    private Database(Context context) {
        super(context, "db", null,db_version );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS product(" +
                           "id INTEGER PRIMARY KEY, " +
                           "name VARCHAR(45)," +
                           "description VARCHAR(200)," +
                           "price FLOAT(10)," +
                           "short_description VARCHAR(50)," +
                           "stock INT(10)," +
                           "featured INT(1)," +
                           "weight FLOAT(10)," +
                           "picture VARCHAR(60)," +
                           "picture2 VARCHAR(60)," +
                           "subcat_id INT(5)" +
                       ");";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    private static Database sInstance;
    //SINGLETON PARAMETERS
    public static synchronized Database getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Database(context.getApplicationContext());
        }
        return sInstance;
    }
    /* ########################
       #         TOOLS        #
       ######################## */

    public int getDataInt(String column, Cursor cursor){ return cursor.getInt(cursor.getColumnIndex(column)); }
    public String getDataString(String column, Cursor cursor){ return cursor.getString(cursor.getColumnIndex(column)); }
    public float getDataFloat(String column, Cursor cursor){ return cursor.getFloat(cursor.getColumnIndex(column)); }

    /* ########################
       #   CRUD - Produtos    #
       ######################## */

    //CREATE
    public boolean createProduct(DAOProduct p){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO product(id, name, description, " +
                                             "price, short_description, stock, " +
                                             "featured, weight, picture, " +
                                             "picture2, subcat_id) VALUES('" +
                                             + p.getIdProduct() + "',' "
                                             + p.getName() + "',' "
                                             + p.getDescription() + "',' "
                                             + p.getPrice() + "',' "
                                             + p.getShortDescription() + "',' "
                                             + p.getStock() + "',' "
                                             + p.getFeatured() + "',' "
                                             + p.getWeight() + "',' "
                                             + p.getPicture1() + "',' "
                                             + p.getPicture2() + "',' "
                                             + p.getSubCategory_idSubCategory()
                + "')";
        Log.d("DB-Get", query);
        try{db.execSQL(query); db.close(); return true;}catch(Exception e){ return false;}
    }

    //READ
    public DAOProduct getProduct(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM product WHERE id=" + id;
        Log.d("DB-Get", query);
        Cursor c = db.rawQuery(query,null);
        if(c!=null && c.moveToFirst()){

            DAOProduct p = new DAOProduct(
                    getDataInt("id", c),
                    getDataString("name",c),
                    getDataString("description",c),
                    getDataFloat("price",c),
                    getDataString("short_description",c),
                    getDataInt("stock",c),
                    (getDataInt("featured",c)==1),
                    getDataFloat("weight",c),
                    getDataString("picture",c),
                    getDataString("picture2",c),
                    getDataInt("subcat_id",c)
                    );
            c.close();
            db.close();
            return p;
        }
        return null;
    }
    public DAOProduct getProduct(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM product WHERE name=" + name;
        Log.d("DB-Get", query);
        Cursor c = db.rawQuery(query,null);
        if(c!=null && c.moveToFirst()){

            DAOProduct p = new DAOProduct(
                    getDataInt("id", c),
                    getDataString("name",c),
                    getDataString("description",c),
                    getDataFloat("price",c),
                    getDataString("short_description",c),
                    getDataInt("stock",c),
                    (getDataInt("featured",c)==1),
                    getDataFloat("weight",c),
                    getDataString("picture",c),
                    getDataString("picture2",c),
                    getDataInt("subcat_id",c)
            );
            c.close();
            db.close();
            return p;
        }
        return null;
    }
    public ArrayList<DAOProduct> getProducts(){
        ArrayList<DAOProduct> temp = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM product";
        Log.d("DB-Get", query);
        Cursor c = db.rawQuery(query,null);
        if(c!=null && c.moveToFirst()){
            do {
                DAOProduct p = new DAOProduct(
                        getDataInt("id", c),
                        getDataString("name", c),
                        getDataString("description", c),
                        getDataFloat("price", c),
                        getDataString("short_description", c),
                        getDataInt("stock", c),
                        (getDataInt("featured", c) == 1),
                        getDataFloat("weight", c),
                        getDataString("picture", c),
                        getDataString("picture2", c),
                        getDataInt("subcat_id", c)
                );
                temp.add(p);
            }while (c.moveToNext());
            c.close();
            db.close();
        }
        return temp;
    }

    //UPDATE
    public boolean updateProduct(DAOProduct p){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE product SET "
                + "id=" + p.getIdProduct() + ", "
                + "name="+ p.getName() + ", "
                + "description="+ p.getDescription() + ", "
                + "price="+ p.getPrice() + ", "
                + "short_description="+ p.getShortDescription() + ", "
                + "stock="+ p.getStock() + ", "
                + "featured="+ p.getFeatured() + ", "
                + "weight="+ p.getWeight() + ", "
                + "picture="+ p.getPicture1() + ", "
                + "picture2="+ p.getPicture2() + ", "
                + "subcat_id="+ p.getSubCategory_idSubCategory() + ", "
                + ") WHERE id=" + p.getIdProduct();
        Log.d("DB-Get", query);
        try{db.execSQL(query); db.close(); return true;}catch(Exception e){ return false;}
    }

    //DELETE
    public boolean deleteProduct(DAOProduct p){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM product WHERE id=" + p.getIdProduct();
        Log.d("DB-Get", query);
        try{db.execSQL(query); db.close(); return true;}catch(Exception e){ return false;}
    }
}
