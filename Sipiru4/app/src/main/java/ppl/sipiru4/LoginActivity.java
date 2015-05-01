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
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.User;

public class LoginActivity extends Activity{
    static final String PREFS_NAME = "pref";
    static final String KEY_USERNAME = "uname";
    static final String KEY_NAMA = "name";
    static final String KEY_ROLE = "role";
    private EditText username;
    private EditText password;
    static SharedPreferences setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);

        setting = getSharedPreferences(PREFS_NAME,0);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setupVariables();
    }

    private void setupVariables() {
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.ruang);
        Button login = (Button) findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    authenticateLogin(v);
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void authenticateLogin(View view) throws IOException, JSONException {
        String uname = username.getText()+"";
        String pass = password.getText()+"";

        if (uname.equals("mr")&&pass.equals("mr")){
            Intent i = new Intent(getApplicationContext(), MainActivityMR.class);
            i.putExtra("id", "peminjam");

            startActivity(i);
        }

        JSONArray hasil = JSONParser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/" + uname + "&" + pass);
        JSONObject data = hasil.getJSONObject(0);
        String username = data.getString("username");
        String nama = data.getString("nama");
        String kodeOrg = data.getString("kode_org");
        String role = data.getString("nama_role");
        int status = data.getInt("state");
        String kodeIdentitas = data.getString("kodeidentitas");


        if (status == 1) {
            if (role.equals("mahasiswa")) {
                SharedPreferences.Editor edit = setting.edit();
                edit.putString(KEY_USERNAME, username);
                edit.putString(KEY_NAMA, nama);
                edit.putString(KEY_ROLE, role);
                edit.apply();

                Intent i = new Intent(getApplicationContext(), MainActivityP.class);
                //mengoper data user ke MainActivityP.class
                User user = new User(username,nama,kodeOrg,role,kodeIdentitas);
                i.putExtra("user", user);
                startActivity(i);
//                finish();
            }
            else {
                Log.e("warning","bukan mahasiswa atau manager");
            }
            //lakukan pengecekan terhadap manager
//            if (role == "manager") {
//
//            }
//            PenggunaController.loginPengguna(new User(this,username,nama,kodeOrg,role,kodeIdentitas));
//            Log.e("pengguna", User.getNama(this));
        }
        else {
            Toast.makeText(getApplicationContext(), "akun tidak terdaftar", Toast.LENGTH_SHORT).show();
//            numberOfRemainingLoginAttempts--;
//            if (numberOfRemainingLoginAttempts == 0) {
//                Toast.makeText(getApplicationContext(), "Login dibekukan. silakan tutup aplikasi dan coba lagi nanti.", Toast.LENGTH_SHORT).show();
//                login.setEnabled(false);
//            }
        }
    }
}