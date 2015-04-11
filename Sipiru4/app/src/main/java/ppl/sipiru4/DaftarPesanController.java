package ppl.sipiru4;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ppl.sipiru4.adapter.DaftarPesanDemoAdapter;
import ppl.sipiru4.model.DaftarPesanItem;

public class DaftarPesanController extends Fragment {

    public  DaftarPesanController(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.daftar_pesan_ui, container, false);

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
