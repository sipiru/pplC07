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

import ppl.sipiru4.adapter.DaftarPermohonanAdapterK;
import ppl.sipiru4.model.DaftarPeminjamanItemMR;
import ppl.sipiru4.model.DaftarPermohonanItemK;

public class DaftarPermohonanK extends Fragment {
    ListView lv;

    DaftarPermohonanAdapterK adapter;
    private ArrayList<DaftarPermohonanItemK> mItems;
    public DaftarPermohonanK(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);


        mItems = new ArrayList<DaftarPermohonanItemK>();
        Resources resources = getResources();
        //TODO : get data daftar permohonan di manager kemahasiswaan (npm peminjam dan ruangan yang dipinjam) dan masukkan ke arraylist
        mItems.add(new DaftarPermohonanItemK(("rafi.devandra"), ("3113")));
        mItems.add(new DaftarPermohonanItemK(("adit.murda"), ("3304")));
        adapter = new DaftarPermohonanAdapterK(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPermohonanK.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}