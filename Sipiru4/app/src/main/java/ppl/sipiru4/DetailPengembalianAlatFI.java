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
public class DetailPengembalianAlatFI extends Activity {
    final Context context = this;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_peminjaman_fi);
        //TODO : menampilkan detail peminjaman yang sudah disetujui oleh fasumITF
        TextView ruang = (TextView) findViewById(R.id.ruang);
        ruang.setText("3113");
        TextView nama = (TextView) findViewById(R.id.nama);
        nama.setText("Aditya Murda");
        TextView npm = (TextView) findViewById(R.id.npm);
        npm.setText("12602345");
        TextView prihal = (TextView) findViewById(R.id.prihal);
        prihal.setText("Rapat BEM");
        TextView tgl = (TextView) findViewById(R.id.tgl);
        tgl.setText("22 April 2015");
        TextView jam = (TextView) findViewById(R.id.jam);
        jam.setText("08.00-10.00");
        TextView fasilitas = (TextView) findViewById(R.id.fasilitas);
        fasilitas.setText("2 Mikrofon");
        Button selesai = (Button) findViewById(R.id.selesai);

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                // set title
                alertDialogBuilder.setTitle("Apakah semua peralatan sudah dikembalikan?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk konfirmasi")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                // current activity
                                //TODO : hapus semua data peminjaman ini disemua daftar peminjaman Role

                                Toast.makeText(getApplicationContext(), "Peminjaman sudah selesai",
                                        Toast.LENGTH_SHORT).show();
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
                alertDialog.show();
            }
        });
    }
}
