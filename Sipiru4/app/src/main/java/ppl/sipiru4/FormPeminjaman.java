package ppl.sipiru4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.SessionManager;
import ppl.sipiru4.controller.PenggunaController;

public class FormPeminjaman extends Activity {
    Context context;
    PenggunaController penggunaController;
    SessionManager session;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if (getActionBar()!=null) {
            getActionBar().setTitle("Form Peminjaman");
        }
        setContentView(R.layout.form_peminjaman_ui);
        session = new SessionManager(getApplicationContext());
        // mendapatkan informasi user
        penggunaController = new PenggunaController(session.getUser());

		// mendapatkan informasi yang dioper dari kelas sebelumnya. gunanya untuk me-auto generate form yang akan diisi
        Bundle b = getIntent().getExtras();
        if (b!=null) {
            String kodeRuangan = b.getString("kodeRuangan");
            String waktuAwal = b.getString("waktuAwal");
            String waktuAkhir = b.getString("waktuAkhir");

            final String username = penggunaController.getCurrentPengguna().getUsername();

            final EditText ruang = (EditText)findViewById(R.id.ruang);
            ruang.setText(kodeRuangan);

            final EditText nama = (EditText)findViewById(R.id.nama);
            nama.setText(penggunaController.getCurrentPengguna().getNama());

            final Spinner perihal = (Spinner) findViewById(R.id.perihal);
            final String[] perihalArray;
            perihalArray = getResources().getStringArray(R.array.perihal);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, perihalArray) {
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
			// me-submit permohonan
            btnSubmit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String perihalValue = perihalArray[perihal.getSelectedItemPosition()]+"";
//                    Log.e("perihal", perihalValue);
                    String kegiatanValue = kegiatan.getText()+"";

                    if (kegiatanValue.trim().length()==0) {
                        Toast.makeText(getApplicationContext(), "Isian kegiatan tidak valid", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        int statusP;
                        if ((peralatan.getText()+"").trim().length()==0){
                            statusP = 0;
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
                        String kegiatanModif = kegiatanValue.replaceAll(" ", "%20");

						// mengecek koneksi internet
                        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                        if (networkInfo!=null && networkInfo.isConnected()) {
							// memasukkan permohonan ke database menggunakan akses webserver
                            new SubmitHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/mengajukanPeminjaman/"
                                    + username + "&" + namaP + "&" + statusP + "&" + ruang.getText() + "&"
                                    + waktuAwal + "&" + waktuAkhir + "&" + perihalValue + "&" + kegiatanModif + "&" + alat);
                        }
                        else {
                            Toast.makeText(context, "Mohon periksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        else {
            Toast.makeText(context, "Error memunculkan Form Peminjaman", Toast.LENGTH_LONG).show();
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
