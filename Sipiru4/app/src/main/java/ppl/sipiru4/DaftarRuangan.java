package ppl.sipiru4;

//import android.app.Fragment;
import android.app.Activity;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.adapter.DaftarRuanganAdapter;
import ppl.sipiru4.model.DaftarRuanganItem;

public class DaftarRuangan extends Fragment {
    ListView lv;
    String namaRuangan;
    String tglmulai;
    String tglSelesai;
    String jamMulai;
    String jamSelesai;

    DaftarRuanganAdapter adapter;
    private ArrayList<DaftarRuanganItem> mItems;

    public DaftarRuangan() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarRuanganItem>();
        Resources resources = getResources();
        //TODO : menampilkan daftar ruangan yang bisa dipinjam
        //TODO : ambil kode ruangan, masukin list (yang ditampilkan kode ruangannya saja)
//        JSONParser parser = new JSONParser();
//        JSONArray hasil = parser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/ruangan/");
//        try {
//            JSONObject data = hasil.getJSONObject(0);
//            namaRuangan = data.getString("nama");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        mItems.add(new DaftarRuanganItem("R2303", "40", "Ruang Kelas"));
        mItems.add(new DaftarRuanganItem("R2403", "50", "Ruang Kelas"));
        mItems.add(new DaftarRuanganItem("Aula", "300", "Aula utama gedung B"));
        mItems.add(new DaftarRuanganItem("R3133", "300", "Ruang kelas gedung C"));
        adapter = new DaftarRuanganAdapter(getActivity().getApplicationContext(), mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //TODO : get detail informasi suatu ruangan yang diklik, ditampilkan dikelas DetailRuangan
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailRuangan.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });


        return rootView;
    }
}