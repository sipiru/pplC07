package ppl.sipiru4;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gina on 4/24/2015.
 */
public class DetailPendingP extends FragmentActivity {
    final Context context = this;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_permohonan_p);
        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText("R2304");
        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText("Gina Andriyani");
        TextView npm = (TextView)findViewById(R.id.npm);
        npm.setText("1106022654");
        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText("PSAF");
        TextView tgl = (TextView)findViewById(R.id.tgl);
        tgl.setText("30 Juni 2015");
        TextView jam = (TextView)findViewById(R.id.jam);
        jam.setText("08.00 - 13.00");
        TextView permintaanLain = (TextView)findViewById(R.id.permintaanlain);
        Button batal = (Button)findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk membatalkan permohonan ?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk membatalkan permohonan!")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                Fragment a = new DaftarPendingP();
                                //TODO: hapus data permohonan yang diklik

                                //TODO: pindah ke fragment DaftarPermohonanP
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
