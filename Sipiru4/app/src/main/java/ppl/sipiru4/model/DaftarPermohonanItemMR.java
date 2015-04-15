package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarPermohonanItemMR {

    public final String title;
    public final Drawable icon;
    public final Drawable icon2;

    // the text for the ListView item title
    public DaftarPermohonanItemMR(String title, Drawable icon, Drawable icon2) {

        this.title = title;
        this.icon = icon;
        this.icon2 = icon2;
    }

    public String getTitle() {
        return this.title;

    }

    public String setTitle() {
        return title;
    }
}
