package ppl.sipiru4.adapter;

import ppl.sipiru4.Entity.Ruangan;
import ppl.sipiru4.R;
import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DaftarRuanganAdapter extends ArrayAdapter<Ruangan> {

//    private Context context;
//    private ArrayList<DaftarRuanganItem> navDrawerItems;

    public DaftarRuanganAdapter(Context context, ArrayList<Ruangan> items) {
        super(context, R.layout.list_daftar_ruangan, items );
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
            viewHolder.kodeRuangan = (TextView) convertView.findViewById(R.id.kode);
            viewHolder.namaRuangan = (TextView) convertView.findViewById(R.id.nama);
            viewHolder.kapasitas = (TextView) convertView.findViewById(R.id.kapasitas);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        Ruangan item = getItem(position);

        viewHolder.kodeRuangan.setText(item.getKode());
        viewHolder.namaRuangan.setText(item.getNama());
        viewHolder.kapasitas.setText(""+item.getKapasitas());

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
        TextView namaRuangan;
        TextView kapasitas;
    }

}
