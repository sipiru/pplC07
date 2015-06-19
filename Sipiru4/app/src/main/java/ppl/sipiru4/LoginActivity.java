package ppl.sipiru4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.SessionManager;
import ppl.sipiru4.Entity.User;
import ppl.sipiru4.controller.PenggunaController;

public class LoginActivity extends Activity{
    static final String PREFS_NAME = "pref";
    static final String KEY_USERNAME = "uname";
    static final String KEY_NAMA = "name";
    static final String KEY_ROLE = "role";
    private EditText uname;
    private EditText pass;
    Context context;
    private ProgressDialog pDialog;
    SessionManager session;
    PenggunaController penggunaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new SessionManager(getApplicationContext());
        context = this;
        if(session.isUserLoggedIn()) {
            if (session.getUser().getRole().equals("mahasiswa")) {
                Intent i = new Intent(context,MainActivityP.class);
                startActivity(i);
                finish();
            }
            else if (session.getUser().getRole().equals("manajer ruangan")){
                Intent i = new Intent(context,MainActivityMR.class);
                startActivity(i);
                finish();
            }
            else if (session.getUser().getRole().equals("manajer kemahasiswaan")){
                Intent i = new Intent(context,MainActivityK.class);
                startActivity(i);
                finish();
            }
            else if (session.getUser().getRole().equals("manajer umum") || session.getUser().getRole().equals("manajer itf")){
                Intent i = new Intent(context,MainActivityFI.class);
                startActivity(i);
                finish();
            }
            else if (session.getUser().getRole().equals("admin")){
                Intent i = new Intent(context,MainActivityA.class);
                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(context, "Ada kesalahan pencatatan role", Toast.LENGTH_SHORT).show();
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);

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
                    Toast.makeText(context, "Mohon periksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void authenticateLogin(String username, String password) throws IOException, JSONException {
        if (username.equals("mr")&&password.equals("mr")){
            // username dan password percobaan untuk manajer ruangan, tanpa menggunakan SSO
            penggunaController = new PenggunaController(new User("mr","mr","manajer ruangan"));
            session.createLoginSession(penggunaController.getCurrentPengguna());

            Intent i = new Intent(getApplicationContext(), MainActivityMR.class);
            startActivity(i);
            finish();
        }
        else if (username.equals("mk")&&password.equals("mk")){
            // username dan password percobaan untuk manajer kemahasiswaan, tanpa menggunakan SSO
            penggunaController = new PenggunaController(new User("mk","mk","manajer kemahasiswaan"));
            session.createLoginSession(penggunaController.getCurrentPengguna());

            Intent i = new Intent(getApplicationContext(), MainActivityK.class);
            startActivity(i);
            finish();
        }
        else if (username.equals("itf")&&password.equals("itf")){
            // username dan password buat percobaan untuk manajer ITF, tanpa menggunakan SSO
            penggunaController = new PenggunaController(new User("itf","itf","manajer itf"));
            session.createLoginSession(penggunaController.getCurrentPengguna());

            Intent i = new Intent(getApplicationContext(), MainActivityFI.class);
            startActivity(i);
            finish();
        }
        else if (username.equals("admin")&&password.equals("admin")){
            // username dan password buat percobaan untuk admin, tanpa menggunakan SSO
            penggunaController = new PenggunaController(new User("admin","admin","admin"));
            session.createLoginSession(penggunaController.getCurrentPengguna());

            Intent i = new Intent(getApplicationContext(), MainActivityA.class);
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
            String username;
            String nama;
            String kodeOrg;
            String role;
            int status;
            try {
                username = data.getString("username");
                nama = data.getString("nama");
                kodeOrg = data.getString("kode_org");
                role = data.getString("nama_role");
                status = data.getInt("state");
//                kodeIdentitas = data.getString("kodeidentitas");
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, e.toString(),Toast.LENGTH_SHORT).show();
                return;
            }

            if (status == 1) {
                Toast.makeText(context, "kode org : " + kodeOrg.substring(12,14), Toast.LENGTH_SHORT).show();
                if (role.equals("mahasiswa")) {
                    penggunaController = new PenggunaController(new User(username,nama,role));
                    session.createLoginSession(penggunaController.getCurrentPengguna());

                    Intent i = new Intent(getApplicationContext(), MainActivityP.class);
                    startActivity(i);
                    finish();
                }
                else if (role.equals("manajer ruangan")){
                    penggunaController = new PenggunaController(new User(username,nama,role));
                    session.createLoginSession(penggunaController.getCurrentPengguna());

                    Intent i = new Intent(getApplicationContext(), MainActivityMR.class);
                    startActivity(i);
                    finish();
                }
                else if (role.equals("manajer kemahasiswaan")){
                    penggunaController = new PenggunaController(new User(username,nama,role));
                    session.createLoginSession(penggunaController.getCurrentPengguna());

                    Intent i = new Intent(getApplicationContext(), MainActivityK.class);
                    startActivity(i);
                    finish();
                }
                else if (role.equals("manajer itf") || role.equals("manajer umum")){
                    penggunaController = new PenggunaController(new User(username,nama,role));
                    session.createLoginSession(penggunaController.getCurrentPengguna());

                    Intent i = new Intent(getApplicationContext(), MainActivityFI.class);
                    startActivity(i);
                    finish();
                }
                else if (role.equals("admin")){
                    penggunaController = new PenggunaController(new User(username,nama,role));
                    session.createLoginSession(penggunaController.getCurrentPengguna());

                    Intent i = new Intent(getApplicationContext(), MainActivityA.class);
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