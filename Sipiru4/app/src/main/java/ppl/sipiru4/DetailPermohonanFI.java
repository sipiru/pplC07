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

public class DetailPermohonanFI extends Activity {
    final Context context = this;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_permohonan_mk);
        //TODO : generate dari deskripsi suatu permohonan yang diterima oleh Fasum
        TextView ruang = (TextView)findViewById(R.id.ruang);
        TextView nama = (TextView)findViewById(R.id.nama);
        TextView npm = (TextView)findViewById(R.id.npm);
        TextView prihal = (TextView)findViewById(R.id.prihal);
        TextView tglMulai = (TextView)findViewById(R.id.tglMulai);
        TextView tglSelesai = (TextView)findViewById(R.id.tglSelesai);
        TextView jamMulai = (TextView)findViewById(R.id.mulai);
        TextView jamSelesai = (TextView)findViewById(R.id.selesai);
        TextView permintaanLain = (TextView)findViewById(R.id.permintaanLain);


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
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //TODO : update data permohonan
                                Toast.makeText(getApplicationContext(), "Data permohonan berhasil disetujui",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
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
                                //TODO : kirim pesan penolakan ke peminjam bahwa permohonan ditolak oleh FASUM/ITF
                                //TODO : hapus data permohonan
                                //TODO : panggil fragment daftar permohonan --UI

                                Toast.makeText(getApplicationContext(), "Data permohonan berhasil ditolak",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
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


