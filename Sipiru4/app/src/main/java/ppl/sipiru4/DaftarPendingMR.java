package ppl.sipiru4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import ppl.sipiru4.Entity.SessionManager;
import ppl.sipiru4.adapter.DaftarPermohonanAdapterMR;
import ppl.sipiru4.controller.PeminjamanController;

public class DaftarPendingMR extends Fragment {
    ListView lv;
    DaftarPermohonanAdapterMR adapter;
    ArrayList<Peminjaman> mItems;
    PeminjamanController peminjamanController;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
		
		// mengakses webserver menggunakan AsyncTask untuk mendapatkan semua daftar peminjaman manajer ruangan yang belum disetujui
        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/displayManajerRuangan/");

        lv = (ListView) rootView.findViewById(R.id.list);
        session = new SessionManager(getActivity().getApplicationContext());

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
                Toast.makeText(getActivity(), "gagal menghubungkan ke server. coba lagi.",Toast.LENGTH_SHORT).show();
                return;
            }
            mItems = new ArrayList<>();
            peminjamanController = new PeminjamanController(hasil);

            for (int i = 0; i < hasil.length(); i++) {
                mItems.add(peminjamanController.getPeminjaman(i));
            }

            adapter = new DaftarPermohonanAdapterMR(getActivity().getApplicationContext(),mItems);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent i = new Intent(getActivity().getApplicationContext(), DetailPendingMR.class);
                    // mengoper informasi peminjaman tertentu ke DetailPendingMR.class
                    i.putExtra("peminjaman", peminjamanController.getPeminjaman(position));
                    startActivity(i);
                }
            });
            pDialog.dismiss();
        }
    }
}