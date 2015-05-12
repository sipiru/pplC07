package ppl.sipiru4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import ppl.sipiru4.Entity.JSONParser;

public class TambahRuangan extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.tambah_ruangan, container, false);

        final EditText kodeRuangan = (EditText) rootView.findViewById(R.id.kodeRuangan);
        final EditText namaRuangan = (EditText) rootView.findViewById(R.id.namaRuangan);
        final EditText kapasitas = (EditText) rootView.findViewById(R.id.kapasitas);
        final EditText deskripsi = (EditText) rootView.findViewById(R.id.deskripsi);

        Button add = (Button) rootView.findViewById(R.id.buttonAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kodeRuangan.getText().toString().trim().length()==0 || kapasitas.getText().toString().trim().length()==0) {
                    Toast.makeText(getActivity(), "Kode ruangan dan kapasitas harus diisi", Toast.LENGTH_SHORT).show();
                }
                else if (kodeRuangan.getText().toString().length() > 5) {
                    Toast.makeText(getActivity(), "Kode ruangan maksimal 5 karakter", Toast.LENGTH_SHORT).show();
                }
                else {
                    String kode = kodeRuangan.getText().toString().trim();
                    String nama = namaRuangan.getText().toString().replaceAll(" ","%20");
                    String kap = kapasitas.getText().toString().trim();
                    String desk = deskripsi.getText().toString().replaceAll(" ","%20");

                    new SubmitHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/menambahRuangan/"
                            + kode + "&" + nama + "&" + kap + "&" + desk);
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
            pDialog.setMessage("Menambah ruangan ke database...");
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
                Toast.makeText(getActivity(), "Ruangan berhasil ditambah", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(), "Error.", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }

}
