package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarRuanganItem {

    public final String title;

    // the text for the ListView item title
    public DaftarRuanganItem(String title) {

        this.title = title;
    }

    public String getTitle() {
        return this.title;

    }

    public String setTitle() {
        return title;
    }
}
