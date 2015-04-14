package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ppl.sipiru4.adapter.JamTersediaAdapter;
import ppl.sipiru4.model.JamTersediaItem;

public class DetailRuangan extends Activity {
    ListView lv;
    JamTersediaAdapter adapter;
    private ArrayList<JamTersediaItem> mItems;

    public DetailRuangan(){}


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.deskripsi_ruangan);

        TextView kapasitas = (TextView)findViewById(R.id.kapasitas);
        //TODO : get kapasitas ruangan
        kapasitas.setText("40");
        TextView fasilitas = (TextView)findViewById(R.id.fasilitas);
        //TODO :get fasilitas ruangan
        fasilitas.setText("mikrofon");
        lv = (ListView)findViewById(R.id.listJam);
        mItems = new ArrayList<JamTersediaItem>();
        //TODO: menampilkan kapan saja suatu ruangan bisa dipinjam
        mItems.add(new JamTersediaItem("07.00", "08.00"));
        mItems.add(new JamTersediaItem("05.00", "08.00"));
        adapter = new JamTersediaAdapter(getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //TODO : menampilkan form yang jamMulai, jamSelesai, tglMulai, dan tglSelesai sesuai dengan masukan pengguna
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FormPeminjaman.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }
}


