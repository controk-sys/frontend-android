package com.example.jourdanrodrigues.controk.Client;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jourdanrodrigues.controk.BaseActivity;
import com.example.jourdanrodrigues.controk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientCreationActivity extends BaseActivity {
    private String[] mPlaceOptions;
    private SparseArray<String> mPlaceOptionsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_creation);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.client_creation_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setPlaceOptions();
    }

    private void setPlaceOptions() {
        final AppCompatActivity activity = this;
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getResources().getString(R.string.web_service_api) + "place_options/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        buildPlaceOptions(new JSONArray(response));
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, mPlaceOptions);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ((Spinner) findViewById(R.id.place_option_field)).setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Snackbar.make(findViewById(R.id.client_creation), "Error gathering data", Snackbar.LENGTH_INDEFINITE)
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
