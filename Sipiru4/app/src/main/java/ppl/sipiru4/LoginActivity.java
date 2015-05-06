package ppl.sipiru4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.User;

public class LoginActivity extends Activity{
    static final String PREFS_NAME = "pref";
    static final String KEY_USERNAME = "uname";
    static final String KEY_NAMA = "name";
    static final String KEY_ROLE = "role";
    static final String KEY_KODE_ORG = "kode_org";
    final static String KEY_KODE_IDENTITAS = "kode_identitas";
    private EditText uname;
    private EditText pass;
    static SharedPreferences setting;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);
        context = this;

        setting = getSharedPreferences(PREFS_NAME,0);

        setupVariables();
    }

    private void setupVariables() {
        uname = (EditText) findViewById(R.id.editText1);
        pass = (EditText) findViewById(R.id.ruang);
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
        JSONObject data = null;
        try {
            data = new LoginSync().execute("http://ppl-c07.cs.ui.ac.id/connect/" + uname.getText().toString() + "&" + pass.getText().toString()).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        assert data != null;
        String username = data.getString("username");
        String nama = data.getString("nama");
        String kodeOrg = data.getString("kode_org");
        String role = data.getString("nama_role");
        int status = data.getInt("state");
        String kodeIdentitas = data.getString("kodeidentitas");

        if (status == 1) {
            if (role.equals("mahasiswa")) {
                // menyimpan data user ke SharedPreferences
                SharedPreferences.Editor edit = setting.edit();
                edit.putString(KEY_USERNAME, username);
                edit.putString(KEY_NAMA, nama);
                edit.putString(KEY_KODE_ORG,kodeOrg);
                edit.putString(KEY_ROLE, role);
                edit.putString(KEY_KODE_IDENTITAS,kodeIdentitas);
                edit.apply();

                Intent i = new Intent(getApplicationContext(), MainActivityP.class);
                //mengoper data user ke MainActivityP.class
                User user = new User(username,nama,kodeOrg,role,kodeIdentitas);
                i.putExtra("user", user);
                startActivity(i);
                finish();
            }
            else {
                Log.e("warning","bukan mahasiswa Fasilkom");
            }
        }
        else if (uname.getText().toString().equals("mr")&&pass.getText().toString().equals("mr")){
            SharedPreferences.Editor edit = setting.edit();
            edit.putString(KEY_USERNAME, "mr");
            edit.putString(KEY_NAMA, "mr");
            edit.putString(KEY_ROLE, "manager ruangan");
            edit.apply();

            Intent i = new Intent(getApplicationContext(), MainActivityMR.class);
            User user = new User("mr","mr",null,"manager ruangan",null);
            //mengoper data user ke MainActivityMR.class
            i.putExtra("user", user);
            startActivity(i);
            finish();
        }
        else if (uname.getText().toString().equals("mk")&&pass.getText().toString().equals("mk")){
            SharedPreferences.Editor edit = setting.edit();
            edit.putString(KEY_USERNAME, "mk");
            edit.putString(KEY_NAMA, "mk");
            edit.putString(KEY_ROLE, "manager kemahasiswaan");
            edit.apply();

            Intent i = new Intent(getApplicationContext(), MainActivityK.class);
            //mengoper data user ke MainActivityK.class
            User user = new User("mk","mk",null,"manager kemahasiswaan",null);
            i.putExtra("user", user);
            startActivity(i);
            finish();
        }
        else if (uname.getText().toString().equals("itf")&&pass.getText().toString().equals("itf")){
            SharedPreferences.Editor edit = setting.edit();
            edit.putString(KEY_USERNAME, "itf");
            edit.putString(KEY_NAMA, "itf");
            edit.putString(KEY_ROLE, "FASUM/ITF");
            edit.apply();

            Intent i = new Intent(getApplicationContext(), MainActivityFI.class);
            //mengoper data user ke MainActivityFI.class
            User user = new User("itf","itf",null,"FASUM/ITF",null);
            i.putExtra("user", user);
            startActivity(i);
            finish();
        }
        else if (uname.getText().toString().equals("admin")&&pass.getText().toString().equals("admin")){
            SharedPreferences.Editor edit = setting.edit();
            edit.putString(KEY_USERNAME, "admin");
            edit.putString(KEY_NAMA, "admin");
            edit.putString(KEY_ROLE, "admin");
            edit.apply();

            Intent i = new Intent(getApplicationContext(), MainActivityA.class);
            //mengoper data user ke MainActivityA.class
            User user = new User("admin","admin",null,"admin",null);
            i.putExtra("user", user);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(), "akun tidak terdaftar", Toast.LENGTH_SHORT).show();
        }
    }

    // kelas AsyncTask untuk mengakses URL
    private class LoginSync extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Logging in...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONArray hasil = JSONParser.getJSONfromURL(args[0]);
            JSONObject data = null;

            try {
                data = hasil.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(JSONObject data) {
            pDialog.dismiss();
        }
    }
}