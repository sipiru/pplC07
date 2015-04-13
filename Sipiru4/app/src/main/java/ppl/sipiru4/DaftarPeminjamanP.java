package ppl.sipiru4;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ppl.sipiru4.adapter.DaftarPeminjamanAdapter;
import ppl.sipiru4.model.DaftarPeminjamanItem;

public class DaftarPeminjamanP extends Fragment {
    ListView lv;

    DaftarPeminjamanAdapter adapter;
    private ArrayList<DaftarPeminjamanItem> mItems;
    public DaftarPeminjamanP(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);


        mItems = new ArrayList<DaftarPeminjamanItem>();
        Resources resources = getResources();
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
        mItems.add(new DaftarPeminjamanItem(getString(R.string.hello_world)));
        mItems.add(new DaftarPeminjamanItem(getString(R.string.hello_world)));
        adapter = new DaftarPeminjamanAdapter(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPeminjamanP.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }

    private void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
    }
}