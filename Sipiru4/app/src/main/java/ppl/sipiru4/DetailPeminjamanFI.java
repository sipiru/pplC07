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

public class DetailPeminjamanFI extends Activity {
    final Context context = this;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_peminjaman_fi);
        //TODO : menampilkan detail peminjaman yang sudah disetujui oleh fasumITF
        TextView ruang = (TextView) findViewById(R.id.ruang);
        TextView nama = (TextView) findViewById(R.id.nama);
        TextView npm = (TextView) findViewById(R.id.npm);
        TextView prihal = (TextView) findViewById(R.id.prihal);
        TextView tgl = (TextView) findViewById(R.id.tgl);
        TextView jam = (TextView) findViewById(R.id.jam);
        TextView permintaanLain = (TextView) findViewById(R.id.permintaanLain);
        Button selesai = (Button) findViewById(R.id.btnSelesai);

                            selesai.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                            context);
                                    // set title
                                    alertDialogBuilder.setTitle("Apakah anda yakin peminjam sudah mengembalikan semua peralatan ?");

                                    // set dialog message
                                    alertDialogBuilder
                                            .setMessage("Tekan Ya untuk keluar!")
                                            .setCancelable(false)
                                            .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    // if this button is clicked, close
                                                    // current activity
                                                    //TODO : hapus semua data peminjaman ini disemua daftar peminjaman Role
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
}


