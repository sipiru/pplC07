package ppl.sipiru4;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.Entity.User;
import ppl.sipiru4.adapter.DaftarPermohonanAdapterP;

public class DaftarPermohonanP extends Fragment {
    ListView lv;
    DaftarPermohonanAdapterP adapter;
    User user;

    public DaftarPermohonanP(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Bundle b;
        b = getArguments();
        if (b!=null) {
            user = b.getParcelable("user");
            Log.e("user daftar permohonan",user.getUsername() + " " + user.getNama() + " " + user.getRole());
        }

        lv = (ListView) rootView.findViewById(R.id.list);

        final ArrayList<Peminjaman> mItems = new ArrayList<>();

        JSONArray jArray = JSONParser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/daftarPendingPeminjam/"
                + user.getUsername());
        for (int i = 0 ; i < jArray.length(); i++) {
            try {
                JSONObject jPeminjaman = jArray.getJSONObject(i);
                assert jPeminjaman != null;
                int id = jPeminjaman.getInt("id");
                String kodeRuangan = jPeminjaman.getString("kode_ruangan");
                String namaP = jPeminjaman.getString("nama_peminjam");
                String usernameP = jPeminjaman.getString("username_peminjam");
                boolean statusPeminjam = jPeminjaman.getBoolean("status_peminjam");
                String perihal = jPeminjaman.getString("perihal");
                String kegiatan = jPeminjaman.getString("kegiatan");
                String mulai = jPeminjaman.getString("waktu_awal_pinjam");
                String selesai = jPeminjaman.getString("waktu_akhir_pinjam");
                String peralatan = jPeminjaman.getString("peralatan");
                String status = "" + jPeminjaman.getInt("status");

                mItems.add(new Peminjaman(id,kodeRuangan,usernameP,namaP,statusPeminjam,mulai,selesai,perihal,kegiatan,peralatan,status, getResources().getDrawable(R.drawable.kotak)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        adapter = new DaftarPermohonanAdapterP(getActivity().getApplicationContext(), mItems);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity().getApplicationContext(), DetailPermohonanP.class);
                // passing array index
                i.putExtra("peminjaman", mItems.get(position));
                startActivity(i);
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.frame_container, new DetailPermohonanP(mItems.get(position)));
////                Toast.makeText(getActivity(), "detail peminjaman", Toast.LENGTH_SHORT).show();
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });
        return rootView;
    }
}