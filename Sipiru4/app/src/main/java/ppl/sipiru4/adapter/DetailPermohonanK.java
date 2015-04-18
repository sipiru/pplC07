package ppl.sipiru4.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ppl.sipiru4.R;

public class DetailPermohonanK extends Activity {
    final Context context = this;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.validasi_mr);
        //TODO : generate dari data permohonan
        TextView ruang = (TextView)findViewById(R.id.ruang);
        TextView nama = (TextView)findViewById(R.id.nama);
        TextView npm = (TextView)findViewById(R.id.npm);
        TextView prihal = (TextView)findViewById(R.id.prihal);
        TextView tglMulai = (TextView)findViewById(R.id.tglMulai);
        TextView tglSelesai = (TextView)findViewById(R.id.tglSelesai);
        TextView jamMulai = (TextView)findViewById(R.id.mulai);
        TextView jamSelesai = (TextView)findViewById(R.id.selesai);
        TextView permintaanLain = (TextView)findViewById(R.id.permintaanLain);

        Button btnTeruskan = (Button)findViewById(R.id.btnteruskan);
        btnTeruskan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : send data ke manager kemahasiswaan
                Toast.makeText(getApplicationContext(), "Data permohonan berhasil di teruskan ke Manager Kemahasiswaan",
                        Toast.LENGTH_SHORT).show();
            }
        });
        Button btnUpdate = (Button)findViewById(R.id.btnSetuju);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : update data permohonan
                Toast.makeText(getApplicationContext(), "Data permohonan berhasil di update",
                        Toast.LENGTH_SHORT).show();
            }
        });
        Button btnTolak = (Button)findViewById(R.id.btnTolak);
        btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : update data permohonan

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk menolak permohonan ini?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk menolak!")
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //TODO : kirim pesan penolakan ke peminjam
                                //TODO : hapus data permohonan
                                //TODO : panggil fragment daftar permohonan --UI
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

                // show it
                alertDialog.show();
            }
        });
    }
}


