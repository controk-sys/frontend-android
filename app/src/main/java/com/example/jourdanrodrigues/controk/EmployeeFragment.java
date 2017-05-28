package com.example.jourdanrodrigues.controk;

public class EmployeeFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 1;

    public int getFragment() {
        return R.layout.fragment_employee;
    }

    public int getTitle() {
        return R.string.employee_title;
    }

    public EmployeeFragment() {

    }

    public static EmployeeFragment newInstance() {
        return new EmployeeFragment();
    }
}
