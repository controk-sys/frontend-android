package com.example.jourdanrodrigues.controk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SupplierFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 2;

    public SupplierFragment() {

    }

    public static SupplierFragment newInstance() {
        return new SupplierFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supplier, container, false);
    }
}
