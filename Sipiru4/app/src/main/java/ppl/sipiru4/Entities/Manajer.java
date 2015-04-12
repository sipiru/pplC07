package ppl.sipiru4.Entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 12/04/2015.
 */
public class Manajer {
    private String username;
    private String tipe;

    public Manajer(String username, String tipe) {
        this.username = username;
        this.tipe = tipe;
    }

    public String getUsername() {
        return username;
    }

    public String getTipe() {
        return tipe;
    }
}
