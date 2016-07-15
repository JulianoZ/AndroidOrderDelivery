package codswork.ifspra.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import codswork.ifspra.Controller;
import codswork.ifspra.R;
import codswork.ifspra.activities.DetailActivity;
import codswork.ifspra.dao.DAOProduct;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Juliano on 31/05/2016.
 */
public class ProductAdapter extends ArrayAdapter<DAOProduct> {

    String url="http://julianoblanco-001-site3.ctempurl.com/Images/Products/";
    private Context context;
    private List<DAOProduct> productList;


    public ProductAdapter(Context context, int resource, List<DAOProduct> objects) {
        super(context, resource, objects);
        this.context = context;
        this.productList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.product_item_file,parent,false);
        DAOProduct prod = productList.get(position);

        TextView tv = (TextView) view.findViewById(R.id.name);
        tv.setText(prod.getName());

        TextView tv2 = (TextView) view.findViewById(R.id.price);
        tv2.setText("R$ " + Double.toString(prod.getPrice()));

        ImageView img = (ImageView) view.findViewById(R.id.img);
        img.setImageBitmap(Controller.ProductsBitmapList.get(prod.getIdProduct()));
        //Picasso.with(context)
        //        .load(url + prod.getPicture1())//.resize(100, 100)
        //        .into(img);

        final int id = prod.getIdProduct();
        ImageButton info = (ImageButton)view.findViewById(R.id.btn_detail);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("id", id);
                getContext().startActivity(i);
            }
        });

        ImageButton add = (ImageButton)view.findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
