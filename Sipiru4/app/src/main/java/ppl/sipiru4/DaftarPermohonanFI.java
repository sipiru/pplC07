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

import ppl.sipiru4.adapter.DaftarPermohonanAdapterFI;
import ppl.sipiru4.adapter.DaftarPermohonanAdapterK;
import ppl.sipiru4.model.DaftarPermohonanItemFI;
import ppl.sipiru4.model.DaftarPermohonanItemK;

public class DaftarPermohonanFI extends Fragment {
    ListView lv;

    DaftarPermohonanAdapterFI adapter;
    private ArrayList<DaftarPermohonanItemFI> mItems;
    public DaftarPermohonanFI(){}
    //private DaftarPermohonanItem mItems; // ListView items list

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);

        mItems = new ArrayList<DaftarPermohonanItemFI>();
        Resources resources = getResources();
        //TODO : get data daftar permohonan di FasumITF (npm peminjam dan ruangan yang dipinjam) dan masukkan ke arraylist
        mItems.add(new DaftarPermohonanItemFI(("rafi.devandra"), ("2301")));
        mItems.add(new DaftarPermohonanItemFI(("gina"), ("Aula")));
        adapter = new DaftarPermohonanAdapterFI(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPermohonanFI.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}