package ppl.sipiru4.model;


import android.graphics.drawable.Drawable;

public class DaftarPesanItem {
 // the drawable for the ListView item ImageView
    public final String judulPesan;
    public final String potonganPesan;
    public final Drawable btnHapus;
    // the text for the ListView item title
// the text for the ListView item description
    public DaftarPesanItem(String judulPesan, String potonganPesan, Drawable btnHapus) {
        this.judulPesan = judulPesan;
        this.potonganPesan = potonganPesan;
        this.btnHapus= btnHapus;
    }
}
