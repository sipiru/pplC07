package ppl.sipiru4.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.R;

public class DaftarPengembalianAlatAdapterFI extends ArrayAdapter<Peminjaman>{
    public DaftarPengembalianAlatAdapterFI(Context context, List<Peminjaman> items) {
        super(context, R.layout.list_permohonan, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_peminjaman_fi, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.nama = (TextView) convertView.findViewById(R.id.nama);
            viewHolder.perihal = (TextView) convertView.findViewById(R.id.deskripsi);
            viewHolder.statusP = (TextView) convertView.findViewById(R.id.status);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        Peminjaman item = getItem(position);

        viewHolder.nama.setText(item.getNamaP());
        viewHolder.perihal.setText(item.getPerihal());
        if (item.getStatusPeminjam()) {
            viewHolder.statusP.setText("Peralatan belum dikembalikan");
            viewHolder.statusP.setTextColor(Color.RED);
        } else {
            viewHolder.statusP.setText("Selesai");
            viewHolder.statusP.setTextColor(Color.BLACK);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView nama;
        TextView perihal;
        TextView statusP;
        ImageView img;
    }
}
