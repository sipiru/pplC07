package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        String date1 = peminjamanController.getPeminjaman().getMulai();
        String date2 = peminjamanController.getPeminjaman().getSelesai();
        String dateView1 = null;
        String dateView2 = null;
        try {
            Date init1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date1);
            dateView1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(init1);
            Date init2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date2);
            dateView2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(init2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        mulai.setText(dateView1);

        TextView selesai = (TextView)findViewById(R.id.waktuSelesai);
        selesai.setText(dateView2);

        TextView peralatan = (TextView)findViewById(R.id.peralatan);
        peralatan.setText(peminjamanController.getPeminjaman().getPeralatan());

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}


