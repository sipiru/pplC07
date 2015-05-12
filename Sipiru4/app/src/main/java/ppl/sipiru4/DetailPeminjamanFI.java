package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import ppl.sipiru4.Entity.Peminjaman;

public class DetailPeminjamanFI extends Activity {
    Peminjaman peminjaman;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_peminjaman_fi);

        Bundle b = getIntent().getExtras();
        peminjaman = b.getParcelable("peminjaman");
        Log.e("peminjaman", peminjaman.getKodeRuangan() + " " + peminjaman.getNamaP() + " " + peminjaman.getId());

        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText(peminjaman.getKodeRuangan());

        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText(peminjaman.getNamaP());

        TextView username = (TextView)findViewById(R.id.username);
        username.setText(peminjaman.getUsernameP());

        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText(peminjaman.getPerihal());

        TextView kegiatan = (TextView)findViewById(R.id.kegiatan);
        kegiatan.setText(peminjaman.getKegiatan());

        TextView mulai = (TextView)findViewById(R.id.waktuMulai);
        mulai.setText(peminjaman.getMulai());

        TextView selesai = (TextView)findViewById(R.id.waktuSelesai);
        selesai.setText(peminjaman.getSelesai());

        TextView peralatan = (TextView)findViewById(R.id.peralatan);
        peralatan.setText(peminjaman.getPeralatan());

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}


