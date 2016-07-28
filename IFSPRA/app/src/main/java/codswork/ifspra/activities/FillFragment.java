package codswork.ifspra.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import codswork.ifspra.CartInterface;
import codswork.ifspra.Controller;
import codswork.ifspra.R;
import codswork.ifspra.adapters.OrderedProductAdapter;
import codswork.ifspra.adapters.ProductAdapter;
import codswork.ifspra.pojo.Product;

/**
 * Created by Felipe on 12/07/2016.
 */
public class FillFragment extends Fragment{

    View myView;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //if(Controller.isFastBuyChecked)
        //    menu.findItem(R.id.check_buy).setChecked(true);
        //if(Controller.isFastRemChecked)
        //    menu.findItem(R.id.check_rem).setChecked(true);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView  = inflater.inflate(R.layout.fragment_fill, container, false);
        // setHasOptionsMenu(true);
        return myView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");

        Controller.active_id = -2;
    }

}
