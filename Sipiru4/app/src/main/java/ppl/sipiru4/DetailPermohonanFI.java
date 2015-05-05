package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
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

import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.Entity.User;

public class DetailPermohonanFI extends Activity {
    final Context context = this;
    Peminjaman peminjaman;
    Bundle b;
    SharedPreferences setting;
    User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_permohonan_fi);

        setting = getSharedPreferences(LoginActivity.PREFS_NAME,0);
        user = new User(setting.getString(LoginActivity.KEY_USERNAME,null), setting.getString(LoginActivity.KEY_NAMA,null),
                setting.getString(LoginActivity.KEY_KODE_ORG,null), setting.getString(LoginActivity.KEY_ROLE,null),
                setting.getString(LoginActivity.KEY_KODE_IDENTITAS,null));

        // mendapatkan nilai-nilai yang dioper dari DaftarPermohonanK.class
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

            TextView waktuMulai = (TextView)findViewById(R.id.waktuMulai);
            waktuMulai.setText(peminjaman.getMulai());

            TextView waktuSelesai = (TextView)findViewById(R.id.waktuSelesai);
            waktuSelesai.setText(peminjaman.getSelesai());

            TextView peralatan = (TextView)findViewById(R.id.peralatan);
            peralatan.setText(peminjaman.getPeralatan());

            Button btnUpdate = (Button)findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    // set title
                    alertDialogBuilder.setTitle("Apakah anda yakin untuk mengupdate peralatan?");
                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Tekan Ya untuk menyetujui")
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/acceptByManajerKemahasiswaan/" + peminjaman.getId());
                                    if (notif.equals("\"sukses\"")){
                                        Toast.makeText(getApplicationContext(), "Peralatan berhasil diupdate", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Error. Permohonan tidak ada", Toast.LENGTH_SHORT).show();
                                    }
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
            Button btnSetuju = (Button)findViewById(R.id.btnSetuju);
            btnSetuju.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    // set title
                    alertDialogBuilder.setTitle("Apakah anda yakin untuk menyetujui permohonan ini?");
                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Tekan Ya untuk menyetujui")
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/acceptByManajerKemahasiswaan/" + peminjaman.getId());
                                    if (notif.equals("\"sukses\"")){
                                        Toast.makeText(getApplicationContext(), "Permohonan berhasil disetujui", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Error. Permohonan tidak ada", Toast.LENGTH_SHORT).show();
                                    }
                                    Intent i = new Intent(getApplicationContext(),MainActivityMR.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    i.putExtra("user",user);
                                    i.putExtra("navPosition",0);
                                    startActivity(i);
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
            Button btnTolak = (Button)findViewById(R.id.btnTolak);
            btnTolak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    // set title
                    alertDialogBuilder.setTitle("Apakah anda yakin untuk menolak permohonan ini?");
                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Tekan Ya untuk menolak")
                            .setCancelable(false)
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/rejectPeminjaman/"+ peminjaman.getId());
                                    if (notif.trim().equals("\"sukses\"")) {
                                        Toast.makeText(getApplicationContext(), "Permohonan berhasil ditolak", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error. Permohonan tidak ada", Toast.LENGTH_SHORT).show();
                                    }
                                    Intent i = new Intent(getApplicationContext(),MainActivityMR.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    i.putExtra("user",user);
                                    i.putExtra("navPosition",0);
                                    startActivity(i);
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
}


