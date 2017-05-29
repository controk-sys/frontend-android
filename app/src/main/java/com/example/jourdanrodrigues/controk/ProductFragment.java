package com.example.jourdanrodrigues.controk;

public class ProductFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 3;

    public int getFragment() {
        return R.layout.fragment_product;
    }

    public int getTitle() {
        return R.string.product_title;
    }

    public ProductFragment() {

    }
}
