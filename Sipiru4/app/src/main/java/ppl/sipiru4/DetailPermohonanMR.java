package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;

public class DetailPermohonanMR extends Activity {
    final Context context = this;
    Peminjaman peminjaman;
    int posisi;
    Bundle b;

//    public DetailPermohonanMR(Peminjaman peminjaman) {
//        this.peminjaman = peminjaman;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validasi_mr);

        // mendapatkan nilai-nilai yang dioper dari DaftarPermohonanMR.class
        b = getIntent().getExtras();
        if (b!=null){
            peminjaman = b.getParcelable("peminjaman");
            posisi = b.getInt("posisi");
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

            Button btnTeruskan = (Button)findViewById(R.id.btnTeruskan);
            btnTeruskan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set title
                    alertDialogBuilder.setTitle("Apakah anda yakin untuk meneruskan permohonan ini?");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Tekan Ya untuk meneruskan")
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/acceptByManajerRuangan/"+ peminjaman.getId());
                                    if (notif.equals("\"sukses\"")){
                                        Toast.makeText(getApplicationContext(), "Permohonan berhasil diteruskan", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Error. Permohonan tidak ada", Toast.LENGTH_SHORT).show();
                                    }
                                    finish();
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
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/rejectPeminjaman/"+ peminjaman.getId());
                                    if (notif.trim().equals("\"sukses\"")) {
                                        Toast.makeText(getApplicationContext(), "Permohonan berhasil ditolak", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error. Permohonan tidak ada", Toast.LENGTH_SHORT).show();
                                    }
                                    finish();
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
}


