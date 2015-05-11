package ppl.sipiru4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.adapter.JamTersediaAdapter;
import ppl.sipiru4.model.JamTersediaItem;

public class CariRuanganRuang extends Fragment {
    ListView lView;
    JamTersediaAdapter adapterList;

    public CariRuanganRuang() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cari_ruangan_ruang_ui, container, false);
        final Spinner spinner = (Spinner)rootView.findViewById(R.id.spinner);
        final ArrayList<String> ruangan = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ruangan);
        final TextView namaRuangan = (TextView) rootView.findViewById(R.id.namaRuangan);
        lView = (ListView)rootView.findViewById(R.id.listView);

        // mengakses URL menggunakan AsyncTask class
        JSONArray jArray = null;
        try {
            jArray = new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/ruangan/").get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // memasukkan nama ruangan ke ArrayList Ruangan
        assert jArray != null;
        for (int i = 0; i < jArray.length(); i++) {
            try {
                ruangan.add(jArray.getJSONObject(i).getString("nama"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinner.setAdapter(adapter);

        final JSONArray finalJArray = jArray;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final ArrayList<JamTersediaItem> waktuRuangan = new ArrayList<>();

                int sid = spinner.getSelectedItemPosition();
                namaRuangan.setText(ruangan.get(sid));

//                JSONArray jArray2 = null;
//                try {
//                    jArray2 = JSONParser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/jadwalRuangan/" + finalJArray.getJSONObject(sid).getString("kode"));
//                    Toast.makeText(getActivity(),"terpilih "+ finalJArray.getJSONObject(sid).getString("kode"),Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

                // mengakses URL menggunakan AsyncTask class
                AsyncTask<String, String, JSONArray> hasil;
                JSONArray jArray2 = null;
                try {
                    hasil = new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/jadwalRuangan/"
                            + finalJArray.getJSONObject(sid).getString("kode"));

                    // memasukkan content URL ke dalam variable bertipe JArray
                    try {
                        jArray2 = hasil.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                assert jArray2 != null;
                for (int i = 0 ; i < jArray2.length(); i++) {
                    JSONObject jJadwal;
                    try {
                        jJadwal = jArray2.getJSONObject(i);
                        waktuRuangan.add(new JamTersediaItem(jJadwal.getString("waktu_awal_pinjam"),jJadwal.getString("waktu_akhir_pinjam")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                adapterList = new JamTersediaAdapter(getActivity(),waktuRuangan);
                lView.setAdapter(adapterList);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        }
        );
        lView.setAdapter(adapterList);

        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    // kelas AsyncTask untuk mengakses URL
    private class TaskHelper extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Mendapatkan jadwal ruangan...");
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
                Toast.makeText(getActivity(),"gagal terhubung ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }

            pDialog.dismiss();
        }
    }
}