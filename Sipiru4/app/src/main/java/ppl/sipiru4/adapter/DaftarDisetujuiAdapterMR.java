package ppl.sipiru4.adapter;

import ppl.sipiru4.R;
import ppl.sipiru4.model.DaftarDisetujuiItemMR;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DaftarDisetujuiAdapterMR extends ArrayAdapter<DaftarDisetujuiItemMR> {

    private Context context;
    private ArrayList<DaftarDisetujuiItemMR> navDrawerItems;

    public DaftarDisetujuiAdapterMR(Context context, List<DaftarDisetujuiItemMR> items) {
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
            viewHolder.npmPeminjam = (TextView) convertView.findViewById(R.id.npmPeminjam);
            viewHolder.ruangan = (TextView) convertView.findViewById(R.id.ruangan);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        DaftarDisetujuiItemMR item = getItem(position);

       viewHolder.npmPeminjam.setText(item.npmPeminjam);
       viewHolder.ruangan.setText(item.ruangan);

        return convertView;
    }
    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see ://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */

    private static class ViewHolder {
        TextView npmPeminjam;
        TextView ruangan;
    }
}
