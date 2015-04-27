package ppl.sipiru4;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ppl.sipiru4.adapter.DaftarDisetujuiAdapterP;
import ppl.sipiru4.model.DaftarDisetujuiItemP;

public class DaftarDisetujuiP extends Fragment {
    ListView lv;

    DaftarDisetujuiAdapterP adapter;
    private ArrayList<DaftarDisetujuiItemP> mItems;

    public DaftarDisetujuiP(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarDisetujuiItemP>();
        Resources resources = getResources();
        //TODO : get data nama ruangan yang dipinjam dan status peminjaman seorang peminjam, masukkin array mItems
        mItems.add(new DaftarDisetujuiItemP(("2304"), ("status peminjaman sudah selesai")));
        mItems.add(new DaftarDisetujuiItemP(("2303"), ("status peminjaman sedang berlangsung")));
        adapter = new DaftarDisetujuiAdapterP(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailDisetujuiP.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        return rootView;
    }
}
