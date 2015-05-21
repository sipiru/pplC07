package ppl.sipiru4.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ppl.sipiru4.Entity.DaftarManajer;
import ppl.sipiru4.Entity.Manajer;
import ppl.sipiru4.Entity.Peminjaman;


public class ManajerController {
    private Manajer[] manajers;
    private int size;

    public ManajerController(JSONArray jArray) {
        manajers = new Manajer[jArray.length()];
        for (int i = 0 ; i < jArray.length() ; i++) {
            try {
                JSONObject jPeminjaman = jArray.getJSONObject(i);
                String username = jPeminjaman.getString("username");
                String nama = jPeminjaman.getString("nama");
                String role = jPeminjaman.getString("role");

                manajers[i] = new Manajer(username,nama, role);
                size++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Manajer getManajer(int i) {
        return manajers[i];
    }
}
