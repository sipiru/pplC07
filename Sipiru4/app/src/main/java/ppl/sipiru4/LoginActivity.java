package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ppl.sipiru4.Business.JSONParser;

public class LoginActivity extends Activity{
    private EditText username;
    private EditText password;
    private Button login;
    private static final String user = "user"; //konstanta untuk menyimpan username menggunakan SharedPreferences
    SharedPreferences settings;
    int numberOfRemainingLoginAttempts = 10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setupVariables();
        Toast.makeText(this,"silahkan login", Toast.LENGTH_SHORT).show();
        settings = getSharedPreferences("user", MODE_PRIVATE);
    }

    private void setupVariables() {
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.ruang);
        login = (Button) findViewById(R.id.buttonLogin);
        if (settings != null) {
            SharedPreferences.Editor edit = settings.edit();
            edit.clear();
            edit.commit();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    authenticateLogin(v);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void authenticateLogin(View view) throws IOException, JSONException {
        //TODO : periksa apakah username dan password terdaftar sebagai salah satu role
        String uname = username.getText()+"";
        String pass = password.getText()+"";
        Log.e("nama", uname);
        Log.e("password", pass);

        JSONParser parser = new JSONParser();
        JSONArray hasil = parser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/"+uname+"&"+pass);
        JSONObject data = hasil.getJSONObject(0);
        // TODO: jalankan fungsi login() untuk semua controller untuk mengambil semua data user
        int status = data.getInt("state");
        Log.e("satus", ""+status);
        String role = data.getString("nama_role");
        Log.e("role", role);
        if (status == 1) {
            if (role.equals("mahasiswa")) {
                Intent i = new Intent(getApplicationContext(), MainActivityP.class);
                // passing array index
                i.putExtra("id", "peminjam");
                startActivity(i);
            }
            else {
                Log.e("asdsda","asdsda");
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "akun tidak terdaftar", Toast.LENGTH_SHORT).show();
            numberOfRemainingLoginAttempts--;
            if (numberOfRemainingLoginAttempts == 0) {
                Toast.makeText(getApplicationContext(), "Login dibekukan. silakan tutup aplikasi dan coba lagi nanti.", Toast.LENGTH_SHORT).show();
                login.setEnabled(false);
            }
        }
    }
}