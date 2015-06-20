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
import ppl.sipiru4.adapter.DaftarRejectedAdapter;
import ppl.sipiru4.controller.PeminjamanController;

public class DaftarRejected extends Fragment {
    ListView lv;
    DaftarRejectedAdapter adapter;
    ArrayList<Peminjaman> mItems;
    PeminjamanController peminjamanController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list, container, false);
		
		// mengakses webserver untuk mendapatkan daftar peminjaman yang ditolak
        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/showRejected/");

        lv = (ListView) rootView.findViewById(R.id.list);

        return rootView;
    }

    // kelas AsyncTask untuk mengakses URL
    private class TaskHelper extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Mendapatkan daftar yang ditolak...");
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
                Toast.makeText(getActivity(),"gagal menghubungkan server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            mItems  = new ArrayList<>();
            peminjamanController = new PeminjamanController(hasil);
            for (int i = 0 ; i < hasil.length(); i++) {
                mItems.add(peminjamanController.getPeminjaman(i));
            }

            adapter = new DaftarRejectedAdapter(getActivity().getApplicationContext(), mItems);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Intent i = new Intent(getActivity().getApplicationContext(), DetailRejected.class);
                    // mengoper informasi suatu peminjaman ke DetailRejected.class
                    i.putExtra("peminjaman", peminjamanController.getPeminjaman(position));
                    startActivity(i);
                }
            });
            pDialog.dismiss();
        }
    }
}