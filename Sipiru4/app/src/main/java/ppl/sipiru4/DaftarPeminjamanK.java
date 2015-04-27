package ppl.sipiru4;

//import android.app.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ppl.sipiru4.adapter.DaftarPeminjamanAdapterK;
import ppl.sipiru4.adapter.DaftarPermohonanAdapterK;
import ppl.sipiru4.model.DaftarPeminjamanItemK;
import ppl.sipiru4.model.DaftarPermohonanItemK;

public class DaftarPeminjamanK extends Fragment {
    ListView lv;

    DaftarPeminjamanAdapterK adapter;
    private ArrayList<DaftarPeminjamanItemK> mItems;
    public DaftarPeminjamanK(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarPeminjamanItemK>();
        Resources resources = getResources();
        //TODO : get data daftar permohonan yang diterima manager kemahasiswaan (npm peminjam dan ruangan yang dipinjam) dan masukkan ke arraylist untuk ditampilkan
        mItems.add(new DaftarPeminjamanItemK(("rauhil"), ("3113")));
        mItems.add(new DaftarPeminjamanItemK(("gina"), ("3304")));
        adapter = new DaftarPeminjamanAdapterK(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPeminjamanK.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}