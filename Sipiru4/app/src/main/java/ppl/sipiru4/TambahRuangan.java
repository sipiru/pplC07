package ppl.sipiru4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import ppl.sipiru4.Entity.JSONParser;

public class TambahRuangan extends Activity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_ruangan);
        context = this;

        final EditText kodeRuangan = (EditText) findViewById(R.id.kodeRuangan);
        final EditText namaRuangan = (EditText) findViewById(R.id.namaRuangan);
        final EditText kapasitas = (EditText) findViewById(R.id.kapasitas);
        final EditText deskripsi = (EditText) findViewById(R.id.deskripsi);

        Button add = (Button) findViewById(R.id.buttonAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kodeRuangan.getText().toString().trim().length()==0 || kapasitas.getText().toString().trim().length()==0) {
                    Toast.makeText(context, "Kode ruangan dan kapasitas harus diisi", Toast.LENGTH_SHORT).show();
                }
                else if (kodeRuangan.getText().toString().length() > 5) {
                    Toast.makeText(context, "Kode ruangan maksimal 5 karakter", Toast.LENGTH_SHORT).show();
                }
                else {
                    String kode = kodeRuangan.getText().toString().trim();
                    String nama = namaRuangan.getText().toString().replaceAll(" ","%20");
                    String kap = kapasitas.getText().toString().trim();
                    String desk = deskripsi.getText().toString().replaceAll(" ","%20");
                    if (kap.length() > 4) {
                        Toast.makeText(context, "kapasitas terlalu besar.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        new SubmitHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/menambahRuangan/"
                                + kode + "&" + nama + "&" + kap + "&" + desk);
                    }
                }
            }
        });
    }

    // kelas AsyncTask untuk mengakses URL
    private class SubmitHelper extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Menambah ruangan ke database...");
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
                Toast.makeText(context, "Ruangan berhasil ditambah", Toast.LENGTH_SHORT).show();
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
