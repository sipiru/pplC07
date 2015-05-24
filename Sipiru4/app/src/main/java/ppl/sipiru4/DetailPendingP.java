package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.controller.PeminjamanController;
import ppl.sipiru4.controller.PenggunaController;

public class DetailPendingP extends Activity {
    final Context context = this;
    PeminjamanController peminjamanController;
    Bundle b;
    PenggunaController penggunaController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pending_p);
        Log.e("Detail Permohonan P", "create");

        // mendapatkan nilai-nilai yang dioper dari DaftarPendingP.class
        b = getIntent().getExtras();

        if (b!=null) {
            Peminjaman peminjaman = b.getParcelable("peminjaman");

            peminjamanController = new PeminjamanController(peminjaman);
//        Log.e("peminjaman", peminjaman.getKodeRuangan() + " " + peminjaman.getNamaP() + " " + peminjaman.getId());

            TextView ruang = (TextView)findViewById(R.id.ruang);
            ruang.setText(peminjamanController.getPeminjaman().getKodeRuangan());

            TextView nama = (TextView)findViewById(R.id.nama);
            nama.setText(peminjamanController.getPeminjaman().getNamaP());

            TextView username = (TextView)findViewById(R.id.username);
            username.setText(peminjamanController.getPeminjaman().getUsernameP());

            TextView prihal = (TextView)findViewById(R.id.prihal);
            prihal.setText(peminjamanController.getPeminjaman().getPerihal());

            TextView kegiatan = (TextView) findViewById(R.id.kegiatan);
            kegiatan.setText(peminjamanController.getPeminjaman().getKegiatan());

            TextView waktuMulai = (TextView)findViewById(R.id.waktuMulai);
            waktuMulai.setText(peminjamanController.getPeminjaman().getMulai());

            TextView waktuSelesai = (TextView)findViewById(R.id.waktuSelesai);
            waktuSelesai.setText(peminjamanController.getPeminjaman().getSelesai());

            TextView peralatan = (TextView)findViewById(R.id.permintaanlain);
            peralatan.setText(peminjamanController.getPeminjaman().getPeralatan());

            Button batal = (Button)findViewById(R.id.batal);
            batal.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set title
                    alertDialogBuilder.setTitle("Apakah anda yakin untuk membatalkan permohonan?");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Tekan Ya untuk membatalkan permohonan")
                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/membatalkanPermohonan/"
                                            + peminjamanController.getPeminjaman().getId());
                                }
                            })
                            .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // show it
                    alertDialog.show();
                }
            });
        }
        else {
            Toast.makeText(context,"Error mendapatkan data peminjaman",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    // kelas AsyncTask untuk mengakses URL
    private class TaskHelper extends android.os.AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage("Sedang diproses...");
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
                Toast.makeText(context,"gagal menghubungkan ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (data.trim().equals("\"sukses\"")) {
                Toast.makeText(getApplicationContext(), "Permohonan berhasil dibatalkan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Error. Permohonan sudah tidak ada", Toast.LENGTH_SHORT).show();
            }
            Intent i = new Intent(getApplicationContext(),MainActivityP.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.putExtra("navPosition",2);
            startActivity(i);
            pDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Detail Permohonan P", "resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Detail Permohonan P","stop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Detail Permohonan P", "start");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Detail Permohonan P", "pause");
    }
}