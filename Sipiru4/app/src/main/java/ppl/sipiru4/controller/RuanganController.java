package ppl.sipiru4.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ppl.sipiru4.Entity.Ruangan;

public class RuanganController {
    Ruangan[] ruangans;
    int size;

    public RuanganController(Ruangan r) {
        ruangans = new Ruangan[1];
        ruangans[0] = r;
    }

    public RuanganController(JSONArray jArray) {
        ruangans = new Ruangan[jArray.length()];
        for (int i = 0 ; i < jArray.length(); i++) {
            try {
                JSONObject jsonRuangan = jArray.getJSONObject(i);
                String kode = jsonRuangan.getString("kode");
                String nama = jsonRuangan.getString("nama");
                int kapasitas = jsonRuangan.getInt("kapasitas");
                String deskripsi = jsonRuangan.getString("deskripsi");

                ruangans[i] = new Ruangan(kode, nama, kapasitas, deskripsi);
                size++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Ruangan getRuangan(int i) {
        return ruangans[i];
    }

    public Ruangan getRuangan() {
        return ruangans[0];
    }

    public int getSize() {
        return size;
    }

}
