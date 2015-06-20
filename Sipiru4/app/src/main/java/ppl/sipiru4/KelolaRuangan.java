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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import java.util.ArrayList;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Ruangan;
import ppl.sipiru4.adapter.DaftarRuanganAdminAdapter;
import ppl.sipiru4.controller.RuanganController;

public class KelolaRuangan extends Fragment {
    ListView lv;
    DaftarRuanganAdminAdapter adapter;
    ArrayList<Ruangan> mItems;
    RuanganController ruanganController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_admin, container, false);
		// ketika membuka halaman ini, akan diambil semua informasi ruangan terlebih dulu
        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/ruangan/");

        lv = (ListView) rootView.findViewById(R.id.list);

        Button tambah = (Button) rootView.findViewById(R.id.btnAdd);
        tambah.setText(R.string.button_add_ruangan);
		// mengarahkan ke halaman tambah ruangan
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), TambahRuangan.class);
                startActivity(i);
            }
        });

        return rootView;
    }

    // kelas AsyncTask untuk mengakses URL
    private class TaskHelper extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Mendapatkan informasi...");
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
            if (hasil == null) {
                pDialog.dismiss();
                Toast.makeText(getActivity(), "gagal menghubungkan ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }

            mItems = new ArrayList<>();
            ruanganController = new RuanganController(hasil);

            for (int i = 0; i < ruanganController.getSize(); i++) {
                mItems.add(ruanganController.getRuangan(i));
            }

            adapter = new DaftarRuanganAdminAdapter(getActivity().getApplicationContext(), mItems);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(parent.getContext(), UpdateRuangan.class);
                    i.putExtra("ruangan", ruanganController.getRuangan(position));
                    startActivity(i);
                }
            });

            pDialog.dismiss();
        }
    }
}