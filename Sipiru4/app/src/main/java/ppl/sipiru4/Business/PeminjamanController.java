package ppl.sipiru4.Business;

import ppl.sipiru4.Constants;
import ppl.sipiru4.Entities.ConnectionErrorException;
import ppl.sipiru4.Entities.DaftarPeminjaman;
import ppl.sipiru4.Entities.JSONHelper;
import ppl.sipiru4.Business.ParseErrorException;
import ppl.sipiru4.Entities.Peminjaman;
import ppl.sipiru4.Entities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * Created by User on 11/04/2015.
 */
public class PeminjamanController {

    private static DaftarPeminjaman daftarPeminjaman;

    public static boolean tambahPeminjaman(Peminjaman peminjaman) {
        String strPeminjaman = "{";
        strPeminjaman += "peminjam:" + peminjaman.getPeminjam().getUsername() + '&' + "mulai:" +
                peminjaman.getMulai().toString() + '&' +
                "selesai:" + peminjaman.getSelesai().toString() + '&' +
                "ruangan:" + peminjaman.getRuangan().getKode() + "status:" +peminjaman.getStatus();
        strPeminjaman += '}';

        try {
            JSONHelper.post(Constants.PINJAM_ADDR, strPeminjaman);
        } catch (IOException e) {
            return false;
        }

        daftarPeminjaman.tambahPeminjaman(peminjaman);
        return true;
    }

    public static void retrieveAllPeminjaman(User pengguna)
            throws ConnectionErrorException, ParseErrorException {
        JSONArray jsonArray;

        try {
            jsonArray = JSONHelper.getArrayFromUrl(Constants.PEMINJAMAN_ADDR + '&' +
                    "pengguna:" + pengguna.getUsername());
        } catch (IOException|JSONException e) {
            throw new ConnectionErrorException();
        }

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object;
            try {
                object = (JSONObject) jsonArray.get(i);
            } catch (JSONException e) {
                throw new ParseErrorException();
            }
            Peminjaman peminjaman = null;
//            try {
//                peminjaman = new Peminjaman(pengguna,
//                        new GregorianCalendar(object.getString("mulai")),
//                        new GregorianCalendar(object.getString("selesai")),
//                        RuanganController.cariRuangan(object.getString("koderuangan")));
//            } catch (JSONException e) {
//                throw new ParseErrorException();
//            }
            daftarPeminjaman.tambahPeminjaman(peminjaman);
        }
    }
}
