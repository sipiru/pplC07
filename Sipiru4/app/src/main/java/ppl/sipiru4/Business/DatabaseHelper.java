package ppl.sipiru4.Business;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ppl.sipiru4.Constants;
import ppl.sipiru4.Entities.Peminjaman;
import ppl.sipiru4.Entities.Ruangan;

/**
 * Created by User on 20/04/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context) {
        this(context, Constants.DB_FILENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `daftar_ruang`(`kode` TEXT, nama `TEXT`, `kapasitas` INTEGER, `deskripsi` TEXT)");
        db.execSQL("CREATE TABLE `daftar_ruang`(`kode_ruang` TEXT, identitas_peminjam `TEXT`," +
                "`waktu_mulai` TEXT, `waktu_selesai` TEXT, `status` TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 3)
            ;
        else
            ;

        if (newVersion > 2)
            ;
    }

    public long addRuangan(Ruangan ruangan) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kode", ruangan.getKode());
        values.put("nama", ruangan.getNama());
        values.put("kapasitas", ruangan.getKapasitas());
        values.put("deskripsi", ruangan.getDeskripsi());
        return db.insert("daftar_ruang", null, values);
    }

    public long addPeminjaman(Peminjaman peminjaman) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kode_ruang", peminjaman.getRuangan().getKode());
        values.put("peminjam", peminjaman.getPeminjam().getKodeIdentitas());
        values.put("waktu_mulai", peminjaman.getMulai().toString());
        values.put("waktu_selesai", peminjaman.getSelesai().toString());
        values.put("status", peminjaman.getStatus());
        return db.insert("daftar_peminjaman", null, values);
    }
}