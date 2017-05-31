package com.example.jourdanrodrigues.controk.Address;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

public class AddressCreationFragment extends BaseFragment {
    private String[] mPlaceOptions;
    private SparseArray<String> mPlaceOptionsMap;

    public AddressCreationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        view.findViewById(R.id.fab_finish_creation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Successfuly got here.", Snackbar.LENGTH_LONG).show();
            }
        });

        setPlaceOptions();

        return view;
    }

    @Override
    public int getFragment() {
        return R.layout.fragment_address_creation;
    }

    @Override
    public int getTitle() {
        return R.string.address_creation_title;
    }

    private void setPlaceOptions() {
        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = getResources().getString(R.string.web_service_api) + "place_options/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        buildPlaceOptions(new JSONArray(response));
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, mPlaceOptions);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ((Spinner) activity.findViewById(R.id.place_option_field)).setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Snackbar.make(activity.findViewById(R.id.address_creation), "Error gathering data", Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.try_again, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setPlaceOptions();
                            }
                        }).show();
                }
            });
        queue.add(stringRequest);
    }

    private void buildPlaceOptions(JSONArray placeOptions) throws JSONException {
        mPlaceOptions = new String[placeOptions.length()];
        mPlaceOptionsMap = new SparseArray<>();

        for (int i = 0; i < placeOptions.length(); i++) {
            JSONObject placeOption = (JSONObject) placeOptions.get(i);
            mPlaceOptionsMap.put(placeOption.getInt("id"), placeOption.getString("name"));
            mPlaceOptions[i] = placeOption.getString("name");
        }
    }
}
