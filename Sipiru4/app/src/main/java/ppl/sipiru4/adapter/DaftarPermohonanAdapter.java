package ppl.sipiru4.adapter;

import ppl.sipiru4.R;
import ppl.sipiru4.model.DaftarPermohonanItem;
import ppl.sipiru4.model.NavDrawerItem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DaftarPermohonanAdapter extends ArrayAdapter<DaftarPermohonanItem> {

    private Context context;
    private ArrayList<DaftarPermohonanItem> navDrawerItems;

    public DaftarPermohonanAdapter(Context context, List<DaftarPermohonanItem> items) {
        super(context, R.layout.daftar_permohonan_ui, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_permohonan, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            //viewHolder.ivIcon = (Button) convertView.findViewById(R.id.setuju);
            //viewHolder.ivIcon = (Button) convertView.findViewById(R.id.tolak);
            //viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.label);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        DaftarPermohonanItem item = getItem(position);

//        viewHolder.tvTitle.setText(item.title);

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
    }

/*    public DaftarPermohonanAdapter(Context context, ArrayList<DaftarPermohonanItem> navDrawerItems){
        super(context, R.layout.daftar_permohonan_ui, navDrawerItems);
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

        TextView txtTitle = (TextView) convertView.findViewById(R.id.textView1);
        txtTitle.setText(navDrawerItems.get(position).getTitle());
        return convertView;
    }*/

}
