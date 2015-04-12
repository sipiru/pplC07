package ppl.sipiru4.Business;

import com.grupc07.sipiru.Constants;
import com.grupc07.sipiru.Entities.ConnectionErrorException;
import com.grupc07.sipiru.Entities.DaftarRuangan;
import com.grupc07.sipiru.Entities.JSONHelper;
import com.grupc07.sipiru.Entities.ParseErrorException;
import com.grupc07.sipiru.Entities.Ruangan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by User on 12/04/2015.
 */
public class RuanganController {

    private RuanganController() {}

    private static DaftarRuangan daftarRuangan = null;

    public static void retrieveDaftarRuangan() throws ConnectionErrorException, JSONException {
        JSONArray jsonArray = null;
        try {
            jsonArray = JSONHelper.getArrayFromUrl(Constants.RUANG_ADDR);
        } catch (IOException|JSONException e) {
            throw new ConnectionErrorException();
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            daftarRuangan.tambahRuangan(new Ruangan(jsonObject.getString("kode"),
                    jsonObject.getString("nama"),
                    jsonObject.getInt("kapasitas"),
                    jsonObject.getString("deskripsi")));
        }
    }

    public static boolean tambahRuangan (Ruangan ruangan) throws ConnectionErrorException {
        if (daftarRuangan == null)
            throw new ConnectionErrorException();
        String strRuangan = "{";
        strRuangan += "\"kode:\"" + ruangan.getKode() + '&' + "\"nama:\"" + ruangan.getNama() + '&' +
                "\"kapasitas:\"" + ruangan.getKapasitas() + '&' +
                "\"deskripsi:\"" + ruangan.getDeskripsi();
        strRuangan += '}';

        try {
            JSONHelper.post(Constants.RUANG_ADDR, strRuangan);
        } catch (IOException e) {
            throw new ParseErrorException();
        }

        daftarRuangan.tambahRuangan(ruangan);
        return true;
    }

    public static Ruangan cariRuangan(String kode) {
        return daftarRuangan.getRuanganByKode(kode);
    }
}
