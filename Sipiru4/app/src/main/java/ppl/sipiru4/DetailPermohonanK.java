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

import ppl.sipiru4.Entity.Peminjaman;

public class DetailPermohonanK extends Activity {
    final Context context = this;
    Peminjaman peminjaman;
    Bundle b;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_permohonan_mk);

        // mendapatkan nilai-nilai yang dioper dari DaftarPermohonanMR.class
        b = getIntent().getExtras();
        peminjaman = b.getParcelable("peminjaman");
        Log.e("peminjaman", peminjaman.getKodeRuangan() + " " + peminjaman.getNamaP() + " " + peminjaman.getId());

        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText(peminjaman.getKodeRuangan());

        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText(peminjaman.getNamaP());

        TextView username = (TextView)findViewById(R.id.npm);
        username.setText(peminjaman.getUsernameP());

        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText(peminjaman.getPerihal());

        TextView tglMulai = (TextView)findViewById(R.id.tglMulai);
        TextView tglSelesai = (TextView)findViewById(R.id.tglSelesai);
        TextView jamMulai = (TextView)findViewById(R.id.mulai);
        TextView jamSelesai = (TextView)findViewById(R.id.selesai);
        TextView permintaanLain = (TextView)findViewById(R.id.permintaanLain);

        Button btnSetuju = (Button)findViewById(R.id.btnSetuju);
        btnSetuju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : update data permohonan
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk menyetujui permohonan ini?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk menyetujui")
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                Toast.makeText(getApplicationContext(), "Data permohonan berhasil disetujui",Toast.LENGTH_SHORT).show();
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
                //TODO : update data permohonan

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk menolak permohonan ini?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk menolak!")
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //TODO : kirim pesan penolakan ke peminjam bahwa permohonan ditolak oleh manager kemahasiswaan
                                //TODO : hapus data permohonan
                                //TODO : panggil fragment daftar permohonan --UI

                                Toast.makeText(getApplicationContext(), "Data permohonan berhasil ditolak",Toast.LENGTH_SHORT).show();
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
}