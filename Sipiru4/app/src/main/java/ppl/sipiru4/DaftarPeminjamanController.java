/*
package ppl.sipiru4;

import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ppl.sipiru4.adapter.DaftarPermohonanAdapter;
import ppl.sipiru4.adapter.DaftarPesanDemoAdapter;
import ppl.sipiru4.model.DaftarPermohonanItem;

public class DaftarPermohonan extends ListFragment  {

    private List<DaftarPermohonanItem> mItems; // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the items list
        mItems = new ArrayList<DaftarPermohonanItem>();
        Resources resources = getResources();
        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world), resources.getDrawable(R.drawable.ic), resources.getDrawable(R.drawable.ic) ));
        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world), resources.getDrawable(R.drawable.ic), resources.getDrawable(R.drawable.ic) ));

        //mItems.add(new DaftarPesanItem(resources.getDrawable(R.drawable.youtube), getString(R.string.youtube), getString(R.string.youtube_description)));
        // initialize and set the list adapter
        setListAdapter(new DaftarPermohonanAdapter(getActivity(), mItems));
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }
*/
/*    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.detail_pesan, container, false);

        return rootView;
    }*//*


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        DaftarPermohonanItem item = mItems.get(position);



        //Toast.makeText(getActivity(), item.title, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity().getApplicationContext(), SingleListItem.class);
        startActivity((Intent) intent);

    }
*/
/*    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;
        fragment = new SingleListItem();
    }*//*

}



//adapternya bikin dikelas lain aja abis itu dipanggil disini*/
package ppl.sipiru4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DaftarPeminjamanController extends Fragment {

    public DaftarPeminjamanController(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.daftar_permohonan_ui, container, false);

        return rootView;
    }
}
