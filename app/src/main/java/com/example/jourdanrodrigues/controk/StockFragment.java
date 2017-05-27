package com.example.jourdanrodrigues.controk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class StockFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 4;

    public StockFragment() {

    }

    public static StockFragment newInstance() {
        return new StockFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stock, container, false);
    }
}
