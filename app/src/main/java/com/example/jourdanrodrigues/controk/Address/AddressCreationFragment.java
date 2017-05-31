package com.example.jourdanrodrigues.controk.Address;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.example.jourdanrodrigues.controk.BasePersonCreationActivity;
import com.example.jourdanrodrigues.controk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddressCreationFragment extends BaseFragment {
    private String[] mPlaceOptions;
    private HashMap<String, Integer> mPlaceOptionsMap;
    private Spinner mPlaceOption;
    private TextInputLayout mPlaceName;
    private TextInputLayout mNumber;
    private TextInputLayout mComplement;
    private TextInputLayout mNeighborhood;
    private TextInputLayout mCity;
    private TextInputLayout mState;
    private TextInputLayout mCep;

    public AddressCreationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        mPlaceOption = (Spinner) view.findViewById(R.id.place_option_field);
        mPlaceName = (TextInputLayout) view.findViewById(R.id.place_name_field);
        mNumber = (TextInputLayout) view.findViewById(R.id.address_number_field);
        mComplement = (TextInputLayout) view.findViewById(R.id.address_complement_field);
        mNeighborhood = (TextInputLayout) view.findViewById(R.id.address_neighborhood_field);
        mCity = (TextInputLayout) view.findViewById(R.id.address_city_field);
        mState = (TextInputLayout) view.findViewById(R.id.address_state_field);
        mCep = (TextInputLayout) view.findViewById(R.id.address_cep_field);

        final BasePersonCreationActivity activity = (BasePersonCreationActivity) getActivity();

        view.findViewById(R.id.fab_finish_creation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.mAddress = new Address(
                    mPlaceOptionsMap.get(mPlaceOption.getSelectedItem().toString()),
                    mPlaceName.getEditText().getText().toString(),
                    Integer.parseInt(mNumber.getEditText().getText().toString()),
                    mComplement.getEditText().getText().toString(),
                    mNeighborhood.getEditText().getText().toString(),
                    mCity.getEditText().getText().toString(),
                    mState.getEditText().getText().toString(),
                    mCep.getEditText().getText().toString()
                );
                Snackbar.make(view, "Successfully got here.", Snackbar.LENGTH_LONG).show();
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
        mPlaceOptionsMap = new HashMap<>();

        for (int i = 0; i < placeOptions.length(); i++) {
            JSONObject placeOption = (JSONObject) placeOptions.get(i);
            mPlaceOptionsMap.put(placeOption.getString("name"), placeOption.getInt("id"));
            mPlaceOptions[i] = placeOption.getString("name");
        }
    }
}
