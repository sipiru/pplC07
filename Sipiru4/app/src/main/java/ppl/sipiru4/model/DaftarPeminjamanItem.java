package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarPeminjamanItem {

    public final String title;

    // the text for the ListView item title
    public DaftarPeminjamanItem(String title ) {

        this.title = title;
    }

    public String getTitle() {
        return this.title;

    }

    public String setTitle() {
        return title;
    }

}
