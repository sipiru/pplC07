package ppl.sipiru4.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ppl.sipiru4.Entity.Manajer;

public class ManajerController {
    private Manajer[] manajers;
    private int size;

    public ManajerController(JSONArray jArray) {
        manajers = new Manajer[jArray.length()];
        for (int i = 0 ; i < jArray.length() ; i++) {
            try {
                JSONObject jsonObject = jArray.getJSONObject(i);
                String username = jsonObject.getString("username");
                String nama = jsonObject.getString("nama");
                String role = jsonObject.getString("role");
                String no_hp = jsonObject.getString("no_hp");

                manajers[i] = new Manajer(username,nama, role, no_hp);
                size++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Manajer getManajer(int i) {
        return manajers[i];
    }

    public int getSize() {
        return size;
    }
}
