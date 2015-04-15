package ppl.sipiru4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ppl.sipiru4.R;
import ppl.sipiru4.model.DaftarPeminjamanItemP;

public class DaftarPeminjamanAdapterP extends ArrayAdapter<DaftarPeminjamanItemP> {

    private Context context;
    private ArrayList<DaftarPeminjamanItemP> navDrawerItems;

    public DaftarPeminjamanAdapterP(Context context, List<DaftarPeminjamanItemP> items) {
        super(context, R.layout.list_permohonan, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_peminjaman_p, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label);
            viewHolder.status = (TextView) convertView.findViewById(R.id.statusClosed);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        DaftarPeminjamanItemP item = getItem(position);

        viewHolder.tvTitle.setText(item.title);
        viewHolder.status.setText(item.statusClosed);
        return convertView;
    }
    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see ://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */

    private static class ViewHolder {
        TextView tvTitle;
        TextView status;
    }


}
