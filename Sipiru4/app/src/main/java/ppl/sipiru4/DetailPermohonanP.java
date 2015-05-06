package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.Entity.User;

public class DetailPermohonanP extends Activity {
    final Context context = this;
    Peminjaman peminjaman;
    SharedPreferences setting;
    Bundle b;
    User user;

//    public DetailPermohonanP(Peminjaman peminjaman) {
//        this.peminjaman = peminjaman;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_permohonan_p);

        setting = getSharedPreferences(LoginActivity.PREFS_NAME,0);
        user = new User(setting.getString(LoginActivity.KEY_USERNAME,null), setting.getString(LoginActivity.KEY_NAMA,null),
                setting.getString(LoginActivity.KEY_KODE_ORG,null), setting.getString(LoginActivity.KEY_ROLE,null),
                setting.getString(LoginActivity.KEY_KODE_IDENTITAS,null));

        // mendapatkan nilai-nilai yang dioper dari DaftarPermohonanP.class
        b = getIntent().getExtras();
        peminjaman = b.getParcelable("peminjaman");
        Log.e("peminjaman", peminjaman.getKodeRuangan() + " " + peminjaman.getNamaP() + " " + peminjaman.getId());

        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText(peminjaman.getKodeRuangan());

        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText(peminjaman.getNamaP());

        TextView username = (TextView)findViewById(R.id.username);
        username.setText(peminjaman.getUsernameP());

        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText(peminjaman.getPerihal());

        TextView kegiatan = (TextView) findViewById(R.id.kegiatan);
        kegiatan.setText(peminjaman.getKegiatan());

        TextView waktuMulai = (TextView)findViewById(R.id.waktuMulai);
        waktuMulai.setText(peminjaman.getMulai());

        TextView waktuSelesai = (TextView)findViewById(R.id.waktuSelesai);
        waktuSelesai.setText(peminjaman.getSelesai());

        TextView peralatan = (TextView)findViewById(R.id.permintaanlain);
        peralatan.setText(peminjaman.getPeralatan());

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
                                String notif = null;
                                try {
                                    notif = new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/membatalkanPermohonan/" + peminjaman.getId()).get();
                                } catch (InterruptedException | ExecutionException e) {
                                    e.printStackTrace();
                                }

                                assert notif != null;
                                if (notif.trim().equals("\"sukses\"")) {
                                    Toast.makeText(getApplicationContext(), "Permohonan berhasil dibatalkan", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Error. Permohonan sudah tidak ada", Toast.LENGTH_SHORT).show();
                                }
                                Intent i = new Intent(getApplicationContext(),MainActivityP.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                i.putExtra("user",user);
                                i.putExtra("navPosition",2);
                                startActivity(i);
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
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            return JSONParser.getNotifFromURL(args[0]);
        }

        @Override
        protected void onPostExecute(String data) {
            pDialog.dismiss();
        }
    }
}


