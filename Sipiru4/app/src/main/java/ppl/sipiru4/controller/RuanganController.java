package ppl.sipiru4.controller;

import ppl.sipiru4.Entity.DaftarRuangan;
import ppl.sipiru4.Entity.Ruangan;

public class RuanganController {

    private RuanganController() {}

    private static DaftarRuangan daftarRuangan = null;

//    public static void retrieveDaftarRuangan() throws ConnectionErrorException, JSONException {
//        JSONArray jsonArray = null;
//        try {
//            jsonArray = JSONHelper.getArrayFromUrl(Constants.RUANG_ADDR);
//        } catch (IOException|JSONException e) {
//            throw new ConnectionErrorException();
//        }
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//            daftarRuangan.tambahRuangan(new Ruangan(jsonObject.getString("kode"),
//                    jsonObject.getString("nama"),
//                    jsonObject.getInt("kapasitas"),
//                    jsonObject.getString("deskripsi")));
//        }
//    }

//    public static boolean tambahRuangan (Ruangan ruangan) throws ConnectionErrorException, ParseErrorException {
//        if (daftarRuangan == null)
//            throw new ConnectionErrorException();
//        String strRuangan = "{";
//        strRuangan += "kode:" + ruangan.getKode() + '&' + "nama:" + ruangan.getNama() + '&' +
//                "kapasitas:" + ruangan.getKapasitas() + '&' +
//                "deskripsi:" + ruangan.getDeskripsi();
//        strRuangan += '}';
//
//        try {
//            JSONHelper.post(Constants.RUANG_ADDR, strRuangan);
//        } catch (IOException e) {
//            throw new ParseErrorException();
//        }
//
//        daftarRuangan.tambahRuangan(ruangan);
//        return true;
//    }

    public static Ruangan cariRuangan(String kode) {
        return daftarRuangan.getRuanganByKode(kode);
    }
}
