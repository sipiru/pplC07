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

import ppl.sipiru4.adapter.DaftarPesanAdapterP;
import ppl.sipiru4.model.DaftarPesanItem;

public class DaftarPesanMR extends Fragment {
    ListView lv;

    DaftarPesanAdapterP adapter;
    private ArrayList<DaftarPesanItem> mItems;
    public DaftarPesanMR(){}
    //private DaftarPesanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_daftarpesan, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarPesanItem>();
        Resources resources = getResources();
        //TODO : get data daftar pesan milik manager ruangan dan simpan di ArrayList beberapa potongan pesannya saja
        mItems.add(new DaftarPesanItem(("Pesan MR 1"), resources.getDrawable(R.drawable.status_pinjaman) ));
        mItems.add(new DaftarPesanItem(("Pesan MR 2"), resources.getDrawable(R.drawable.status_pinjaman)));
        adapter = new DaftarPesanAdapterP(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPesan.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}