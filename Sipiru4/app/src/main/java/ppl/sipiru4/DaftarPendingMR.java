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
import ppl.sipiru4.adapter.DaftarPendingAdapterMR;
import ppl.sipiru4.model.DaftarPendingItemMR;

public class DaftarPendingMR extends Fragment {
    ListView lv;
    String listPendingNama;
    String listPendingRuangan;
    DaftarPendingAdapterMR adapter;
    private ArrayList<DaftarPendingItemMR> mItems;
    //private DaftarPermohonanItem mItems; // ListView items list

    public DaftarPendingMR(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);

        mItems = new ArrayList<DaftarPendingItemMR>();
        Resources resources = getResources();
        //TODO get data permohonan di manager ruangan
        JSONParser parser = new JSONParser();
        JSONArray hasil = parser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/displayManajerRuangan/");
        try {
            for(int i=0; i<hasil.length();i++){
                JSONObject data = hasil.getJSONObject(i);
                listPendingNama = data.getString("username_peminjam");
                listPendingRuangan = data.getString("kode_ruangan");
                mItems.add(new DaftarPendingItemMR((listPendingNama),(listPendingRuangan)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mItems.add(new DaftarPendingItemMR(("1106022654"),("2303")));
        mItems.add(new DaftarPendingItemMR(("1106549872"),("2302")));
        adapter = new DaftarPendingAdapterMR(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPendingMR.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}

