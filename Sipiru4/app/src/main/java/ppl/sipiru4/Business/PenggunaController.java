package ppl.sipiru4.Business;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import ppl.sipiru4.Constants;
import ppl.sipiru4.Entity.User;

/**
 * Created by User on 12/04/2015.
 */
public class PenggunaController {

    private static User currentPengguna = null;

    private PenggunaController() {}

    public static void login(JSONArray json) {
        // 0: username
        // 1: nama
        // 2: kode_org
        // 3: nama_role
        // 4: state
        // 5: kodeidentitas
        try {
            currentPengguna = new User(json.getString(0), json.getString(1), json.getString(2),
                    json.getString(3), json.getString(5));
        } catch (JSONException e) {
            Log.e(Constants.JSONTAG; "login pengguna error")
        }
    }

    public static User getCurrentPengguna() {
        if (currentPengguna != null) {
            return currentPengguna;
        }
        return null;
    }
}