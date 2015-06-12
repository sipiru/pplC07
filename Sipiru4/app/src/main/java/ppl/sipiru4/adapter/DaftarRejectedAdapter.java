package ppl.sipiru4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.R;


public class DaftarRejectedAdapter extends ArrayAdapter<Peminjaman> {

    public DaftarRejectedAdapter(Context context, ArrayList<Peminjaman> items) {
        super(context, R.layout.list, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_rejected, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.kodeRuangan = (TextView) convertView.findViewById(R.id.nama);
            viewHolder.perihal = (TextView) convertView.findViewById(R.id.deskripsi);
            viewHolder.alasan = (TextView) convertView.findViewById(R.id.alasan);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.waktuPinjam = (TextView) convertView.findViewById(R.id.waktuPinjam);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        Peminjaman item = getItem(position);

        viewHolder.kodeRuangan.setText(item.getKodeRuangan());
        viewHolder.perihal.setText(item.getPerihal());
        viewHolder.alasan.setText(item.getAlasanDitolak());

        String[] input = item.getMulai().split(" ");
        String[] tanggal = input[0].split("-");
        String date = tanggal[2]+"-"+tanggal[1]+"-"+tanggal[0] + " " + input[1];
        String dateView = null;
        try {
            Date init = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
            dateView = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(init);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.waktuPinjam.setText(dateView);
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
        TextView perihal;
        TextView alasan;
        ImageView img;
        TextView waktuPinjam;
    }
}
