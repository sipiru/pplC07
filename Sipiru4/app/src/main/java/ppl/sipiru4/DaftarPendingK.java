package ppl.sipiru4;

//import android.app.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.StrictMode;
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
import ppl.sipiru4.adapter.DaftarPendingAdapterK;
import ppl.sipiru4.model.DaftarPendingItemK;

public class DaftarPendingK extends Fragment {
    ListView lv;
    String listPendingNPM;
    String listPendingRuangan;

    DaftarPendingAdapterK adapter;
    private ArrayList<DaftarPendingItemK> mItems;
    public DaftarPendingK(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mItems = new ArrayList<DaftarPendingItemK>();
        JSONParser parser = new JSONParser();
        JSONArray hasil = parser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/displayManajerKemahasiswaan/");
        try {

                JSONObject data = hasil.getJSONObject(0);
                listPendingNPM = data.getString("username_peminjam");
                listPendingRuangan = data.getString("kode_ruangan");
                mItems.add(new DaftarPendingItemK((listPendingNPM),(listPendingRuangan)));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Resources resources = getResources();
        //TODO : get data daftar permohonan di manager kemahasiswaan (npm peminjam dan ruangan yang dipinjam) dan masukkan ke arraylist
        mItems.add(new DaftarPendingItemK(("1106029845"), ("3113")));
        mItems.add(new DaftarPendingItemK(("1206032543"), ("3304")));
        adapter = new DaftarPendingAdapterK(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPendingK.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}

