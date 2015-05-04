package ppl.sipiru4;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ppl.sipiru4.Entity.Peminjaman;

public class DetailPeminjamanMR extends Activity {
    Peminjaman peminjaman;
    int posisi;
    Bundle b;

//    public DetailPeminjamanMR(Peminjaman peminjaman) {
//        this.peminjaman = peminjaman;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_peminjaman_mr);

        // mendapatkan nilai-nilai yang dioper dari DaftarPermohonanMR.class
        b = getIntent().getExtras();
        peminjaman = b.getParcelable("peminjaman");
        posisi = b.getInt("posisi");
        Log.e("peminjaman", peminjaman.getKodeRuangan() + " " + peminjaman.getNamaP() + " " + peminjaman.getId() + " posisi " + posisi);

        TextView ruang = (TextView)findViewById(R.id.ruang);
        ruang.setText(peminjaman.getKodeRuangan());

        TextView nama = (TextView)findViewById(R.id.nama);
        nama.setText(peminjaman.getNamaP());

        TextView username = (TextView)findViewById(R.id.username);
        username.setText(peminjaman.getUsernameP());

        TextView prihal = (TextView)findViewById(R.id.prihal);
        prihal.setText(peminjaman.getPerihal());

        TextView waktuMulai = (TextView)findViewById(R.id.waktuMulai);
        waktuMulai.setText(peminjaman.getMulai());

        TextView waktuSelesai = (TextView)findViewById(R.id.waktuSelesai);
        waktuSelesai.setText(peminjaman.getSelesai());

        TextView peralatan = (TextView)findViewById(R.id.peralatan);
        peralatan.setText(peminjaman.getPeralatan());

        Button download = (Button)findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "start downloading ....",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}


