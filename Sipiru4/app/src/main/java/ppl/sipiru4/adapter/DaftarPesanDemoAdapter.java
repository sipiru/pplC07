package ppl.sipiru4.adapter;

/**
 * Created by Gina on 4/2/2015.
 */

import ppl.sipiru4.model.DaftarPesanItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import java.util.List;
import ppl.sipiru4.R;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DaftarPesanDemoAdapter extends ArrayAdapter<DaftarPesanItem> {

    public DaftarPesanDemoAdapter(Context context, List<DaftarPesanItem> items) {
        super(context, R.layout.daftar_pesan_ui, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.pesan_list_item_ui, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.ikon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        DaftarPesanItem item = getItem(position);
        viewHolder.ivIcon.setImageDrawable(item.icon);
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
        ImageView ivIcon;
        TextView tvTitle;
    }
}
