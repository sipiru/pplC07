package ppl.sipiru4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ppl.sipiru4.adapter.DaftarHistoryAdapterFI;
import ppl.sipiru4.model.DaftarHistoryItemFI;

public class DaftarHistoryFI extends Fragment {
    ListView lv;

    DaftarHistoryAdapterFI adapter;
    private ArrayList<DaftarHistoryItemFI> mItems;
    public DaftarHistoryFI(){}
    //private DaftarPermohonanItem mItems; // ListView items list

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarHistoryItemFI>();
        Resources resources = getResources();
        /*TODO : get data peminjaman (yang sudah disetujui oleh Fasum) dan masukkan NPM dan kode ruangan yang dipinjam kedalam ArrayList mItems untuk ditampilkan di daftar peminjaman Fasum/ITF
        */
        mItems.add(new DaftarHistoryItemFI(("adit.murda"), ("3113"), ("2 Mikrofon")));
        mItems.add(new DaftarHistoryItemFI(("rauhil"), ("3304"), ("Proyektor")));
        adapter = new DaftarHistoryAdapterFI(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailHistoryFI.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}

