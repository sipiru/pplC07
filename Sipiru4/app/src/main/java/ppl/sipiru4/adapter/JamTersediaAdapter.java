package ppl.sipiru4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import ppl.sipiru4.R;
import ppl.sipiru4.model.JamTersediaItem;

public class JamTersediaAdapter extends ArrayAdapter<JamTersediaItem> {

    public JamTersediaAdapter(Context context, ArrayList<JamTersediaItem> items) {
        super(context, R.layout.list, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.ui_tiap_list_jamtersedia, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.jamMulai = (TextView) convertView.findViewById(R.id.mulai);
            viewHolder.jamSelesai = (TextView) convertView.findViewById(R.id.selesai);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        JamTersediaItem item = getItem(position);

        try {
            viewHolder.jamMulai.setText(item.getJamMulai());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        viewHolder.jamSelesai.setText(item.getJamSelesai());

        return convertView;
    }
    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see ://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
     */

    private static class ViewHolder {
        TextView jamMulai;
        TextView jamSelesai;
    }

}
