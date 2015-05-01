package ppl.sipiru4;

import android.os.Bundle;
import android.os.StrictMode;
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
        final TextView namaRuangan = (TextView) rootView.findViewById(R.id.namaRuangan);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, ruangan);
        lView = (ListView)rootView.findViewById(R.id.listView);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final JSONArray jArray = JSONParser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/ruangan/");
        for (int i = 0; i < jArray.length(); i++) {
            try {
                ruangan.add(jArray.getJSONObject(i).getString("nama"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final ArrayList<JamTersediaItem> waktuRuangan = new ArrayList<>();

                int sid = spinner.getSelectedItemPosition();
                namaRuangan.setText(ruangan.get(sid));

                JSONArray jArray2 = null;
                try {
                    jArray2 = JSONParser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/jadwalRuangan/" + jArray.getJSONObject(sid).getString("kode"));
                    Toast.makeText(getActivity(),"terpilih "+jArray.getJSONObject(sid).getString("kode"),Toast.LENGTH_SHORT).show();
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

//    @Override
//    public void onAttach(Activity activity)
//    {
//        super.onAttach(activity);
//    }
//
//    @Override
//    public void onStart()
//    {
//        super.onStart();
//    }
//
//    @Override
//    public void onResume()
//    {
//        super.onResume();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Log.d("MyTag", "TabFragment0--onDestroyView");
//    }
//    @Override
//    public void onViewStateRestored(Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        Log.d("MyTag", "TabFragment0--onViewStateRestored");
//    }
}
