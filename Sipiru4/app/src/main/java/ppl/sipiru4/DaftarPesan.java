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
import java.util.List;

import ppl.sipiru4.adapter.DaftarPermohonanAdapter;
import ppl.sipiru4.adapter.DaftarPesanDemoAdapter;
import ppl.sipiru4.model.DaftarPermohonanItem;
import ppl.sipiru4.model.DaftarPesanItem;

public class DaftarPesan extends Fragment {
    DaftarPesanDemoAdapter adapter;
    ListView lv;
    private ArrayList<DaftarPesanItem> mItems;

    public DaftarPesan(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daftar_pesan_ui, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);


        mItems = new ArrayList<DaftarPesanItem>();
        Resources resources = getResources();
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
        mItems.add(new DaftarPesanItem(getString(R.string.hello_world) , getString(R.string.hello_world), resources.getDrawable(R.drawable.abc_ic_clear_mtrl_alpha)));
        mItems.add(new DaftarPesanItem(getString(R.string.hello_world) , getString(R.string.hello_world),resources.getDrawable(R.drawable.abc_ic_clear_mtrl_alpha)));
        adapter = new DaftarPesanDemoAdapter(getActivity().getApplicationContext(),mItems);
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
/*    private List<DaftarPesanItem> mItems; // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the items list
        mItems = new ArrayList<DaftarPesanItem>();
        Resources resources = getResources();
        mItems.add(new DaftarPesanItem(resources.getDrawable(R.drawable.ic), getString(R.string.hello_world), getString(R.string.hello_world)));
        mItems.add(new DaftarPesanItem(resources.getDrawable(R.drawable.ic), getString(R.string.hello_world), getString(R.string.hello_world)));

        //mItems.add(new DaftarPesanItem(resources.getDrawable(R.drawable.youtube), getString(R.string.youtube), getString(R.string.youtube_description)));
        // initialize and set the list adapter
        setListAdapter(new DaftarPesanDemoAdapter(getActivity(), mItems));
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //retrieve theListView item
        DaftarPesanItem item = mItems.get(position);
        Intent myIntent = new Intent(v.getContext(), DetailPesanController.class);
        String detilPesan = "detail pesan";
        startActivityForResult(myIntent, 0);

    }*/
}
