package ppl.sipiru4.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Gina on 4/8/2015.
 */
public class DaftarPermohonanItem {

    public final Drawable icon;
    public final Drawable icon2;// the drawable for the ListView item ImageView
    public final String title; // the text for the ListView item title
    public DaftarPermohonanItem(String title, Drawable icon, Drawable icon2) {
        this.title = title;
        this.icon = icon;
        this.icon2 = icon2;

    }

}
