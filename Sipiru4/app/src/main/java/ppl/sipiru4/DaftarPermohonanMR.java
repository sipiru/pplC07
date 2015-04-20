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

import ppl.sipiru4.adapter.DaftarPermohonanAdapterMR;
import ppl.sipiru4.model.DaftarPermohonanItemMR;

public class DaftarPermohonanMR extends Fragment {
    ListView lv;
    DaftarPermohonanAdapterMR adapter;
    private ArrayList<DaftarPermohonanItemMR> mItems;
    //private DaftarPermohonanItem mItems; // ListView items list

    public DaftarPermohonanMR(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);

        mItems = new ArrayList<DaftarPermohonanItemMR>();
        Resources resources = getResources();
        //TODO get data permohonan di manager ruangan
        mItems.add(new DaftarPermohonanItemMR(("rauhil"),("2303")));
        mItems.add(new DaftarPermohonanItemMR(("gina"),("2302")));
        adapter = new DaftarPermohonanAdapterMR(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPermohonanMR.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}