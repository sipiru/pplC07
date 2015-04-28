package ppl.sipiru4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import ppl.sipiru4.Entity.Peminjaman;

public class DetailPeminjamanP extends Fragment {
    Peminjaman peminjaman;

    public DetailPeminjamanP(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_peminjaman_p, container, false);

        TextView ruang = (TextView)rootView.findViewById(R.id.ruang);
        ruang.setText(peminjaman.getKodeRuangan());

        TextView nama = (TextView)rootView.findViewById(R.id.nama);
        nama.setText(peminjaman.getNamaP());

        TextView username = (TextView)rootView.findViewById(R.id.username);
        username.setText(peminjaman.getUsernameP());

        TextView prihal = (TextView)rootView.findViewById(R.id.prihal);
        prihal.setText(peminjaman.getPerihal());

        TextView mulai = (TextView)rootView.findViewById(R.id.waktuMulai);
        mulai.setText(peminjaman.getMulai());

        TextView selesai = (TextView)rootView.findViewById(R.id.waktuSelesai);
        selesai.setText(peminjaman.getSelesai());

        TextView peralatan = (TextView)rootView.findViewById(R.id.permintaanlain);
        peralatan.setText(peminjaman.getPeralatan());

        return rootView;
    }
}


