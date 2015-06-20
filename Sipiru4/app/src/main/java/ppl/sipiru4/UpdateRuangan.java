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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Ruangan;
import ppl.sipiru4.controller.RuanganController;

public class UpdateRuangan extends Activity {
    ArrayAdapter<String> adapter;
    String[] ruangan;
    RuanganController ruanganController;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar()!=null) {
            getActionBar().setTitle("Update Ruangan");
        }
        setContentView(R.layout.update_ruangan);
        context = this;

        final TextView kodeRuangan = (TextView) findViewById(R.id.kodeRuangan);
        final EditText namaRuangan = (EditText) findViewById(R.id.namaRuangan);
        final EditText kapasitas = (EditText) findViewById(R.id.kapasitas);
        final EditText deskripsi = (EditText) findViewById(R.id.deskripsi);

		// mendapatkan detail suatu ruangan yang dioper dari kelas sebelumnya
        Bundle b = getIntent().getExtras();
        if (b!=null) {
            Ruangan ruangan = b.getParcelable("ruangan");
            ruanganController = new RuanganController(ruangan);

            kodeRuangan.setText(ruanganController.getRuangan().getKode());
            namaRuangan.setText(ruanganController.getRuangan().getNama());
            kapasitas.setText(ruanganController.getRuangan().getKapasitas()+"");
            deskripsi.setText(ruanganController.getRuangan().getDeskripsi());

            Button save = (Button) findViewById(R.id.buttonSave);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String kode = kodeRuangan.getText().toString();
                    String nama = namaRuangan.getText().toString().replaceAll(" ","%20");
                    String kap = kapasitas.getText().toString().trim();
                    String desk = deskripsi.getText().toString().replaceAll(" ","%20");

                    if (kap.length() > 4) {
                        Toast.makeText(context, "kapasitas terlalu besar.", Toast.LENGTH_SHORT).show();
                    }
                    else {
						// mengecek koneksi internet
                        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                        if (networkInfo!=null && networkInfo.isConnected()) {
							// mengubah database menggunakan akses ke webserver
                            new SubmitHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/updateRuangan/"
                                    + kode + "&" + nama + "&" + kap + "&" + desk);
                        }
                        else {
                            Toast.makeText(context, "Mohon periksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
        else {
            Toast.makeText(context, "Error membuka halaman Update Ruangan",Toast.LENGTH_LONG).show();
        }
    }

    // kelas AsyncTask untuk mengakses URL
    private class SubmitHelper extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Meng-update ruangan...");
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
                Toast.makeText(context,"gagal terhubung ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (data.trim().equals("\"sukses\"")){
                Toast.makeText(context, "Ruangan berhasil diupdate", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context,MainActivityA.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("navPosition",0);
                startActivity(i);
            }
            else {
                Toast.makeText(context, "Error.", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }
}
