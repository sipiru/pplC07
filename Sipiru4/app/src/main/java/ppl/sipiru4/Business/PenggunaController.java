package ppl.sipiru4.Business;

import ppl.sipiru4.Constants;
import ppl.sipiru4.Entities.ConnectionErrorException;
import ppl.sipiru4.Entities.JSONHelper;
import ppl.sipiru4.Entities.ParseErrorException;
import ppl.sipiru4.Entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by User on 12/04/2015.
 */
public class PenggunaController {

    private static User currentPengguna = null;

    private PenggunaController() {}

    public static void retrieveCurrentPengguna() throws ConnectionErrorException, ParseErrorException {
        JSONArray jsonArray = null;
        try {
            jsonArray = JSONHelper.getArrayFromUrl(Constants.ACTIVEUSER_ADDR);
        } catch (IOException|JSONException e) {
            throw new ConnectionErrorException();
        }

        if (jsonArray.length() > 1) throw new ParseErrorException();

        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonArray.get(0);
        } catch (JSONException e) {
            throw new ParseErrorException();
        }
        try {
            currentPengguna = new User(jsonObject.getString("username"),
                    jsonObject.getString("kodeidentitas"),jsonObject.getString("nama_role"));
        } catch (JSONException e) {
            throw new ParseErrorException();
        }
    }

    public static User getCurrentPengguna() {
        if (currentPengguna != null) {
            return currentPengguna;
        }
        return null;
    }
}