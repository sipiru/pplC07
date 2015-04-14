package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DetailPermohonanP extends FragmentActivity {
    final Context context = this;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_permohonan_p);
        TextView ruang = (TextView)findViewById(R.id.Ruang);
        TextView nama = (TextView)findViewById(R.id.Nama);
        TextView npm = (TextView)findViewById(R.id.NPM);
        TextView prihal = (TextView)findViewById(R.id.Prihal);
        TextView tgl = (TextView)findViewById(R.id.Tgl);
        TextView jam = (TextView)findViewById(R.id.Jam);
        TextView permintaanLain = (TextView)findViewById(R.id.permintaanlain);
        Button batal = (Button)findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO :
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk membatalkan permohonan ?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk membatalkan permohonan!")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                Fragment a = new DaftarPermohonanP();
                                //TODO: hapus data permohonan yang dklik
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


