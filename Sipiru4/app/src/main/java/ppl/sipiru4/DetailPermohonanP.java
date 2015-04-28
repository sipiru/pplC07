package ppl.sipiru4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;

public class DetailPermohonanP extends Fragment {
    Peminjaman peminjaman;

    public DetailPermohonanP(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_permohonan_p, container, false);

        TextView ruang = (TextView)rootView.findViewById(R.id.ruang);
        ruang.setText(peminjaman.getKodeRuangan());

        TextView nama = (TextView)rootView.findViewById(R.id.nama);
        nama.setText(peminjaman.getNamaP());

        TextView username = (TextView)rootView.findViewById(R.id.username);
        username.setText(peminjaman.getUsernameP());

        TextView prihal = (TextView)rootView.findViewById(R.id.prihal);
        prihal.setText(peminjaman.getPerihal());

        TextView waktuMulai = (TextView)rootView.findViewById(R.id.waktuMulai);
        waktuMulai.setText(peminjaman.getMulai());

        TextView waktuSelesai = (TextView)rootView.findViewById(R.id.waktuSelesai);
        waktuSelesai.setText(peminjaman.getSelesai());

        TextView peralatan = (TextView)rootView.findViewById(R.id.permintaanlain);
        peralatan.setText(peminjaman.getPeralatan());

        Button batal = (Button)rootView.findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk membatalkan permohonan?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk membatalkan permohonan")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, close
                                //TODO: hapus data permohonan yang diklik
                                String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/membatalkanPermohonan/"+peminjaman.getId());
                                Toast.makeText(getActivity(),notif,Toast.LENGTH_SHORT).show();
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
    return rootView;
    }
}


