package ppl.sipiru4;

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

import ppl.sipiru4.adapter.DaftarPeminjamanAdapterFI;
import ppl.sipiru4.adapter.DaftarPeminjamanAdapterMR;
import ppl.sipiru4.model.DaftarPeminjamanItemFI;
import ppl.sipiru4.model.DaftarPeminjamanItemMR;

public class DaftarPeminjamanFI extends Fragment {
    ListView lv;

    DaftarPeminjamanAdapterFI adapter;
    private ArrayList<DaftarPeminjamanItemFI> mItems;
    public DaftarPeminjamanFI(){}
    //private DaftarPermohonanItem mItems; // ListView items list

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);


        mItems = new ArrayList<DaftarPeminjamanItemFI>();
        Resources resources = getResources();
        /*TODO : get data peminjaman (yang sudah disetujui oleh Fasum) dan masukkan NPM dan kode ruangan yang dipinjam kedalam ArrayList mItems untuk ditampilkan di daftar peminjaman Fasum/ITF
        */
        mItems.add(new DaftarPeminjamanItemFI(("1106022654FI"), ("3113")));
        mItems.add(new DaftarPeminjamanItemFI(("1106022452FI"), ("3304")));
        adapter = new DaftarPeminjamanAdapterFI(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPeminjamanFI.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}