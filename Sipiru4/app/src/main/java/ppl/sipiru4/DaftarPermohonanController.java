package ppl.sipiru4;

import android.support.v4.app.Fragment;
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

import ppl.sipiru4.adapter.DaftarPermohonanAdapter;
import ppl.sipiru4.model.DaftarPermohonanItem;

public class DaftarPermohonanController extends Fragment {

    public DaftarPermohonanController(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.daftar_permohonan_ui, container, false);

        return rootView;
    }
    /*private List<DaftarPermohonanItem> mItems; // ListView items list

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the items list
        mItems = new ArrayList<DaftarPermohonanItem>();
        Resources resources = getResources();
        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world), resources.getDrawable(R.drawable.status_pinjaman), resources.getDrawable(R.drawable.tolak_pinjaman) ));
        mItems.add(new DaftarPermohonanItem(getString(R.string.hello_world), resources.getDrawable(R.drawable.status_pinjaman), resources.getDrawable(R.drawable.tolak_pinjaman) ));

        setListAdapter(new DaftarPermohonanAdapter(getActivity(), mItems));
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
        DaftarPermohonanItem item = mItems.get(position);

        Intent intent = new Intent(getActivity().getApplicationContext(), DetailPesanController.class);
        startActivity((Intent) intent);

    }
*/
}