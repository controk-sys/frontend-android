package com.example.jourdanrodrigues.controk.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.example.jourdanrodrigues.controk.BaseFragmentCreation;
import com.example.jourdanrodrigues.controk.BasePersonCreationActivity;
import com.example.jourdanrodrigues.controk.MainActivity;
import com.example.jourdanrodrigues.controk.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class EmployeeCreationActivity extends BasePersonCreationActivity {
    public Employee mEmployee;
    public Socket mSocket = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_creation);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.employee_creation_title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        updateFragment(new EmployeeCreationFragment(), null);
    }

    @Override
    public void finish() {
        if (mSocket != null) {
            mSocket.disconnect();
        }
        super.finish();
    }

    public void performCreation(final BaseFragmentCreation fragment) {
        final JSONObject employee = new JSONObject();
        JSONObject address = new JSONObject();

        final View view = fragment.getView();
        assert view != null;

        Snackbar.make(view, "Employee creation started.", Snackbar.LENGTH_INDEFINITE);

        final EmployeeCreationActivity activity = this;

        try {
            address.put("place", mAddress.getPlaceOption());
            address.put("place_name", mAddress.getPlaceName());
            address.put("number", mAddress.getNumber());
            address.put("complement", mAddress.getComplement());
            address.put("neighborhood", mAddress.getNeighborhood());
            address.put("city", mAddress.getCity());
            address.put("state", mAddress.getState());
            address.put("cep", mAddress.getCep());

            employee.put("name", mEmployee.getName());
            employee.put("email", mEmployee.getEmail());
            employee.put("cpf", mEmployee.getCpf());
            employee.put("role", mEmployee.getRole());
            employee.put("mobile", mContact.getCellPhone());
            employee.put("phone", mContact.getPhone());
            employee.put("observation", mEmployee.getObservation());
            employee.put("address", address);


            mSocket = mSocket != null ? mSocket : IO.socket(getResources().getString(R.string.web_socket_host));
            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    mSocket.emit("create employee", employee);
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) { /* Just disconnects */}
            }).on("create ok", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Snackbar.make(view, args[0].toString(), Snackbar.LENGTH_LONG).show();
                    mSocket.disconnect();
                    Intent intent = new Intent(activity, MainActivity.class);
                    Bundle options = ActivityOptionsCompat.makeCustomAnimation(activity, R.anim.enter, R.anim.exit).toBundle();
                    fragment.startActivity(intent, options);
                    mSocket = null;
                }
            }).on("create failed", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Snackbar.make(view, args[0].toString(), Snackbar.LENGTH_LONG).show();
                    mSocket.disconnect();
                    mSocket = null;
                }
            });

            mSocket.connect();
        } catch (URISyntaxException | JSONException e) {
            e.printStackTrace();
        }
    }
}
