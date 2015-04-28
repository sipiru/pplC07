package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.R;

public class DetailPeminjamanMR extends Fragment {
    Peminjaman peminjaman;

    public DetailPeminjamanMR(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_peminjaman_mr, container, false);

        //TODO menampilkan data peminjaman yang sudah disetujui manager ruangan
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

        Button download = (Button)rootView.findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : mengunduh berkas peminjaman
                Toast.makeText(getActivity(), "start downloading ....",
                        Toast.LENGTH_SHORT).show();
            }
        });

    return rootView;
    }
}


