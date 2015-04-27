package ppl.sipiru4;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ppl.sipiru4.adapter.DaftarPendingAdapterP;
import ppl.sipiru4.model.DaftarPendingItemP;

public class DaftarPendingP extends Fragment {
    ListView lv;

    DaftarPendingAdapterP adapter;
    private ArrayList<DaftarPendingItemP> mItems;
    public DaftarPendingP(){}
    //private DaftarPermohonanItem mItems; // ListView items list


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);

        mItems = new ArrayList<DaftarPendingItemP>();
        Resources resources = getResources();
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
        //TODO : get daftar permohonan peminjam, masukkan nama ruangan yang dipinjam dan statusnya ke ArrayList

        mItems.add(new DaftarPendingItemP("2304","Sudah disetujui oleh Manajer Ruangan"));
        mItems.add(new DaftarPendingItemP("2404","Sudah disetujui oleh Kemahasiswaan"));

        adapter = new DaftarPendingAdapterP(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                //TODO : menampilkan detail permohonan yang diklik
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPendingP.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}

