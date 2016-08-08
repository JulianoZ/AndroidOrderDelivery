package codswork.ifspra.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codswork.ifspra.R;

/**
 * Created by Felipe on 12/07/2016.
 */
public class CartFragment extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView  = inflater.inflate(R.layout.cart_layout, container, false);
        return myView;
    }
}
