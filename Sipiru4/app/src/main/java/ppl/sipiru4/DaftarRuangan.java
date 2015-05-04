package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import ppl.sipiru4.Entity.Ruangan;
import ppl.sipiru4.adapter.DaftarRuanganAdapter;

public class DaftarRuangan extends Activity {
    ListView lv;
    JSONArray jArray;
    DaftarRuanganAdapter adapter;

    public DaftarRuangan(){
    }

//    public DaftarRuangan(JSONArray input) {
//        jArray = input;
//    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_daftar_ruangan);

        Bundle b = getIntent().getExtras();
        try {
            jArray = new JSONArray(b.getString("daftarRuangan"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("jArray daftar ruangan", jArray+"");
        final String waktuAwal = b.getString("waktuAwal");
        final String waktuAkhir = b.getString("waktuAkhir");
        Log.e("waktu daftar ruangan", waktuAwal+" "+waktuAkhir);

        lv = (ListView) findViewById(R.id.list);
        final ArrayList<Ruangan> mItems = new ArrayList<>();

        if (jArray == null) {
            return ;
        }

        for (int i = 0 ; i < jArray.length() ; i++) {
            JSONObject jsonRuangan;
            try {
                jsonRuangan = jArray.getJSONObject(i);
                assert jsonRuangan != null;
                String kode = jsonRuangan.getString("kode");
                String nama = jsonRuangan.getString("nama");
                String kapasitas = "" + jsonRuangan.getInt("kapasitas");
                String deskripsi = jsonRuangan.getString("deskripsi");

                mItems.add(new Ruangan(kode, nama, kapasitas, deskripsi));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.e("mitems", mItems.toString());

        adapter = new DaftarRuanganAdapter(getApplicationContext(), mItems);
        Log.e("adapter", adapter.toString());
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), DetailRuangan.class);
                // passing array index
                i.putExtra("ruangan", mItems.get(position));
                i.putExtra("waktuAwal", waktuAwal);
                i.putExtra("waktuAkhir", waktuAkhir);
                startActivity(i);
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame_container, new DetailRuangan(mItems.get(position)));
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}