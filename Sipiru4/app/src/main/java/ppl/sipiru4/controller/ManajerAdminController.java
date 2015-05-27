package ppl.sipiru4.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ppl.sipiru4.Entity.ManajerAdmin;


public class ManajerAdminController {
    private ManajerAdmin[] manajerAdmins;
    private int countManajerRuangan;
    private int countManajerKemahasiswaan;
    private int countManajerUmum;
    private int countManajerItf;
    private int countAdmin;
    private int size;

    public ManajerAdminController(JSONArray jArray) {
        manajerAdmins = new ManajerAdmin[jArray.length()];
        for (int i = 0 ; i < jArray.length() ; i++) {
            try {
                JSONObject jsonObject = jArray.getJSONObject(i);
                String username = jsonObject.getString("username");
                String nama = jsonObject.getString("nama");
                String role = jsonObject.getString("role");

                switch (role) {
                    case "manajer ruangan":
                        countManajerRuangan++;
                        break;
                    case "manajer kemahasiswaan":
                        countManajerKemahasiswaan++;
                        break;
                    case "manajer umum":
                        countManajerUmum++;
                        break;
                    case "manajer itf":
                        countManajerItf++;
                        break;
                    case "admin":
                        countAdmin++;
                        break;
                    default:
                        break;
                }

                manajerAdmins[i] = new ManajerAdmin(username,nama, role);
                size++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ManajerAdmin getManajerAdmin(int i) {
        return manajerAdmins[i];
    }

    public int getCountManajerRuangan() {
        return countManajerRuangan;
    }
    public int getCountManajerKemahasiswaan() {
        return countManajerKemahasiswaan;
    }
    public int getCountManajerUmum() {
        return countManajerUmum;
    }
    public int getCountManajerItf() {
        return countManajerItf;
    }
    public int getCountAdmin() {
        return countAdmin;
    }

    public int getSize() {
        return size;
    }
}
