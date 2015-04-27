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

import ppl.sipiru4.adapter.DaftarPermohonanAdapterP;
import ppl.sipiru4.model.DaftarPermohonanItemP;

public class DaftarPermohonanP extends Fragment {
    ListView lv;

    DaftarPermohonanAdapterP adapter;
    private ArrayList<DaftarPermohonanItemP> mItems;
    public DaftarPermohonanP(){}
    //private DaftarPermohonanItem mItems; // ListView items list


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);

        mItems = new ArrayList<DaftarPermohonanItemP>();
        Resources resources = getResources();
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
        //TODO : get daftar permohonan peminjam, masukkan nama ruangan yang dipinjam dan statusnya ke ArrayList

        mItems.add(new DaftarPermohonanItemP("2304","2"));
        mItems.add(new DaftarPermohonanItemP("2404","1"));

        adapter = new DaftarPermohonanAdapterP(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                //TODO : menampilkan detail permohonan yang diklik
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPermohonanP.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}