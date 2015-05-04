package ppl.sipiru4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.adapter.DaftarPeminjamanAdapterK;

public class DaftarPeminjamanK extends Fragment {
    ListView lv;
    DaftarPeminjamanAdapterK adapter;
    ArrayList<Peminjaman> mItems = new ArrayList<>();

    public DaftarPeminjamanK(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);

        adapter = new DaftarPeminjamanAdapterK(getActivity().getApplicationContext(), mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPeminjamanK.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}