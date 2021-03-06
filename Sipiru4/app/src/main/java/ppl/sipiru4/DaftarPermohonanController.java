package ppl.sipiru4;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ppl.sipiru4.adapter.DaftarPermohonanAdapter;
import ppl.sipiru4.model.DaftarPermohonanItem;
import ppl.sipiru4.model.DetailPermohonan;

public class DaftarPermohonanController extends Fragment {
    ListView lv;

    DaftarPermohonanAdapter adapter;
    private ArrayList<DaftarPermohonanItem> mItems;
    public DaftarPermohonanController(){}
    //private DaftarPermohonanItem mItems; // ListView items list



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daftar_permohonan_ui, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);


        mItems = new ArrayList<DaftarPermohonanItem>();
        Resources resources = getResources();
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
//        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world) ));
        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world), resources.getDrawable(R.drawable.status_pinjaman), resources.getDrawable(R.drawable.tolak_pinjaman) ));
        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world), resources.getDrawable(R.drawable.status_pinjaman), resources.getDrawable(R.drawable.tolak_pinjaman) ));
        adapter = new DaftarPermohonanAdapter(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        return rootView;
    }

    private void onListItemClick(ListView l, View v, int position, long id) {

        DaftarPermohonanItem item = mItems.get(position);

    }
    public void goToAttract(View v)
    {
        Intent intent = new Intent(getActivity(), DetailPermohonan.class);

    }
}