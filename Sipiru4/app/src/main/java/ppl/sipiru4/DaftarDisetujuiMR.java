package ppl.sipiru4;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import ppl.sipiru4.adapter.DaftarDisetujuiAdapterMR;
import ppl.sipiru4.model.DaftarDisetujuiItemMR;
import ppl.sipiru4.model.DaftarPendingItemMR;

public class DaftarDisetujuiMR extends Fragment {
    ListView lv;
    String listPendingNama;
    String listPendingRuangan;

    DaftarDisetujuiAdapterMR adapter;
    private ArrayList<DaftarDisetujuiItemMR> mItems;
    public DaftarDisetujuiMR(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarDisetujuiItemMR>();
        Resources resources = getResources();
        //TODO get daftar peminjaman yang dimiliki oleh manager ruangan dan masukkan npm peminjam dan
        // kode ruangan peminjam ke dalam ArrayList mItems untuk ditampilkan di Daftar peminjaman Manager ruangan
        JSONParser parser = new JSONParser();
        JSONArray hasil = parser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/displayManajerKemahasiswaan/");
        try {
            for(int i=0; i<hasil.length();i++){
                JSONObject data = hasil.getJSONObject(i);
                listPendingNama = data.getString("nama_peminjam");
                listPendingRuangan = data.getString("kode_ruangan");
                mItems.add(new DaftarDisetujuiItemMR((listPendingNama),(listPendingRuangan)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mItems.add(new DaftarDisetujuiItemMR(("Aditya Murda"), ("2304")));
        mItems.add(new DaftarDisetujuiItemMR(("Rafi Devandra"), ("3301")));
        mItems.add(new DaftarDisetujuiItemMR(("Yogie Clinov"), ("3302")));
        mItems.add(new DaftarDisetujuiItemMR(("Rauhil Fahmi"), ("3401")));
        mItems.add(new DaftarDisetujuiItemMR(("Gina Andriyani"), ("Aula")));
        adapter = new DaftarDisetujuiAdapterMR(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailDisetujuiMR.class);
                // passing array index
                startActivity(i);
            }
        });
        return rootView;
    }
}

