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
import java.io.IOException;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.Entity.User;

public class DetailPengembalianAlat extends Activity {
    final Context context = this;
    Peminjaman peminjaman;
    Bundle b;
    SharedPreferences setting;
    User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pengembalian_alat_fi);

        setting = getSharedPreferences(LoginActivity.PREFS_NAME,0);
        user = new User(setting.getString(LoginActivity.KEY_USERNAME,null), setting.getString(LoginActivity.KEY_NAMA,null),
                setting.getString(LoginActivity.KEY_KODE_ORG,null), setting.getString(LoginActivity.KEY_ROLE,null),
                setting.getString(LoginActivity.KEY_KODE_IDENTITAS,null));

        // mendapatkan nilai-nilai yang dioper dari DaftarPermohonanFI.class
        b = getIntent().getExtras();
        if (b!=null){
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

            final TextView peralatan = (TextView)findViewById(R.id.peralatan);
            peralatan.setText(peminjaman.getPeralatan());


            Button btnSelesai = (Button)findViewById(R.id.btnSelesai);
            btnSelesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    // set title
                    alertDialogBuilder.setTitle("Apakah semua peralatan sudah dicek kembali?");
                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Tekan Ya untuk konfirmasi")
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/ubahStatusPeminjam/" + peminjaman.getId());
                                }
                            })
                            .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
        }
        else {
            Toast.makeText(context, "Error mendapatkan data peminjaman", Toast.LENGTH_LONG).show();
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
        protected void onPostExecute(String notif) {
            if (notif == null) {
                pDialog.dismiss();
                Toast.makeText(context, "gagal menghubungkan server. coba lagi.",Toast.LENGTH_SHORT).show();
                return;
            }
            if (notif.trim().equals("\"sukses\"")){
                Toast.makeText(getApplicationContext(), "Status berhasil diubah", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            Intent i = new Intent(getApplicationContext(),MainActivityFI.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.putExtra("user",user);
            i.putExtra("navPosition",1);
            startActivity(i);
            pDialog.dismiss();
        }
    }
}


