package ppl.sipiru4;

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
import java.util.List;

import ppl.sipiru4.adapter.DaftarPeminjamanAdapter;
import ppl.sipiru4.adapter.DaftarPermohonanAdapter;
import ppl.sipiru4.adapter.DaftarRuanganAdapter;
import ppl.sipiru4.model.DaftarPeminjamanItem;
import ppl.sipiru4.model.DaftarRuanganItem;

public class DaftarRuangan extends Fragment {
    ListView lv;

    DaftarRuanganAdapter adapter;
    private ArrayList<DaftarRuanganItem> mItems;
    public DaftarRuangan(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daftar_permohonan_ui, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);


        mItems = new ArrayList<DaftarRuanganItem>();
        Resources resources = getResources();
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
        mItems.add(new DaftarRuanganItem(getString(R.string.hello_world)));
        mItems.add(new DaftarRuanganItem(getString(R.string.hello_world)));
        adapter = new DaftarRuanganAdapter(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailRuangan.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }

    private void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
    }
}