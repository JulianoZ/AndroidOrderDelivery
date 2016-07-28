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

    public static int getDataInt(String column, Cursor cursor){ return cursor.getInt(cursor.getColumnIndex(column)); }
    public static String getDataString(String column, Cursor cursor){ return cursor.getString(cursor.getColumnIndex(column)); }
    public static float getDataFloat(String column, Cursor cursor){ return cursor.getFloat(cursor.getColumnIndex(column)); }


}
