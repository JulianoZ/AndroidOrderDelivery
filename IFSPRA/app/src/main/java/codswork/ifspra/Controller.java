package codswork.ifspra;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import codswork.ifspra.activities.LoadActivity;
import codswork.ifspra.dao.DAOProduct;
import codswork.ifspra.database.Database;
import codswork.ifspra.pojo.Product;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Felipe on 12/07/2016.
 */
public class Controller {

    //public static boolean progress = false;

    public static final String EndPointWsRest = "http://julianoblanco-001-site3.ctempurl.com";

    public static ArrayList<DAOProduct> ProductsList = new ArrayList<>();

    public static HashMap<Integer, Bitmap> ProductsBitmapList = new HashMap<>();

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
