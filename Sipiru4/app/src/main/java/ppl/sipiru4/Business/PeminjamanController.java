package ppl.sipiru4.Business;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import ppl.sipiru4.Constants;
import ppl.sipiru4.Entity.DaftarPeminjaman;
import ppl.sipiru4.Entity.Peminjaman;

/**
 * Created by User on 11/04/2015.
 */
public class PeminjamanController {

    private static DaftarPeminjaman daftarPeminjaman = null;

    public static boolean tambahPeminjaman(Peminjaman peminjaman) {
        if (daftarPeminjaman == null) {
            Log.e("PeminjamanController:", "daftar peminjaman null");
            return false;
        }
        String strPeminjaman = "{";
        strPeminjaman += "peminjam:" + peminjaman.getPeminjam().getUsername() + '&' + "mulai:" +
                peminjaman.getMulai().toString() + '&' +
                "selesai:" + peminjaman.getSelesai().toString() + '&' +
                "ruangan:" + peminjaman.getRuangan().getKode() + "status:" +peminjaman.getStatus();
        strPeminjaman += '}';

        try {
            JSONParser.post(Constants.PINJAM_ADDR, strPeminjaman);
        } catch (IOException e) {
            return false;
        }

        daftarPeminjaman.tambahPeminjaman(peminjaman);
        return true;
    }

    public static void login(JSONArray json) {
        // TODO: cek daftar peminjaman terus atur
        ArrayList<Peminjaman> peminjamans = new ArrayList<Peminjaman>();
        try {
            for (int i = 0; i < json.length(); i++) {

            }
            daftarPeminjaman = new DaftarPeminjaman(peminjamans);
        } catch (JSONException e) {

        }
    }
}
