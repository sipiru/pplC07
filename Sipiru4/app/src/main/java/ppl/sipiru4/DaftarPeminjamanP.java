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

import ppl.sipiru4.adapter.DaftarPeminjamanAdapterP;
import ppl.sipiru4.model.DaftarPeminjamanItemP;

public class DaftarPeminjamanP extends Fragment {
    ListView lv;

    DaftarPeminjamanAdapterP adapter;
    private ArrayList<DaftarPeminjamanItemP> mItems;

    public DaftarPeminjamanP(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);


        mItems = new ArrayList<DaftarPeminjamanItemP>();
        Resources resources = getResources();
        //TODO : get data nama ruangan dan status peminjaman dari data peminjaman, masukkin array mItems
        mItems.add(new DaftarPeminjamanItemP(("2304"), ("closed")));
        mItems.add(new DaftarPeminjamanItemP(("2303"), ("closed")));
        adapter = new DaftarPeminjamanAdapterP(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPeminjamanP.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}