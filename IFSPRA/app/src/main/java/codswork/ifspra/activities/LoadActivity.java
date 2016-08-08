package codswork.ifspra.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import codswork.ifspra.Controller;
import codswork.ifspra.R;
import codswork.ifspra.RestInterface;
import codswork.ifspra.dao.DAOProduct;
import codswork.ifspra.database.Database;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoadActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_load);

    }

    @Override
    protected void onResume(){
        super.onResume();

        boolean logged_in = true;

        final ProgressDialog loading = ProgressDialog.show(LoadActivity.this, "Carregando Dados", "Por favor, aguarde...", false, false);

        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Controller.EndPointWsRest).build();
        RestInterface productApi = restAdapter.create(RestInterface.class);

        productApi.getWSRestProduct(new Callback<List<DAOProduct>>() {
            @Override
            public void success(List<DAOProduct> products, Response response) {

                Controller.ProductsList = (ArrayList) products;
                for (DAOProduct p: Controller.ProductsList) {
                    Database db = Database.getInstance(getApplicationContext());
                    if(db.getProduct(p.getIdProduct())==null) {
                        db.createProduct(p);
                    }

                    final DAOProduct product = p;
                    Target loadTarget = new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            Controller.ProductsBitmapList.put(product.getIdProduct(), bitmap);
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) { }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) { }
                    };

                    Picasso.with(getApplicationContext())
                            .load(Controller.EndPointWsRest + "/Images/Products/" + p.getPicture1())//.resize(150, 150)
                            .into(loadTarget);
                }
                loading.dismiss();

            }

            @Override
            public void failure(RetrofitError error) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(),
                        "Não foi possivel carregar os dados no APP - "
                                + error.getMessage() + error.getCause(),
                        Toast.LENGTH_LONG).show();
            }
        });

        if(logged_in) {
            Intent i = new Intent(LoadActivity.this, MainActivity.class);
            startActivity(i);

        }else{

        }
    }

}
