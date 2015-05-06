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

public class DetailRuangan extends Activity {
    Context context;
    Ruangan ruangan;

//    public DetailRuangan(){}
//
//    public DetailRuangan(Ruangan ruangan) {
//        this.ruangan = ruangan;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_ruangan);
        context = this;

        Bundle b = getIntent().getExtras();
        if (b!=null) {
            ruangan = b.getParcelable("ruangan");
            final String waktuAwal = b.getString("waktuAwal");
            final String waktuAkhir = b.getString("waktuAkhir");
            Log.e("detail ruangan", ruangan.getKode()+" "+ruangan.getNama()+" "+ruangan.getKapasitas()+" "+ruangan.getDeskripsi());
            Log.e("waktu", waktuAwal + " " + waktuAkhir);

            TextView namaRuangan = (TextView) findViewById(R.id.namaRuangan);
            namaRuangan.setText(ruangan.getNama());

            final TextView kodeRuangan = (TextView) findViewById(R.id.kodeRuangan);
            kodeRuangan.setText(ruangan.getKode());

            TextView kapasitas = (TextView) findViewById(R.id.kapasitas);
            kapasitas.setText(""+ruangan.getKapasitas());

            TextView deskripsi = (TextView) findViewById(R.id.deskripsi);
            deskripsi.setText(ruangan.getDeskripsi());

//        lv = (ListView) findViewById(R.id.listJam);
//        final ArrayList<JamTersediaItem> mItems = new ArrayList<>();
//
//        JSONArray jArray = JSONParser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/jadwalRuangan/"+kodeRuangan.getText());
//        for (int i = 0 ; i < jArray.length(); i++) {
//            JSONObject jJadwal;
//            try {
//                jJadwal = jArray.getJSONObject(i);
//                mItems.add(new JamTersediaItem(jJadwal.getString("waktu_awal_pinjam"),jJadwal.getString("waktu_akhir_pinjam")));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        adapter = new JamTersediaAdapter(getApplicationContext(), mItems);
//        lv.setAdapter(adapter);

            Button pinjam = (Button) findViewById(R.id.buttonPinjam);
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
            Intent i = new Intent(getApplicationContext(),MainActivityP.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}


