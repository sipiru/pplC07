package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.controller.PeminjamanController;

public class DetailHistoryFI extends Activity {
    PeminjamanController peminjamanController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar()!=null) {
            getActionBar().setTitle("Detail History");
        }
        setContentView(R.layout.detail_history_fi);

        Bundle b = getIntent().getExtras();
        Peminjaman peminjaman = b.getParcelable("peminjaman");
        peminjamanController = new PeminjamanController(peminjaman);
        Log.e("peminjaman", peminjamanController.getPeminjaman().getKodeRuangan() + " " + peminjamanController.getPeminjaman().getNamaP()
                + " " + peminjamanController.getPeminjaman().getId());

        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText(peminjamanController.getPeminjaman().getKodeRuangan());

        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText(peminjamanController.getPeminjaman().getNamaP());

        TextView username = (TextView)findViewById(R.id.username);
        username.setText(peminjamanController.getPeminjaman().getUsernameP());

        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText(peminjamanController.getPeminjaman().getPerihal());

        TextView kegiatan = (TextView)findViewById(R.id.kegiatan);
        kegiatan.setText(peminjamanController.getPeminjaman().getKegiatan());

        TextView mulai = (TextView)findViewById(R.id.waktuMulai);
        mulai.setText(peminjamanController.getPeminjaman().getMulai());

        TextView selesai = (TextView)findViewById(R.id.waktuSelesai);
        selesai.setText(peminjamanController.getPeminjaman().getSelesai());

        TextView peralatan = (TextView)findViewById(R.id.peralatan);
        peralatan.setText(peminjamanController.getPeminjaman().getPeralatan());

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}


