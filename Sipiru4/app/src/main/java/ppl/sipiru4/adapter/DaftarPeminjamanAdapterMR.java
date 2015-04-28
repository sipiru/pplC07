package ppl.sipiru4.adapter;

import ppl.sipiru4.Entity.Peminjaman;
import ppl.sipiru4.R;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DaftarPeminjamanAdapterMR extends ArrayAdapter<Peminjaman> {

    public DaftarPeminjamanAdapterMR(Context context, List<Peminjaman> items) {
        super(context, R.layout.list, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_peminjaman_mr, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.username = (TextView) convertView.findViewById(R.id.npmPeminjam);
            viewHolder.ruangan = (TextView) convertView.findViewById(R.id.ruangan);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        Peminjaman item = getItem(position);

        viewHolder.username.setText(item.getUsernameP());
        viewHolder.ruangan.setText(item.getKodeRuangan());

        return convertView;
    }
    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see ://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */

    private static class ViewHolder {
        TextView username;
        TextView ruangan;
    }
}
