package ppl.sipiru4;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import java.io.IOException;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.controller.RuanganController;

public class UpdateHapusRuangan extends Fragment {
    ArrayAdapter<String> adapter;
    String[] ruangan;
    RuanganController ruanganController;
    Spinner spinner;
    ImageButton search;
    int posisi;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.update_ruangan, container, false);

        final TextView kodeRuangan = (TextView) rootView.findViewById(R.id.kodeRuangan);
        final EditText namaRuangan = (EditText) rootView.findViewById(R.id.namaRuangan);
        final EditText kapasitas = (EditText) rootView.findViewById(R.id.kapasitas);
        final EditText deskripsi = (EditText) rootView.findViewById(R.id.deskripsi);

        spinner = (Spinner)rootView.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) parent.getChildAt(0)).setTextSize(24);
                        posisi = spinner.getSelectedItemPosition();
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

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kodeRuangan.setText(ruanganController.getRuangan(posisi).getKode()+"");
                namaRuangan.setText(ruanganController.getRuangan(posisi).getNama()+"");
                kapasitas.setText(ruanganController.getRuangan(posisi).getKapasitas()+"");
                deskripsi.setText(ruanganController.getRuangan(posisi).getDeskripsi()+"");
            }
        });

        // mengakses URL menggunakan AsyncTask class
        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/ruangan/");

        Button save = (Button) rootView.findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kodeRuangan.getText().toString().trim().length()==0) {
                    Toast.makeText(getActivity(), "klik search terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else if (kapasitas.getText().toString().trim().length()==0) {
                    Toast.makeText(getActivity(), "kapasitas harus diisi", Toast.LENGTH_SHORT).show();
                }
                else {
                    String kode = kodeRuangan.getText().toString();
                    String nama = namaRuangan.getText().toString().replaceAll(" ","%20");
                    String kap = kapasitas.getText().toString().trim();
                    String desk = deskripsi.getText().toString().replaceAll(" ","%20");

                    if (kap.length() > 4) {
                        Toast.makeText(getActivity(), "kapasitas terlalu besar.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        new SubmitHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/updateRuangan/"
                                + kode + "&" + nama + "&" + kap + "&" + desk);
                    }
                }
            }
        });

        Button hapus = (Button) rootView.findViewById(R.id.buttonHapus);
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kodeRuangan.getText().toString().trim().length()==0) {
                    Toast.makeText(getActivity(), "klik search terlebih dulu", Toast.LENGTH_SHORT).show();
                }
                else if (kapasitas.getText().toString().trim().length()==0) {
                    Toast.makeText(getActivity(), "kapasitas harus diisi", Toast.LENGTH_SHORT).show();
                }
                else {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    // set title
                    alertDialogBuilder.setTitle("Apakah anda yakin untuk meneruskan permohonan ini?");
                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Tekan Ya untuk konfirmasi penghapusan")
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    String kode = kodeRuangan.getText().toString();
                                    new RemoveHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/hapusRuangan/"
                                            + kode);
                                }
                            })
                            .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });

        return rootView;
    }

    // kelas AsyncTask untuk mengakses URL
    private class SubmitHelper extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Meng-update ruangan...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                return JSONParser.getNotifFromURL(args[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String data) {
            if (data==null) {
                pDialog.dismiss();
                Toast.makeText(getActivity(),"gagal terhubung ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (data.trim().equals("\"sukses\"")){
                Toast.makeText(getActivity(), "Ruangan berhasil diupdate", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),MainActivityA.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("navPosition",0);
                startActivity(i);
            }
            else {
                Toast.makeText(getActivity(), "Error.", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
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
            int sizeRuangan = hasil.length();
            ruangan = new String[sizeRuangan];
            ruanganController = new RuanganController(hasil);
            // memasukkan nama ruangan ke ArrayList Ruangan
            for (int i = 0; i < sizeRuangan; i++) {
                ruangan[i] = ruanganController.getRuangan(i).getNama();
            }

            adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,ruangan);
            spinner.setAdapter(adapter);
            pDialog.dismiss();
        }
    }

    // kelas AsyncTask untuk mengakses URL
    private class RemoveHelper extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Menghapus ruangan dari database...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                return JSONParser.getNotifFromURL(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String hasil) {
            if (hasil == null) {
                pDialog.dismiss();
                Toast.makeText(getActivity(),"gagal terhubung ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (hasil.trim().equals("\"sukses\"")){
                Toast.makeText(getActivity(), "Ruangan berhasil dihapus", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),MainActivityA.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("navPosition",0);
                startActivity(i);
            }
            else {
                Toast.makeText(getActivity(), "Error.", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }
}
