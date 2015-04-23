package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ppl.sipiru4.Entity.JSONHelper;
import ppl.sipiru4.Entity.JSONParser;

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
            //lakukan pengecekan terhadap manager
//            if (role == "manager") {
//
//            }
        }
        else {
            Toast.makeText(getApplicationContext(), "akun tidak terdaftar", Toast.LENGTH_SHORT).show();
            numberOfRemainingLoginAttempts--;
            if (numberOfRemainingLoginAttempts == 0) {
                Toast.makeText(getApplicationContext(), "Login dibekukan. silakan tutup aplikasi dan coba lagi nanti.", Toast.LENGTH_SHORT).show();
                login.setEnabled(false);
            }
        }

//        Toast.makeText(getApplicationContext(), hasil + "wk", Toast.LENGTH_SHORT);
//        if ((username.getText().toString().equals("p") &&
//                password.getText().toString().equals("p"))
//        ){
//            SharedPreferences.Editor editor = settings.edit();
//            editor.putString(user,""+uname);
//            editor.commit();
//
//            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(getApplicationContext(), MainActivityP.class);
//            // passing array index
//            i.putExtra("id", "peminjam");
//            startActivity(i);
//        }
//        else if(username.getText().toString().equals("mr") &&
//                password.getText().toString().equals("mr")){
//            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(getApplicationContext(), MainActivityMR.class);
//            // passing array index
//            i.putExtra("id", "manager ruangan");
//            startActivity(i);
//        }
//        else if(username.getText().toString().equals("mk") &&
//                password.getText().toString().equals("mk")){
//            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(getApplicationContext(), MainActivityK.class);
//            // passing array index
//            i.putExtra("id", "manager kemahasiswaan");
//            startActivity(i);
//        }
//        else if(username.getText().toString().equals("itf") &&
//                password.getText().toString().equals("itf")){
//            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(getApplicationContext(), MainActivityFI.class);
//            // passing array index
//            i.putExtra("id", "fasum itf");
//            startActivity(i);
//        }
//        else if(username.getText().toString().equals("adm") &&
//                password.getText().toString().equals("adm")){
//            Toast.makeText(getApplicationContext(), "Hello, "+settings.getString(user,"") ,Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(getApplicationContext(), MainActivityP.class);
//            // passing array index
//            i.putExtra("id", "admin");
//            startActivity(i);
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "akun tidak terdaftar", Toast.LENGTH_SHORT).show();
//
//            numberOfRemainingLoginAttempts--;
//            if (numberOfRemainingLoginAttempts == 0) {
//                login.setEnabled(false);
//            }
//        }

    }
}