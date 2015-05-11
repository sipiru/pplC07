package ppl.sipiru4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
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
    ArrayList<Peminjaman> mItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_permohonan, container, false);
        lv = (ListView) rootView.findViewById(R.id.listPermohonan);
        Log.e("Daftar Permohonan P", "createView fragment");

        Bundle b;
        b = getArguments();
        if (b!=null) {
            user = b.getParcelable("user");
            Log.e("user daftar permohonan",user.getUsername() + " " + user.getNama() + " " + user.getRole());
        }

        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/daftarPendingPeminjam/"+ user.getUsername());

        return rootView;
    }

    // kelas AsyncTask untuk mengakses URL
    private class TaskHelper extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Mendapatkan daftar pending...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... args) {
            try {
                return JSONParser.getJSONfromURL(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONArray hasil) {
            if (hasil == null){
                pDialog.dismiss();
                Toast.makeText(getActivity(), "gagal menghubungkan ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            mItems = new ArrayList<>();
            for (int i = 0 ; i < hasil.length(); i++) {
                try {
                    JSONObject jPeminjaman = hasil.getJSONObject(i);
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
                    int status = jPeminjaman.getInt("status");

                    mItems.add(new Peminjaman(id,kodeRuangan,usernameP,namaP,statusPeminjam,mulai,selesai,perihal,kegiatan,peralatan,status));
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
                }
            });
            pDialog.dismiss();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Daftar Permohonan P", "create fragment");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e("Daftar Permohonan P", "attach fragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Daftar Permohonan P", "destroy fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Daftar Permohonan P", "destroyView fragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Daftar Permohonan P", "onDetach fragment");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Daftar Permohonan P", "pause fragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Daftar Permohonan P", "resume fragment");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Daftar Permohonan P", "start fragment");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Daftar Permohonan P", "stop fragment");
    }
}