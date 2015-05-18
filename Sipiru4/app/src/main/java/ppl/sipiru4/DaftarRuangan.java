package ppl.sipiru4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import ppl.sipiru4.Entity.Ruangan;
import ppl.sipiru4.adapter.DaftarRuanganAdapter;
import ppl.sipiru4.controller.RuanganController;

public class DaftarRuangan extends Activity {
    ListView lv;
    Context context;
    JSONArray jArray;
    DaftarRuanganAdapter adapter;
    RuanganController ruanganController;

    public DaftarRuangan(){
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_daftar_ruangan);
        context = this;

        Bundle b = getIntent().getExtras();
        if (b!=null) {
            try {
                jArray = new JSONArray(b.getString("daftarRuangan"));
                ruanganController = new RuanganController(jArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final String waktuAwal = b.getString("waktuAwal");
            final String waktuAkhir = b.getString("waktuAkhir");

            lv = (ListView) findViewById(R.id.list);
            final ArrayList<Ruangan> mItems = new ArrayList<>();

            if (jArray == null) {
                return ;
            }

            for (int i = 0 ; i < ruanganController.getSize() ; i++) {
                mItems.add(ruanganController.getRuangan(i));
            }
//            Log.e("mitems", mItems.toString());

            adapter = new DaftarRuanganAdapter(getApplicationContext(), mItems);
            Log.e("adapter", adapter.toString());
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent i = new Intent(getApplicationContext(), DetailRuangan.class);
                    // mengoper ke kelas yang akan dipanggil
                    i.putExtra("ruangan", ruanganController.getRuangan(position));
                    i.putExtra("waktuAwal", waktuAwal);
                    i.putExtra("waktuAkhir", waktuAkhir);
                    startActivity(i);
                }
            });
        }
        else {
            Toast.makeText(context, "Error membuka halaman Daftar Ruangan",Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, MainActivityP.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}