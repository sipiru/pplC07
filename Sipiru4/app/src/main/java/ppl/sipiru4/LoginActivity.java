package ppl.sipiru4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    static final String KEY_KODE_ORG = "kode_org";
    final static String KEY_KODE_IDENTITAS = "kode_identitas";
    private EditText uname;
    private EditText pass;
    static SharedPreferences setting;
    Context context;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("LoginActivity", "create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);
        context = this;

        setting = getSharedPreferences(PREFS_NAME,0);
        Log.e("user", setting.getString(KEY_USERNAME,null) + " " + setting.getString(KEY_ROLE,null));

        setupVariables();
    }

    private void setupVariables() {
        uname = (EditText) findViewById(R.id.editText1);
        pass = (EditText) findViewById(R.id.ruang);
        Button login = (Button) findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo!=null && networkInfo.isConnected()) {
                    if (uname.getText().toString().trim().length()==0 || pass.getText().toString().trim().length()==0) {
                        Toast.makeText(context, "Mohon isi username dan password dengan benar", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        try {
                            authenticateLogin(uname.getText().toString(), pass.getText().toString());
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    Toast.makeText(context, "Mohon periksa koneksi internet Anda", Toast.LENGTH_LONG).show();
                }
            }
        });

        Log.e("user2", setting.getString(KEY_USERNAME,null) + " " + setting.getString(KEY_ROLE,null));
    }

    public void authenticateLogin(String username, String password) throws IOException, JSONException {
        if (username.equals("mr")&&password.equals("mr")){
            // username dan password percobaan untuk manajer ruangan, tanpa menggunakan SSO
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
        else if (username.equals("mk")&&password.equals("mk")){
            // username dan password percobaan untuk manajer kemahasiswaan, tanpa menggunakan SSO
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
        else if (username.equals("itf")&&password.equals("itf")){
            // username dan password buat percobaan untuk manajer ITF, tanpa menggunakan SSO
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
        else if (username.equals("admin")&&password.equals("admin")){
            // username dan password buat percobaan untuk admin, tanpa menggunakan SSO
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
            // jika menggunakan akun SSO, maka lakukan koneksi ke database
            new LoginSync().execute("http://ppl-c07.cs.ui.ac.id/connect/" + uname.getText().toString() + "&" + pass.getText().toString());
        }
    }

    // kelas berekstends AsyncTask untuk mengakses URL
    class LoginSync extends AsyncTask<String, String, JSONObject> {
        JSONArray hasil;
        JSONObject object;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Logging in...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... f_url) {
            try {
                hasil = JSONParser.getJSONfromURL(f_url[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            if (hasil == null) {
                pDialog.dismiss();
//                Toast.makeText(context,"gagal terhubung ke server. coba lagi",Toast.LENGTH_SHORT).show();
                return null;
            }
            try {
                object = hasil.getJSONObject(0);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            return object;
        }

        @Override
        protected void onPostExecute(JSONObject data) {
            if (data == null) {
                pDialog.dismiss();
                Toast.makeText(context,"gagal terhubung ke server. coba lagi",Toast.LENGTH_SHORT).show();
                return;
            }
            Log.e("user3", setting.getString(KEY_USERNAME,null) + " " + setting.getString(KEY_ROLE,null));
            String username;
            String nama;
            String kodeOrg;
            String role;
            int status;
            String kodeIdentitas;
            try {
                username = data.getString("username");
                nama = data.getString("nama");
                kodeOrg = data.getString("kode_org");
                role = data.getString("nama_role");
                status = data.getInt("state");
                kodeIdentitas = data.getString("kodeidentitas");
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, e.toString(),Toast.LENGTH_SHORT).show();
                return;
            }

            if (status == 1) {
//                kodeOrg.equals("xx.xx.01.00.12.01:mahasiswa")
                Log.e("kode universitas", kodeOrg.substring(12,14));
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
                else if (role.equals("manajer ruangan")){
                    // menyimpan data user ke SharedPreferences
                    SharedPreferences.Editor edit = setting.edit();
                    edit.putString(KEY_USERNAME, username);
                    edit.putString(KEY_NAMA, nama);
                    edit.putString(KEY_KODE_ORG,kodeOrg);
                    edit.putString(KEY_ROLE, role);
                    edit.putString(KEY_KODE_IDENTITAS,kodeIdentitas);
                    edit.apply();

                    Intent i = new Intent(getApplicationContext(), MainActivityMR.class);
                    //mengoper data user ke MainActivityMR.class
                    User user = new User(username,nama,kodeOrg,role,kodeIdentitas);
                    i.putExtra("user", user);
                    startActivity(i);
                    finish();
                }
                else if (role.equals("manajer kemahasiswaan")){
                    // menyimpan data user ke SharedPreferences
                    SharedPreferences.Editor edit = setting.edit();
                    edit.putString(KEY_USERNAME, username);
                    edit.putString(KEY_NAMA, nama);
                    edit.putString(KEY_KODE_ORG,kodeOrg);
                    edit.putString(KEY_ROLE, role);
                    edit.putString(KEY_KODE_IDENTITAS,kodeIdentitas);
                    edit.apply();

                    Intent i = new Intent(getApplicationContext(), MainActivityK.class);
                    //mengoper data user ke MainActivityMK.class
                    User user = new User(username,nama,kodeOrg,role,kodeIdentitas);
                    i.putExtra("user", user);
                    startActivity(i);
                    finish();
                }
                else if (role.equals("manajer ITF")){
                    // menyimpan data user ke SharedPreferences
                    SharedPreferences.Editor edit = setting.edit();
                    edit.putString(KEY_USERNAME, username);
                    edit.putString(KEY_NAMA, nama);
                    edit.putString(KEY_KODE_ORG,kodeOrg);
                    edit.putString(KEY_ROLE, role);
                    edit.putString(KEY_KODE_IDENTITAS,kodeIdentitas);
                    edit.apply();

                    Intent i = new Intent(getApplicationContext(), MainActivityFI.class);
                    //mengoper data user ke MainActivityFI.class
                    User user = new User(username,nama,kodeOrg,role,kodeIdentitas);
                    i.putExtra("user", user);
                    startActivity(i);
                    finish();
                }
                else if (role.equals("admin")){
                    // menyimpan data user ke SharedPreferences
                    SharedPreferences.Editor edit = setting.edit();
                    edit.putString(KEY_USERNAME, username);
                    edit.putString(KEY_NAMA, nama);
                    edit.putString(KEY_KODE_ORG,kodeOrg);
                    edit.putString(KEY_ROLE, role);
                    edit.putString(KEY_KODE_IDENTITAS,kodeIdentitas);
                    edit.apply();

                    Intent i = new Intent(getApplicationContext(), MainActivityA.class);
                    //mengoper data user ke MainActivityA.class
                    User user = new User(username,nama,kodeOrg,role,kodeIdentitas);
                    i.putExtra("user", user);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "maaf, akun tidak bisa digunakan untuk masuk ke aplikasi", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getApplicationContext(), "akun tidak terdaftar", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("LoginActivity", "resume");
    }
}