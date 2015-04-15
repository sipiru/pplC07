package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarPesanItem {

    public final String title;
    public final Drawable icon;

    // the text for the ListView item title
    public DaftarPesanItem(String title, Drawable icon) {

        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;

    }

    public String setTitle() {
        return title;
    }
}
