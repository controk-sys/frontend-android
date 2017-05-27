package com.example.jourdanrodrigues.controk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EmployeeFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 1;

    public EmployeeFragment() {

    }

    public static EmployeeFragment newInstance() {
        return new EmployeeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee, container, false);
    }
}
