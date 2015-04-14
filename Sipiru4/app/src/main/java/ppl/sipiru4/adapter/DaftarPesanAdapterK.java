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

public class DaftarPesanAdapterK extends ArrayAdapter<DaftarPesanItem> {

    private Context context;
    private ArrayList<DaftarPesanItem> navDrawerItems;

    public DaftarPesanAdapterK(Context context, List<DaftarPesanItem> items) {
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
}
