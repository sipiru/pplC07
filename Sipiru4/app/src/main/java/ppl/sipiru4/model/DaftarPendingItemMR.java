package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarPendingItemMR {

    public final String username;
    public final String ruangan;

    // the text for the ListView item title
    public DaftarPendingItemMR(String username, String ruangan) {
        this.username = username;
        this.ruangan = ruangan;
    }

    public String getTitle() {
        return this.username;
    }

    public String getRuangan() {
        return this.ruangan;
    }
}

