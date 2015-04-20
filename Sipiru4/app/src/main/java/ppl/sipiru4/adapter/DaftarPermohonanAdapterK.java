package ppl.sipiru4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ppl.sipiru4.R;
import ppl.sipiru4.model.DaftarPermohonanItemK;

public class DaftarPermohonanAdapterK extends ArrayAdapter<DaftarPermohonanItemK> {

    private Context context;
    private ArrayList<DaftarPermohonanItemK> navDrawerItems;

    public DaftarPermohonanAdapterK(Context context, List<DaftarPermohonanItemK> items) {
        super(context, R.layout.list, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_permohonan_mk, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.npmPeminjam = (TextView) convertView.findViewById(R.id.npmPeminjam);
            final View finalConvertView = convertView;
            viewHolder.ruangan = (TextView) convertView.findViewById(R.id.ruangan);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        DaftarPermohonanItemK item = getItem(position);

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


/*   public DaftarPermohonanAdapter(Context context, ArrayList<DaftarPermohonanItem> navDrawerItems){
        super(context, R.layout.list_permohonan, navDrawerItems);
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

*//*    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }*//*

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.ui_tiap_list_permohonan, null);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.label);
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        Button btnSetuju = (Button) convertView.findViewById(R.id.setuju);
        Button btnTolak =(Button) convertView.findViewById(R.id.tolak);
        return convertView;
    }*/

}