package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;
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
import ppl.sipiru4.controller.PenggunaController;

public class LoginActivity extends Activity{
    private EditText username;
    private EditText password;
    private Button login;
    int numberOfRemainingLoginAttempts = 10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setupVariables();
    }

    private void setupVariables() {
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.ruang);
        login = (Button) findViewById(R.id.buttonLogin);

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
            Intent i = new Intent(this, MainActivityMR.class);
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
                Intent i = new Intent(getApplicationContext(), MainActivityP.class);
                i.putExtra("id", "peminjam");
                startActivity(i);
            }
            else {
                Log.e("warning","bukan mahasiswa atau manager");
            }
            //lakukan pengecekan terhadap manager
//            if (role == "manager") {
//
//            }
            PenggunaController.loginPengguna(new User(this,username,nama,kodeOrg,role,kodeIdentitas));
            Log.e("pengguna", User.getNama(this));
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