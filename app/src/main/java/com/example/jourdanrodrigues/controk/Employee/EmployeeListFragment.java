package com.example.jourdanrodrigues.controk.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jourdanrodrigues.controk.BaseFragment;
import com.example.jourdanrodrigues.controk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListFragment extends BaseFragment {
    public static final int ARG_MENU_POSITION = 1;
    private RecyclerView mEmployeeList;
    private EmployeeAdapter.Listener mEmployeeClickListener;
    private SwipeRefreshLayout mSwipeRefresh;

    public EmployeeListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        view.findViewById(R.id.fab_create_employee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                Intent intent = new Intent(activity, EmployeeCreationActivity.class);
                Bundle options = ActivityOptionsCompat.makeCustomAnimation(activity, R.anim.enter, R.anim.exit).toBundle();
                startActivity(intent, options);
            }
        });

        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_employee);
        mSwipeRefresh.setRefreshing(true);

        mEmployeeList = (RecyclerView) view.findViewById(R.id.recycler_view_employee);
        mEmployeeClickListener = new EmployeeAdapter.Listener() {
            @Override
            public void onClick(Employee employee) {
                // Implementation for employee
                Snackbar.make(view, "Clicked \"" + employee.getName() + "\"", Snackbar.LENGTH_SHORT).show();
            }
        };

        setSwipeRefresh(view);
        populateEmployees(view);

        return view;
    }

    @Override
    public int getFragment() {
        return R.layout.fragment_employee_list;
    }

    @Override
    public int getTitle() {
        return R.string.employee_list_title;
    }

    private void setSwipeRefresh(final View view) {
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateEmployees(view);
            }
        });
    }

    private void populateEmployees(final View view) {
        final List<Employee> employees = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        String url = getResources().getString(R.string.web_service_api) + "employees/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray employeesList = new JSONArray(response);
                        for (int i = 0; i < employeesList.length(); i++) {
                            JSONObject employee = (JSONObject) employeesList.get(i);
                            employees.add(new Employee(
                                employee.getString("name"),
                                employee.getString("email"),
                                employee.getString("cpf"),
                                employee.getString("role"),
                                employee.getString("observation")
                            ));
                        }

                        mEmployeeList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        mEmployeeList.setAdapter(new EmployeeAdapter(employees, mEmployeeClickListener));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {
                        mSwipeRefresh.setRefreshing(false);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Snackbar.make(view, "Couldn't retrieve employees", Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.try_again, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSwipeRefresh.setRefreshing(true);
                                populateEmployees(v);
                            }
                        }).show();
                    mSwipeRefresh.setRefreshing(false);
                }
            });
        queue.add(stringRequest);
    }
}
