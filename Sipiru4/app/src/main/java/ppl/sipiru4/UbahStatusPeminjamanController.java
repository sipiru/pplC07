package ppl.sipiru4;

import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ppl.sipiru4.adapter.UbahStatusPeminjamanAdapter;
import ppl.sipiru4.model.UbahStatusPermohonanItem;

public class UbahStatusPeminjamanController extends ListFragment  {

    public UbahStatusPeminjamanController(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ubah_status_peminjaman, container, false);

        return rootView;
    }
    private List<UbahStatusPermohonanItem> mItems; // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the items list
        mItems = new ArrayList<UbahStatusPermohonanItem>();
        Resources resources = getResources();
        mItems.add(new UbahStatusPermohonanItem(getString(R.string.hello_world), resources.getDrawable(R.drawable.status_pinjaman)) );
        mItems.add(new UbahStatusPermohonanItem(getString(R.string.hello_world), resources.getDrawable(R.drawable.status_pinjaman)) );

        // initialize and set the list adapter
        setListAdapter(new UbahStatusPeminjamanAdapter(getActivity(), mItems));
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
/*        UbahStatusPermohonanItem item = mItems.get(position);


        Intent intent = new Intent(getActivity().getApplicationContext(), DetailPesanController.class);
        startActivity((Intent) intent);*/

    }

}

