package ppl.sipiru4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import ppl.sipiru4.Entity.Ruangan;
import ppl.sipiru4.controller.RuanganController;

public class DetailRuangan extends Activity {
    Context context;
    RuanganController ruanganController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActionBar()!=null) {
            getActionBar().setTitle("Detail Ruangan");
        }
        setContentView(R.layout.detail_ruangan);
        context = this;

		// mendapatkan semua informasi yang dioper dari kelas sebelumnya
        Bundle b = getIntent().getExtras();
        if (b!=null) {
            Ruangan ruangan = b.getParcelable("ruangan");
            ruanganController = new RuanganController(ruangan);

            final String waktuAwal = b.getString("waktuAwal");
            final String waktuAkhir = b.getString("waktuAkhir");

            Log.e("detail ruangan", ruanganController.getRuangan().getKode()+" "+ ruanganController.getRuangan().getNama()+" "
                    + ruanganController.getRuangan().getKapasitas()+" "+ ruanganController.getRuangan().getDeskripsi());
            Log.e("waktu", waktuAwal + " " + waktuAkhir);

            TextView namaRuangan = (TextView) findViewById(R.id.namaRuangan);
            namaRuangan.setText(ruanganController.getRuangan().getNama());

            final TextView kodeRuangan = (TextView) findViewById(R.id.kodeRuangan);
            kodeRuangan.setText(ruanganController.getRuangan().getKode());

            TextView kapasitas = (TextView) findViewById(R.id.kapasitas);
            kapasitas.setText("" + ruanganController.getRuangan().getKapasitas());

            TextView deskripsi = (TextView) findViewById(R.id.deskripsi);
            deskripsi.setText(ruanganController.getRuangan().getDeskripsi());

            Button pinjam = (Button) findViewById(R.id.buttonPinjam);
			// button pinjam akan mengarahkan pengguna ke form peminjaman
            pinjam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), FormPeminjaman.class);
                    i.putExtra("kodeRuangan",kodeRuangan.getText().toString());
                    i.putExtra("waktuAwal", waktuAwal);
                    i.putExtra("waktuAkhir", waktuAkhir);
                    startActivity(i);
                }
            });
        }
        else {
            Toast.makeText(this, "Error membuka halaman Detail Ruangan", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}