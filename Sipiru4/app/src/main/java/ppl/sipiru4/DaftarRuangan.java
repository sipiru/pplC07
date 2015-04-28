package ppl.sipiru4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ppl.sipiru4.Entity.Ruangan;
import ppl.sipiru4.adapter.DaftarRuanganAdapter;

public class DaftarRuangan extends Fragment {
    ListView lv;
    JSONArray jArray;
    DaftarRuanganAdapter adapter;

    public DaftarRuangan(){
    }

    public DaftarRuangan(JSONArray input) {
        jArray = input;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_daftar_ruangan, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);

        final ArrayList<Ruangan> mItems = new ArrayList<>();

        if (jArray == null) {
            return rootView;
        }

        for (int i = 0 ; i < jArray.length() ; i++) {
            JSONObject jsonRuangan;
            try {
                jsonRuangan = jArray.getJSONObject(i);
                assert jsonRuangan != null;
                String kode = jsonRuangan.getString("kode");
                String nama = jsonRuangan.getString("nama");
                int kapasitas = jsonRuangan.getInt("kapasitas");
                String deskripsi = jsonRuangan.getString("deskripsi");

                mItems.add(new Ruangan(kode, nama, kapasitas, deskripsi));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        adapter = new DaftarRuanganAdapter(getActivity().getApplicationContext(), mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                // Sending image id to FullScreenActivity
//                Intent i = new Intent(getActivity().getApplicationContext(), DetailRuangan.class);
//                // passing array index
//                i.putExtra("id", position);
//                startActivity(i);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new DetailRuangan(mItems.get(position)));
                Toast.makeText(getActivity(),"detail ruangan", Toast.LENGTH_SHORT).show();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}