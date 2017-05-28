package com.example.jourdanrodrigues.controk;

public class StockFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 4;

    public int getFragment() {
        return R.layout.fragment_stock;
    }

    public int getTitle() {
        return R.string.stock_title;
    }

    public StockFragment() {

    }

    public static StockFragment newInstance() {
        return new StockFragment();
    }
}
