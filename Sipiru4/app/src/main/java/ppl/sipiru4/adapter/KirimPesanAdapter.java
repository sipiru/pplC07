package ppl.sipiru4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ppl.sipiru4.Entity.Manajer;
import ppl.sipiru4.R;

public class KirimPesanAdapter extends ArrayAdapter<Manajer>{
    public KirimPesanAdapter(Context context, ArrayList<Manajer> items) {
        super(context, R.layout.list, items );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.kirim_pesan_adapter_ui, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.nama = (TextView) convertView.findViewById(R.id.nama);
            viewHolder.role = (TextView) convertView.findViewById(R.id.role);
            viewHolder.no_hp = (TextView) convertView.findViewById(R.id.deskripsi);
            convertView.setTag(viewHolder);

        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // update the item view
        Manajer item = getItem(position);

        viewHolder.nama.setText(item.getNama());
        viewHolder.role.setText(item.getRole());
        viewHolder.no_hp.setText(item.getNo_hp());
        return convertView;
    }

    private static class ViewHolder {
        TextView nama;
        TextView role;
        TextView no_hp;
    }
}
