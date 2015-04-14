package ppl.sipiru4;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ppl.sipiru4.adapter.DaftarRuanganAdapter;
import ppl.sipiru4.model.DaftarRuanganItem;

public class DaftarRuangan extends Fragment {
    ListView lv;

    DaftarRuanganAdapter adapter;
    private ArrayList<DaftarRuanganItem> mItems;
    public DaftarRuangan(){}
    //private DaftarRuanganItem mItems; // ListView items list



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_daftar_ruangan, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarRuanganItem>();
        Resources resources = getResources();
//        mItems.add(new DaftarRuanganItem(getString(R.string.hello_world) ));
//        mItems.add(new DaftarRuanganItem(getString(R.string.hello_world) ));
        //TODO : ambil kode ruangan, masukin list
        mItems.add(new DaftarRuanganItem("2305" ));
        mItems.add(new DaftarRuanganItem("2304"));
        adapter = new DaftarRuanganAdapter(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //TODO : get detail informasi suatu ruangan yang diklik
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