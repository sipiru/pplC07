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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.adapter.JamTersediaAdapter;
import ppl.sipiru4.controller.RuanganController;
import ppl.sipiru4.model.JamTersediaItem;

public class CariRuanganRuang extends Fragment {
    ListView lView;
    JamTersediaAdapter adapterList;
    ArrayAdapter<String> adapter;
    RuanganController ruanganController;
    String[] namaRuangan;
    String[] kodeRuangan;
    Spinner spinner;
    ImageButton search;
    int posisi;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cari_ruangan_ruang_ui, container, false);

        // mengakses URL menggunakan AsyncTask class
        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/ruangan/");

        final TextView namaR = (TextView) rootView.findViewById(R.id.namaRuangan);
        lView = (ListView)rootView.findViewById(R.id.listView);
        spinner = (Spinner)rootView.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextSize(25);
                        posisi = spinner.getSelectedItemPosition();
                        namaR.setText(namaRuangan[posisi]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );

        search = (ImageButton) rootView.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // mengakses URL menggunakan AsyncTask class
                new getJadwalHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/jadwalRuangan/"
                        + kodeRuangan[posisi]);
            }
        });

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
            pDialog.setMessage("Mendapatkan semua informasi ruangan...");
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
            ruanganController = new RuanganController(hasil);
            int sizeRuangan = ruanganController.getSize();

            namaRuangan = new String[sizeRuangan];
            kodeRuangan = new String[sizeRuangan];
            // memasukkan nama ruangan ke ArrayList Ruangan
            for (int i = 0; i < sizeRuangan; i++) {
                namaRuangan[i] = ruanganController.getRuangan(i).getNama();
                kodeRuangan[i] = ruanganController.getRuangan(i).getKode();
            }

            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, namaRuangan);
            spinner.setAdapter(adapter);
            pDialog.dismiss();
        }
    }

    // kelas AsyncTask untuk mengakses URL
    private class getJadwalHelper extends AsyncTask<String, String, JSONArray> {
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

            final ArrayList<JamTersediaItem> waktuRuangan = new ArrayList<>();
            for (int i = 0 ; i < hasil.length(); i++) {
                JSONObject jJadwal;
                try {
                    jJadwal = hasil.getJSONObject(i);

                    String[] input1 = jJadwal.getString("waktu_awal_pinjam").split(" ");
                    String[] format1 = input1[0].split("-");
                    String[] input2 = jJadwal.getString("waktu_akhir_pinjam").split(" ");
                    String[] format2 = input2[0].split("-");
                    String date1 = format1[2]+"-"+format1[1] + "-" + format1[0] + " " + input1[1];
                    String date2 = format2[2]+"-"+format2[1] + "-" + format2[0] + " " + input2[1];
                    String dateView1 = null;
                    String dateView2 = null;
                    try {
                        Date init1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date1);
                        dateView1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(init1);
                        Date init2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date2);
                        dateView2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(init2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    waktuRuangan.add(new JamTersediaItem(dateView1,dateView2));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            adapterList = new JamTersediaAdapter(getActivity(),waktuRuangan);
            lView.setAdapter(adapterList);
            if (waktuRuangan.size()==0) {
                Toast.makeText(getActivity(), "Tidak ada jadwal untuk ruangan " + namaRuangan[posisi], Toast.LENGTH_SHORT).show();
            }

            pDialog.dismiss();
        }
    }
}