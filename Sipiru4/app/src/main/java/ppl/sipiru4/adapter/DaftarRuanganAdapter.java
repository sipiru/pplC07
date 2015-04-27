package ppl.sipiru4.adapter;

import ppl.sipiru4.R;
import ppl.sipiru4.model.DaftarRuanganItem;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DaftarRuanganAdapter extends ArrayAdapter<DaftarRuanganItem> {

    private Context context;
    private ArrayList<DaftarRuanganItem> navDrawerItems;

    public DaftarRuanganAdapter(Context context, List<DaftarRuanganItem> items) {
        super(context, R.layout.list, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_daftarruangan, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.kode = (TextView) convertView.findViewById(R.id.kode);
            viewHolder.kapasitas = (TextView) convertView.findViewById(R.id.kapasitas);
            viewHolder.deskripsi= (TextView) convertView.findViewById(R.id.deskripsi);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        DaftarRuanganItem item = getItem(position);

       viewHolder.kode.setText(item.kode);
        viewHolder.kapasitas.setText(item.kapasitas);
        viewHolder.deskripsi.setText(item.deskripsi);

        return convertView;
    }
    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see ://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */

    private static class ViewHolder {
        TextView kode;
        TextView kapasitas;
        TextView deskripsi;

    }

}
