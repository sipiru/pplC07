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

import ppl.sipiru4.adapter.DaftarDisetujuiAdapterK;
import ppl.sipiru4.model.DaftarDisetujuiItemK;

public class DaftarDisetujuiK extends Fragment {
    ListView lv;

    DaftarDisetujuiAdapterK adapter;
    private ArrayList<DaftarDisetujuiItemK> mItems;
    public DaftarDisetujuiK(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarDisetujuiItemK>();
        Resources resources = getResources();
        //TODO : get data daftar permohonan yang diterima manager kemahasiswaan (npm peminjam dan ruangan yang dipinjam) dan masukkan ke arraylist untuk ditampilkan
        mItems.add(new DaftarDisetujuiItemK(("Rauhil Fahmi"), ("3113")));
        mItems.add(new DaftarDisetujuiItemK(("Gina Andriyani"), ("3304")));
        adapter = new DaftarDisetujuiAdapterK(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailDisetujuiK.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}
