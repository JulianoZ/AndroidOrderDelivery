package codswork.ifspra;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;

import codswork.ifspra.pojo.Ordered;
import codswork.ifspra.pojo.Product;

/**
 * Created by Felipe on 12/07/2016.
 */
public class Controller {

    //public static boolean progress = false;

    public static boolean isFastBuyChecked;// = false;
    public static boolean isFastRemChecked;// = false;

    public static int active_id = -2;

    public static final String EndPointWsRest = "http://julianoblanco-001-site3.ctempurl.com";

    public static ArrayList<Product> ProductsList = new ArrayList<>();

    public static HashMap<Integer, Bitmap> ProductsBitmapList = new HashMap<>();

    public static Ordered Carrinho = new Ordered(); //Forma temporaria de manusear o carrinho

    public static Target getTarget(final Product p){

        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                final Bitmap b = bitmap;
                Controller.ProductsBitmapList.put(p.getIdProduct(), b);
            }
            @Override
            public void onBitmapFailed(Drawable errorDrawable) { }
            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) { }
        };

    }

    public static void LoadImage(Context c, Target t, String url){

        Picasso.with(c)
                .load(url)//.resize(150, 150)
                .into(t);
    }


    public static void saveCount(Context c){
        SharedPreferences settings = c.getSharedPreferences("PrefFile", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("OrderedIdCount", Ordered.id_count);
        editor.putBoolean("isFastBuyChecked", isFastBuyChecked);
        editor.putBoolean("isFastRemChecked", isFastRemChecked);
        editor.commit();
    }

    public static int getCount(Context c){
        SharedPreferences settings = c.getSharedPreferences("PrefFile", 0);

        isFastRemChecked = settings.getBoolean("isFastRemChecked", false);
        isFastBuyChecked = settings.getBoolean("isFastBuyChecked", false);

        return settings.getInt("OrderedIdCount", 0);
    }

    public static void vibrateShort(Context c){
        Vibrator vibe = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(100);
    }
    public static void vibrateLong(Context c){
        Vibrator vibe = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(300);
    }


    /*
    public static void LoadJSONintoDB(final Activity context){
        //progress = false;

        final ProgressDialog loading = ProgressDialog.show(context, "Carregando Dados", "Por favor, aguarde...", false, false);

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(EndPointWsRest).build();
        RestInterface productApi = restAdapter.create(RestInterface.class);

            productApi.getWSRestProduct(new Callback<List<DAOProduct>>() {
                @Override
                public void success(List<DAOProduct> products, Response response) {

                    ProductsList = (ArrayList) products;
                    for (DAOProduct p: ProductsList) {
                        Database db = Database.getInstance(context.getApplicationContext());
                        if(db.getProduct(p.getIdProduct())==null) {
                            db.createProduct(p);
                        }

                        final DAOProduct product = p;
                        Target loadTarget = new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                ProductsBitmapList.put(product.getIdProduct(), bitmap);
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) { }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) { }
                        };

                        Picasso.with(context.getApplicationContext())
                                .load(EndPointWsRest + p.getPicture1()).resize(150, 150)
                                .into(loadTarget);
                    }
                    loading.dismiss();

                }

                @Override
                public void failure(RetrofitError error) {
                    loading.dismiss();
                    Toast.makeText(context.getApplicationContext(),
                            "Não foi possivel carregar os dados no APP - "
                                    + error.getMessage() + error.getCause(),
                            Toast.LENGTH_LONG).show();
                }
            });

        //progress = true;

    }

    */
}