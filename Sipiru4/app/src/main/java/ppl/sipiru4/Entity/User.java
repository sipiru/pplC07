package ppl.sipiru4.Entity;

import android.content.Context;
import android.content.SharedPreferences;

public class User {
    private Context con;
    private static SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public User() {
    }

    public User(Context con,String username, String nama, String kodeOrg, String role, String kodeIdentitas) {
        this.con = con;
        pref = con.getSharedPreferences("pref",0);
        editor = pref.edit();
        editor.putString("username",username);
        editor.putString("nama", nama);
        editor.putString("kodeOrg", kodeOrg);
        editor.putString("role",role);
        editor.putString("kodeIdentitas",kodeIdentitas);
        editor.commit();
    }

    public User(Context con,String waktuMulai, String waktuSelesai) {
        this.con = con;
        pref = con.getSharedPreferences("pref",0);
        editor = pref.edit();
        editor.putString("waktuMulai", waktuMulai);
        editor.putString("waktuSelesai",waktuSelesai);
        editor.commit();
    }

    public User(Context con,String kodeRuangan) {
        this.con = con;
        pref = con.getSharedPreferences("pref",0);
        editor = pref.edit();
        editor.putString("kodeRuangan", kodeRuangan);
        editor.commit();
    }

    public static String getUsername(Context con) {
        pref = con.getSharedPreferences("pref",0);
        return pref.getString("username", null);
    }

    public static String getNama(Context con) {
        pref = con.getSharedPreferences("pref",0);
        return pref.getString("nama", null);
    }

    public static String getKodeOrg(Context con) {
        pref = con.getSharedPreferences("pref",0);
        return pref.getString("kodeOrg",null);
    }

    public static String getRole(Context con) {
        pref = con.getSharedPreferences("pref",0);
        return pref.getString("role",null);
    }

    public static String getKodeIdentitas(Context con) {
        pref = con.getSharedPreferences("pref",0);
        return pref.getString("kodeIdentitas",null);
    }

    public static String getWaktuMulai(Context con) {
        pref = con.getSharedPreferences("pref",0);
        return pref.getString("waktuMulai",null);
    }
    public static String getWaktuSelesai(Context con) {
        pref = con.getSharedPreferences("pref",0);
        return pref.getString("waktuSelesai",null);
    }
    public static String getKodeRuangan(Context con) {
        pref = con.getSharedPreferences("pref",0);
        return pref.getString("kodeRuangan",null);
    }

//    @Override
//    public boolean equals(Context con, Object o) {
//        return ((User)o).getUsername(con).compareTo(getUsername(this.con))==0 && ((User) o).getNama(con).compareTo(getNama(this.con))==0
//                && ((User) o).getKodeOrg(con).compareTo(getKodeOrg(this.con))==0 && ((User) o).getRole(con).compareTo(getRole(this.con))==0
//                && ((User) o).getKodeIdentitas(con).compareTo(getKodeIdentitas(this.con))==0;
//    }
}
