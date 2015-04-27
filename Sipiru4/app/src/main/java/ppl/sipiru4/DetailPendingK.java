package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Gina on 4/24/2015.
 */
public class DetailPendingK extends Activity {
    final Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_permohonan_mk);
        //TODO : generate dari data permohonan
        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText("R3113");
        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText("Rafi Devandra");
        TextView npm = (TextView)findViewById(R.id.npm);
        npm.setText("1106029845");
        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText("Rapat BEM");
        TextView tgl = (TextView)findViewById(R.id.tanggal);
        tgl.setText("20 Maret 2015");
        TextView jam = (TextView)findViewById(R.id.jam);
        jam.setText("14.00 - 16.00");
        TextView fasilitas = (TextView)findViewById(R.id.fasilitas);
        fasilitas.setText("1 Mikrofon");
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

                                Toast.makeText(getApplicationContext(), "Data permohonan berhasil disetujui", Toast.LENGTH_SHORT).show();
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
