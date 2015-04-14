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

import ppl.sipiru4.adapter.DaftarPesanAdapterFI;
import ppl.sipiru4.adapter.DaftarPesanAdapterK;
import ppl.sipiru4.model.DaftarPesanItem;

public class DaftarPesanFI extends Fragment {
    ListView lv;

    DaftarPesanAdapterFI adapter;
    private ArrayList<DaftarPesanItem> mItems;
    public DaftarPesanFI(){}
    //private DaftarPesanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_daftarpesan, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarPesanItem>();
        Resources resources = getResources();
        //TODO : get daftar pesan FASUM ITF dan masukkan ke arraylist mItems
        mItems.add(new DaftarPesanItem(("Pesan FI"), resources.getDrawable(R.drawable.status_pinjaman) ));
        mItems.add(new DaftarPesanItem(("Pesan FI"), resources.getDrawable(R.drawable.status_pinjaman)));
        adapter = new DaftarPesanAdapterFI(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                //TODO : get detail pesan dari manager kemahasiswaan yang diklik
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPesan.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}