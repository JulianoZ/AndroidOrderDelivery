package codswork.ifspra;

/**
 * Created by Juliano on 16/02/2016.
 */

import java.util.List;

import codswork.ifspra.dao.DAOProduct;
import codswork.ifspra.pojo.Product;
import retrofit.Callback;
import retrofit.http.GET;


public interface RestInterface {





        @GET("/WebService/ProductList")
        public void getWSRestProduct(Callback<List<DAOProduct>> response);













}
