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

import ppl.sipiru4.adapter.DaftarPeminjamanAdapterMR;
import ppl.sipiru4.model.DaftarPeminjamanItemMR;

public class DaftarPeminjamanMR extends Fragment {
    ListView lv;

    DaftarPeminjamanAdapterMR adapter;
    private ArrayList<DaftarPeminjamanItemMR> mItems;
    public DaftarPeminjamanMR(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);


        mItems = new ArrayList<DaftarPeminjamanItemMR>();
        Resources resources = getResources();
        //TODO get daftar peminjaman yang dimiliki oleh manager ruangan dan masukkan npm peminjam dan
        // kode ruangan peminjam ke dalam ArrayList mItems untuk ditampilkan di Daftar peminjaman Manager ruangan

        mItems.add(new DaftarPeminjamanItemMR(("1106022654"), ("3113")));
        mItems.add(new DaftarPeminjamanItemMR(("1106022452"), ("3304")));
        adapter = new DaftarPeminjamanAdapterMR(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPeminjamanMR.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}