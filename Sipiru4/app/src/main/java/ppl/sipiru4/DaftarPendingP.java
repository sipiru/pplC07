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
import java.util.ArrayList;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.Entity.User;
import ppl.sipiru4.adapter.DaftarPermohonanAdapterP;
import ppl.sipiru4.controller.PeminjamanController;
import ppl.sipiru4.controller.PenggunaController;

public class DaftarPendingP extends Fragment {
    ListView lv;
    DaftarPermohonanAdapterP adapter;
    ArrayList<Peminjaman> mItems;
    PenggunaController penggunaController;
    PeminjamanController peminjamanController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
        lv = (ListView) rootView.findViewById(R.id.list);
        Log.e("Daftar Permohonan P", "createView fragment");

        Bundle b;
        b = getArguments();
        if (b!=null) {
            User user = b.getParcelable("user");
            penggunaController = new PenggunaController(user);

            Log.e("user daftar permohonan",user.getUsername() + " " + user.getNama() + " " + user.getRole());
        }

        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/daftarPendingPeminjam/" + penggunaController.getCurrentPengguna().getUsername());

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
            peminjamanController = new PeminjamanController(hasil);

            for (int i = 0 ; i < hasil.length(); i++) {
                mItems.add(peminjamanController.getPeminjaman(i));
            }

            adapter = new DaftarPermohonanAdapterP(getActivity().getApplicationContext(), mItems);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    // Sending image id to FullScreenActivity
                    Intent i = new Intent(getActivity().getApplicationContext(), DetailPendingP.class);
                    // passing array index
                    i.putExtra("peminjaman", peminjamanController.getPeminjaman(position));
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