package ppl.sipiru4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.R;

public class DaftarPermohonanAdapterP extends ArrayAdapter<Peminjaman> {

    public DaftarPermohonanAdapterP(Context context, List<Peminjaman> items) {
        super(context, R.layout.list, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_permohonan_p, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.gambar = (ImageView) convertView.findViewById(R.id.gambar);
            viewHolder.kodeRuangan = (TextView) convertView.findViewById(R.id.kodeRuang);
            viewHolder.namaPeminjam = (TextView) convertView.findViewById(R.id.namaPeminjam);
            viewHolder.status = (TextView) convertView.findViewById(R.id.status);
            viewHolder.tglPeminjaman = (TextView) convertView.findViewById(R.id.tglPeminjaman);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        Peminjaman item = getItem(position);


        viewHolder.gambar.setImageDrawable(item.getGambar());
        viewHolder.kodeRuangan.setText(item.getKodeRuangan());
        viewHolder.namaPeminjam.setText(item.getNamaP());
        viewHolder.tglPeminjaman.setText(item.getMulai() + "-" + item.getSelesai());
        if (item.getStatus().equalsIgnoreCase("0")) {
            viewHolder.status.setText("MR");
        }
        else if (item.getStatus().equalsIgnoreCase("1")) {
            viewHolder.status.setText("MK");
        }
        else {
            viewHolder.status.setText("ITF");
        }

        return convertView;
    }
    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see ://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */

    private static class ViewHolder {
        TextView kodeRuangan;
        TextView namaPeminjam;
        TextView tglPeminjaman;
        TextView status;
        ImageView gambar;
    }
}
