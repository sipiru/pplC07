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
import ppl.sipiru4.model.DaftarPesanItem;

public class DaftarPesanAdapterMR extends ArrayAdapter<DaftarPesanItem> {

    private Context context;
    private ArrayList<DaftarPesanItem> navDrawerItems;

    public DaftarPesanAdapterMR(Context context, List<DaftarPesanItem> items) {
        super(context, R.layout.list_daftarpesan, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_daftarpesan, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.hapus = (Button) convertView.findViewById(R.id.hapus);
            final View finalConvertView = convertView;
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label);
            viewHolder.hapus.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    //TODO mengahpus data pesan
                    //TODO memanggil kembali fragment DaftarPesan
                    Toast.makeText(finalConvertView.getContext(),
                            "Pesan sudah berhasil dihapusi", Toast.LENGTH_LONG).show();
                }
            });
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        DaftarPesanItem item = getItem(position);

        viewHolder.tvTitle.setText(item.title);
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
        Button hapus;
    }

/*   public DaftarPesanAdapter(Context context, ArrayList<DaftarPesanItem> navDrawerItems){
        super(context, R.layout.list_Pesan, navDrawerItems);
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
            convertView = mInflater.inflate(R.layout.ui_tiap_list_Pesan, null);
        }

        TextView txtTitle = (TextView) convertView.findViewById(R.id.label);
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        Button btnSetuju = (Button) convertView.findViewById(R.id.setuju);
        Button btnTolak =(Button) convertView.findViewById(R.id.tolak);
        return convertView;
    }*/

}
