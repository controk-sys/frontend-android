package com.example.jourdanrodrigues.controk.Address;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jourdanrodrigues.controk.BaseFragmentCreation;
import com.example.jourdanrodrigues.controk.BasePersonCreationActivity;
import com.example.jourdanrodrigues.controk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddressCreationFragment extends BaseFragmentCreation {
    private String[] mPlaceOptions;
    private HashMap<String, Integer> mPlaceOptionsMap = null;
    private Spinner mPlaceOption;
    private EditText mPlaceName;
    private EditText mNumber;
    private EditText mComplement;
    private EditText mNeighborhood;
    private EditText mCity;
    private EditText mState;
    private EditText mCep;

    public AddressCreationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        assert view != null;

        setActionSendListener();

        view.findViewById(R.id.fab_finish_creation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!errorInFields()) {
                    performCreation();
                }
            }
        });

        setPlaceOptions();

        return view;
    }

    private void performCreation() {
        BasePersonCreationActivity activity = (BasePersonCreationActivity) getActivity();
        activity.mAddress = new Address(
            mPlaceOptionsMap.get(mPlaceOption.getSelectedItem().toString()),
            mPlaceName.getText().toString(),
            Integer.parseInt(mNumber.getText().toString()),
            mComplement.getText().toString(),
            mNeighborhood.getText().toString(),
            mCity.getText().toString(),
            mState.getText().toString(),
            mCep.getText().toString()
        );
        activity.performCreation(this);
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
        if (mPlaceOptions == null) {
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

    @Override
    protected Boolean errorInFields() {
        return (mPlaceName.getError() != null || mNumber.getError() != null ||
            mComplement.getError() != null || mNeighborhood.getError() != null ||
            mCity.getError() != null || mState.getError() != null || mCep.getError() != null);
    }

    @Override
    protected void initializeFields(View view) {
        mPlaceOption = (Spinner) view.findViewById(R.id.place_option_field);
        mPlaceName = ((TextInputLayout) view.findViewById(R.id.place_name_field)).getEditText();
        mNumber = ((TextInputLayout) view.findViewById(R.id.address_number_field)).getEditText();
        mComplement = ((TextInputLayout) view.findViewById(R.id.address_complement_field)).getEditText();
        mNeighborhood = ((TextInputLayout) view.findViewById(R.id.address_neighborhood_field)).getEditText();
        mCity = ((TextInputLayout) view.findViewById(R.id.address_city_field)).getEditText();
        mState = ((TextInputLayout) view.findViewById(R.id.address_state_field)).getEditText();
        mCep = ((TextInputLayout) view.findViewById(R.id.address_cep_field)).getEditText();

        setEmptyFieldValidations(mPlaceName);
        setEmptyFieldValidations(mNumber);
        setEmptyFieldValidations(mComplement);
        setEmptyFieldValidations(mNeighborhood);
        setEmptyFieldValidations(mCity);
        setEmptyFieldValidations(mState);
        setEmptyFieldValidations(mCep);
    }

    private void setActionSendListener() {
        mCep.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    performCreation();
                    return true;
                }
                return false;
            }
        });
    }
}
