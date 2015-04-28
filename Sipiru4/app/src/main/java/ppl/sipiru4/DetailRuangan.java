package ppl.sipiru4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.Ruangan;
import ppl.sipiru4.Entity.User;
import ppl.sipiru4.adapter.JamTersediaAdapter;
import ppl.sipiru4.model.JamTersediaItem;

public class DetailRuangan extends Fragment {
    ListView lv;
    JamTersediaAdapter adapter;
    Ruangan ruangan;

    public DetailRuangan(){}

    public DetailRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_ruangan, container, false);

        TextView namaRuangan = (TextView) rootView.findViewById(R.id.namaRuangan);
        namaRuangan.setText(ruangan.getNama());

        final TextView kodeRuangan = (TextView) rootView.findViewById(R.id.kodeRuangan);
        kodeRuangan.setText(ruangan.getKode());

        TextView kapasitas = (TextView) rootView.findViewById(R.id.kapasitas);
        kapasitas.setText(""+ruangan.getKapasitas());

        TextView deskripsi = (TextView) rootView.findViewById(R.id.deskripsi);
        deskripsi.setText(ruangan.getDeskripsi());

        lv = (ListView) rootView.findViewById(R.id.listJam);
        final ArrayList<JamTersediaItem> mItems = new ArrayList<>();

        JSONArray jArray = JSONParser.getJSONfromURL("http://ppl-c07.cs.ui.ac.id/connect/jadwalRuangan/"+namaRuangan.getText());
        for (int i = 0 ; i < jArray.length(); i++) {
            JSONObject jJadwal;
            try {
                jJadwal = jArray.getJSONObject(i);
                mItems.add(new JamTersediaItem(jJadwal.getString("waktu_awal_pinjam"),jJadwal.getString("waktu_akhir_pinjam")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        adapter = new JamTersediaAdapter(getActivity(), mItems);
        lv.setAdapter(adapter);

        Button pinjam = (Button) rootView.findViewById(R.id.buttonPinjam);
        pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new User(getActivity(),kodeRuangan.getText().toString());
                Intent i = new Intent(getActivity(), FormPeminjaman.class);
                Toast.makeText(getActivity(),"form peminjaman",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        return rootView;
    }
}


