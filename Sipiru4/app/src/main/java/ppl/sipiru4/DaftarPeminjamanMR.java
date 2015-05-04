package ppl.sipiru4;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.adapter.DaftarPeminjamanAdapterMR;

public class DaftarPeminjamanMR extends Fragment {
    ListView lv;
    DaftarPeminjamanAdapterMR adapter;
    private static ArrayList<Peminjaman> mItems = new ArrayList<>();

    public DaftarPeminjamanMR(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        lv = (ListView) rootView.findViewById(R.id.listPermohonan);

        adapter = new DaftarPeminjamanAdapterMR(getActivity().getApplicationContext(),mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPeminjamanMR.class);
                // passing array index
                i.putExtra("peminjaman", mItems.get(position));
                startActivity(i);
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame_container, new DetailPeminjamanMR(mItems.get(position)));
//                Toast.makeText(getActivity(), "detail peminjaman", Toast.LENGTH_SHORT).show();
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });
        return rootView;
    }
}