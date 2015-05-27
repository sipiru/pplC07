package ppl.sipiru4;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import java.util.ArrayList;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.ManajerAdmin;
import ppl.sipiru4.Entity.SessionManager;
import ppl.sipiru4.adapter.DaftarManagerAdminAdapter;
import ppl.sipiru4.controller.ManajerAdminController;

public class KelolaRole extends Fragment {
    ListView lv;
    DaftarManagerAdminAdapter adapter;
    ArrayList<ManajerAdmin> mItems;
    ManajerAdminController manajerAdminController;
    SessionManager session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_admin, container, false);
        new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/showAllManagerAdmin/");

        session = new SessionManager(getActivity().getApplicationContext());
        lv = (ListView) rootView.findViewById(R.id.list);

        Button tambah = (Button) rootView.findViewById(R.id.btnAdd);
        tambah.setText(R.string.button_add_role);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), TambahRole.class);
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
            manajerAdminController = new ManajerAdminController(hasil);

            for (int i = 0; i < manajerAdminController.getSize(); i++) {
                mItems.add(manajerAdminController.getManajerAdmin(i));
            }

            adapter = new DaftarManagerAdminAdapter(getActivity().getApplicationContext(), mItems, manajerAdminController.getCountManajerRuangan(),
                    manajerAdminController.getCountManajerKemahasiswaan(), manajerAdminController.getCountManajerUmum(), manajerAdminController.getCountManajerItf(),
                    manajerAdminController.getCountAdmin());
            lv.setAdapter(adapter);

            pDialog.dismiss();
        }
    }
}