package ppl.sipiru4.Business;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import ppl.sipiru4.Constants;
import ppl.sipiru4.Entity.DaftarPesan;
import ppl.sipiru4.Entity.Pesan;

/**
 * Created by User on 28/04/2015.
 */
public class PesanController {

    private static DaftarPesan daftarPesan = null;

    // TODO: untuk semua fungsi logon sebaiknya ngambil jsonnya dikonsentrasikan di satu tempat aja
    public static void logon(JSONArray json) {
        ArrayList<Pesan> pesans = new ArrayList<Pesan>();
        try {
            for(int i = 0; i < json.length(); i++) {
                int id = json.getInt(0);
                String msg = json.getString(1);
                pesans.add(new Pesan(id, msg));
            }
        } catch (JSONException e) {
            Log.e(Constants.JSONTAG, "login");
        }
        daftarPesan = new DaftarPesan(pesans);
    }

    public static void kirimPesan(String msg) {
        // TODO: masukin sekalian ke executenya
        JSONParser.getJSONfromURL(Constants.PESAN_ADDR
                + PenggunaController.getCurrentPengguna().getUsername() + '/'
                + msg
        );

        // TODO: somehow kasih return value buat id pesan yang baru dibuat, untuk sementara dikasih 0 dulu
        daftarPesan.tambahPesan(new Pesan(0, msg));
    }
}
