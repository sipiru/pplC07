package ppl.sipiru4.model;

import android.graphics.drawable.Drawable;


public class UbahStatusPermohonanItem {

    public final Drawable icon;
    //public final Drawable icon2;// the drawable for the ListView item ImageView
    public final String title; // the text for the ListView item title
    public UbahStatusPermohonanItem(String title, Drawable icon) {
        this.title = title;
        this.icon = icon;
    }

}
