package ppl.sipiru4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ppl.sipiru4.adapter.UbahStatusPeminjamanAdapter;
import ppl.sipiru4.model.UbahStatusPermohonanItem;

public class UbahStatusPeminjaman extends Fragment  {

    public UbahStatusPeminjaman(){}
    private ArrayList<UbahStatusPermohonanItem> mItems;
    UbahStatusPeminjamanAdapter adapter;
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ubah_status_peminjaman, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);

        mItems = new ArrayList<UbahStatusPermohonanItem>();
        //Resources resources = getResources();
        mItems.add(new UbahStatusPermohonanItem(getString(R.string.hello_world), (Button)rootView.findViewById(R.id.selesai)) );
        mItems.add(new UbahStatusPermohonanItem(getString(R.string.hello_world), (Button)rootView.findViewById(R.id.selesai)) );

        // initialize and set the list adapter
        adapter = new UbahStatusPeminjamanAdapter(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        return rootView;
    }
   // ListView items list




    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
/*        UbahStatusPermohonanItem item = mItems.get(position);


        Intent intent = new Intent(getActivity().getApplicationContext(), DetailPesanController.class);
        startActivity((Intent) intent);*/

    }

}

