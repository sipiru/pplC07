package ppl.sipiru4.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ppl.sipiru4.Entity.Peminjaman;

public class PeminjamanController {
    Peminjaman[] peminjamans;
    int size;

    public PeminjamanController(Peminjaman peminjaman) {
        peminjamans = new Peminjaman[1];
        peminjamans[0] = peminjaman;
    }

    public PeminjamanController(JSONArray jArray) {
        peminjamans = new Peminjaman[jArray.length()];
        for (int i = 0 ; i < jArray.length(); i++) {
            try {
                JSONObject jPeminjaman = jArray.getJSONObject(i);
                int id = jPeminjaman.getInt("id");
                String kodeRuangan = jPeminjaman.getString("kode_ruangan");
                String namaP = jPeminjaman.getString("nama_peminjam");
                String usernameP = jPeminjaman.getString("username_peminjam");
                boolean statusPeminjam = jPeminjaman.getBoolean("status_peminjam");
                String perihal = jPeminjaman.getString("perihal");
                String kegiatan = jPeminjaman.getString("kegiatan");
                String mulai = jPeminjaman.getString("waktu_awal_pinjam");
                String selesai = jPeminjaman.getString("waktu_akhir_pinjam");
                String peralatan = jPeminjaman.getString("peralatan");
                int status = jPeminjaman.getInt("status");
                String alasanDitolak = jPeminjaman.getString("alasan_ditolak");

                peminjamans[i] = new Peminjaman(id,kodeRuangan,usernameP,namaP,statusPeminjam,mulai,selesai,perihal,kegiatan,peralatan,status,alasanDitolak);
                size++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Peminjaman getPeminjaman(int i) {
        return peminjamans[i];
    }

    public Peminjaman getPeminjaman() {
        return peminjamans[0];
    }

    public int getSize() {
        return size;
    }
}
