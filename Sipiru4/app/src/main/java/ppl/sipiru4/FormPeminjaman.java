package ppl.sipiru4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.User;

public class FormPeminjaman extends Activity {
    Context context;
    SharedPreferences setting;
    User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_peminjaman_ui);

        setting = getSharedPreferences(LoginActivity.PREFS_NAME,0);

        // mendapatkan informasi user
        user = new User(setting.getString(LoginActivity.KEY_USERNAME,null), setting.getString(LoginActivity.KEY_NAMA,null),
                setting.getString(LoginActivity.KEY_KODE_ORG,null), setting.getString(LoginActivity.KEY_ROLE,null),
                setting.getString(LoginActivity.KEY_KODE_IDENTITAS,null));

        context = this;

        Bundle b = getIntent().getExtras();
        if (b!=null) {
            String kodeRuangan = b.getString("kodeRuangan");
            String waktuAwal = b.getString("waktuAwal");
            String waktuAkhir = b.getString("waktuAkhir");

            final String username = setting.getString(LoginActivity.KEY_USERNAME,null);

            final EditText ruang = (EditText)findViewById(R.id.ruang);
            ruang.setText(kodeRuangan);

            final EditText nama = (EditText)findViewById(R.id.nama);
            nama.setText(setting.getString(LoginActivity.KEY_NAMA,null));

            final Spinner perihal = (Spinner) findViewById(R.id.perihal);
            final ArrayList<String> perihalString = new ArrayList<>();
            perihalString.add("Akademis");
            perihalString.add("Kepanitiaan");
            perihalString.add("Organisasi");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, perihalString) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View v = super.getView(position, convertView, parent);
                    ((TextView)v).setTextSize(24);
                    ((TextView)v).setTextColor(getResources().getColorStateList(R.color.abc_primary_text_disable_only_material_light));

                    return v;
                }

                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    View v = super.getView(position, convertView, parent);
                    ((TextView)v).setTextSize(22);

                    return v;
                }
            };
            perihal.setAdapter(adapter);

            final EditText kegiatan = (EditText)findViewById(R.id.kegiatan);

            final EditText waktuMulai = (EditText)findViewById(R.id.waktuMulai);
            waktuMulai.setText(waktuAwal);

            final EditText waktuSelesai = (EditText)findViewById(R.id.waktuSelesai);
            waktuSelesai.setText(waktuAkhir);

            final EditText peralatan = (EditText)findViewById(R.id.peralatan);

            Button btnSubmit = (Button)findViewById(R.id.submit);
            btnSubmit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String perihalValue = perihalString.get(perihal.getSelectedItemPosition())+"";
//                    Log.e("perihal", perihalValue);
                    String kegiatanValue = kegiatan.getText()+"";

                    if (kegiatanValue.trim().length()==0) {
                        Toast.makeText(getApplicationContext(), "Isian kegiatan tidak valid", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        int statusP;
                        if ((peralatan.getText()+"").trim().length()==0){
                            statusP = 0;
                            peralatan.setText("Tidak ada");
                        }
                        else  {
                            statusP = 1;
                        }

//                        Log.e("username",username);
                        String namaP = nama.getText().toString().replaceAll(" ","%20");
//                        Log.e("nama", namaP);
                        String waktuAwal = waktuMulai.getText().toString().replaceAll(" ","%20");
//                        Log.e("waktuAwal", waktuAwal);
                        String waktuAkhir = waktuSelesai.getText().toString().replaceAll(" ","%20");
//                        Log.e("waktuAkhir", waktuAkhir);
                        String alat = peralatan.getText().toString().replaceAll(" ","%20");

                        new SubmitHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/mengajukanPeminjaman/"
                                + username + "&" + namaP + "&" + statusP + "&" + ruang.getText() + "&"
                                + waktuAwal + "&" + waktuAkhir + "&" + perihalValue + "&" + kegiatanValue + "&" + alat + "&0/");


                    }
                }
            });
        }
        else {
            Toast.makeText(context, "Error memunculkan Form Peminjaman", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(),MainActivityP.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.putExtra("user", user);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    // kelas AsyncTask untuk mengakses URL
    private class SubmitHelper extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Submit permohonan...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                return JSONParser.getNotifFromURL(args[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String data) {
            if (data==null) {
                pDialog.dismiss();
                Toast.makeText(context,"gagal terhubung ke server. coba lagi",Toast.LENGTH_SHORT).show();
                return;
            }

            if (data.trim().equals("\"sukses\"")){
                Toast.makeText(getApplicationContext(), "Permohonan berhasil disubmit", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),MainActivityP.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("user",user);
                startActivity(i);
            }
            else {
                Toast.makeText(getApplicationContext(), "Error. Peminjaman tidak dapat dilakukan",
                        Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }
}
