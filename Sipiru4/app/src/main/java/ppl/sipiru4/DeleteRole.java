package ppl.sipiru4;

import android.app.ProgressDialog;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import java.io.IOException;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.controller.ManajerController;

public class DeleteRole extends Fragment {
    ManajerController manajerController;
    String[] manajerString;
    int  posisi;
    Spinner spinner;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.delete_role, container, false);

        final TextView username = (TextView) rootView.findViewById(R.id.username);
        final TextView namaLengkap = (TextView) rootView.findViewById(R.id.namaPengguna);
        final TextView role = (TextView) rootView.findViewById(R.id.roles);
//        final TextView noHP = (EditText) rootView.findViewById(R.id.nohp);
        spinner = (Spinner)rootView.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        posisi = spinner.getSelectedItemPosition();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );

        ImageButton search = (ImageButton) rootView.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText(manajerController.getManajer(posisi).getUsername());
                namaLengkap.setText(manajerController.getManajer(posisi).getNama());
                role.setText(manajerController.getManajer(posisi).getRole());
            }
        });

        // mengakses URL menggunakan AsyncTask class
        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/showAllManager/");

        Button delete = (Button) rootView.findViewById(R.id.buttonHapus);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().trim().length()==0) {
                    Toast.makeText(getActivity(), "klik search terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    String uname = username.getText().toString();
                    new SubmitHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/deleteManager/" + uname);
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
            pDialog.setMessage("Menghapus role...");
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
                Toast.makeText(getActivity(), "Role berhasil dihapus", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),MainActivityA.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("navPosition",3);
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
            manajerString = new String[sizeRuangan];
            manajerController = new ManajerController(hasil);
            // memasukkan nama ruangan ke ArrayList Ruangan
            for (int i = 0; i < sizeRuangan; i++) {
                manajerString[i] = manajerController.getManajer(i).getNama();
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item,manajerString);
            spinner.setAdapter(adapter);
            pDialog.dismiss();
        }
    }
}
