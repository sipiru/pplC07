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

public class DetailPermohonanMR extends Fragment {
    Peminjaman peminjaman;

    public DetailPermohonanMR(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.validasi_mr, container, false);

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

        TextView peralatan = (TextView)rootView.findViewById(R.id.peralatan);
        peralatan.setText(peminjaman.getPeralatan());

        Button btnTeruskan = (Button)rootView.findViewById(R.id.btnTeruskan);
        btnTeruskan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk menyetujui permohonan ini?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk menyetujui")
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/acceptByManajerRuangan/"+ peminjaman.getId());
                                Toast.makeText(getActivity(),notif,Toast.LENGTH_SHORT).show();
                                DaftarPeminjamanMR.addToHistory(peminjaman);
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

        Button btnTolak = (Button)rootView.findViewById(R.id.btnTolak);
        btnTolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk menolak permohonan ini?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk menolak")
                        .setCancelable(false)
                        .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //TODO : kirim pesan penolakan ke peminjam
                                //TODO : hapus data permohonan
                                //TODO : panggil fragment daftar permohonan --UI
                                String notif = JSONParser.getNotifFromURL("http://ppl-c07.cs.ui.ac.id/connect/rejectPeminjaman/"+ peminjaman.getId());
                                Toast.makeText(getActivity(),notif,Toast.LENGTH_SHORT).show();
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
    return rootView;
    }
}


