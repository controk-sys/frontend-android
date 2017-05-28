package com.example.jourdanrodrigues.controk;

public class SupplierFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 2;

    public int getFragment() {
        return R.layout.fragment_supplier;
    }

    public int getLabel() {
        return R.string.supplier_label;
    }

    public SupplierFragment() {

    }

    public static SupplierFragment newInstance() {
        return new SupplierFragment();
    }
}
