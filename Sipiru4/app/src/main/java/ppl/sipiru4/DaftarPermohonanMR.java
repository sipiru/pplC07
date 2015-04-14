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

import ppl.sipiru4.adapter.DaftarPermohonanAdapter;
import ppl.sipiru4.model.DaftarPermohonanItem;

public class DaftarPermohonanMR extends Fragment {
    ListView lv;

    DaftarPermohonanAdapter adapter;
    private ArrayList<DaftarPermohonanItem> mItems;
    public DaftarPermohonanMR(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);


        mItems = new ArrayList<DaftarPermohonanItem>();
        Resources resources = getResources();
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world), resources.getDrawable(R.drawable.status_pinjaman), resources.getDrawable(R.drawable.tolak_pinjaman) ));
        mItems.add(new DaftarPermohonanItem("jkjk", resources.getDrawable(R.drawable.status_pinjaman), resources.getDrawable(R.drawable.tolak_pinjaman) ));
        adapter = new DaftarPermohonanAdapter(getActivity().getApplicationContext(),mItems);
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