package ppl.sipiru4.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;
import ppl.sipiru4.Entity.JSONParser;
import ppl.sipiru4.Entity.ManajerAdmin;
import ppl.sipiru4.Entity.SessionManager;
import ppl.sipiru4.MainActivityA;
import ppl.sipiru4.R;

public class DaftarManagerAdminAdapter extends ArrayAdapter<ManajerAdmin> {
    SessionManager session;
    int countMR;
    int countMK;
    int countFASUM;
    int countITF;
    int countAdmin;

    public DaftarManagerAdminAdapter(Context context, List<ManajerAdmin> items, int countMR, int countMK, int countFASUM, int countITF, int countAdmin) {
        super(context, R.layout.ui_tiap_list_manager, items );
        this.countMR = countMR;
        this.countMK = countMK;
        this.countFASUM = countFASUM;
        this.countITF = countITF;
        this.countAdmin = countAdmin;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        session = new SessionManager(parent.getContext().getApplicationContext());
        View row = convertView;
        final ViewHolder viewHolder;
        if(row == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(R.layout.ui_tiap_list_manager, parent, false);
            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.nama = (TextView) row.findViewById(R.id.nama);
            viewHolder.username = (TextView) row.findViewById(R.id.username);
            viewHolder.role = (TextView) row.findViewById(R.id.jabatan);
            viewHolder.hapus = (Button) row.findViewById(R.id.btnHapus);
            row.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) row.getTag();
        }
        // update the item view
        final ManajerAdmin item = getItem(position);

        viewHolder.nama.setText(item.getNama());
        viewHolder.username.setText(item.getUsername());
        viewHolder.role.setText(item.getRole());
        viewHolder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(parent.getContext());
                // set title
                alertDialogBuilder.setTitle("Apakah anda yakin untuk menghapus user ini?");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Tekan Ya untuk menghapus")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (viewHolder.role.getText().toString().equals("admin")) {
                                    if (viewHolder.username.getText().toString().equals(session.getUser().getUsername())) {
                                        Toast.makeText(parent.getContext(), "Tidak dapat menghapus akun sendiri"
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                    else if(countAdmin==1) {
                                        Toast.makeText(parent.getContext(), "Tidak boleh dihapus karena hanya ada satu admin"
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        new DeleteHelper(parent).execute("http://ppl-c07.cs.ui.ac.id/connect/deleteAdmin/"
                                                + viewHolder.username.getText().toString());
                                    }
                                } else {
                                    if (viewHolder.role.getText().toString().equals("manajer ruangan") && countMR==1) {
                                        Toast.makeText(parent.getContext(), "Tidak boleh dihapus karena hanya ada satu manajer ruangan"
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                    else if (viewHolder.role.getText().toString().equals("manajer kemahasiswaan") && countMK==1) {
                                        Toast.makeText(parent.getContext(), "Tidak boleh dihapus karena hanya ada satu manajer kemahasiswaan"
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                    else if (viewHolder.role.getText().toString().equals("manajer umum") && countFASUM==1) {
                                        Toast.makeText(parent.getContext(), "Tidak boleh dihapus karena hanya ada satu manajer umum"
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                    else if (viewHolder.role.getText().toString().equals("manajer itf") && countITF==1) {
                                        Toast.makeText(parent.getContext(), "Tidak boleh dihapus karena hanya ada satu manajer itf"
                                                , Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        ConnectivityManager connMgr = (ConnectivityManager) parent.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                                        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                        if (networkInfo!=null && networkInfo.isConnected()) {
                                            new DeleteHelper(parent).execute("http://ppl-c07.cs.ui.ac.id/connect/deleteManager/"
                                                    + viewHolder.username.getText().toString());
                                        }
                                        else {
                                            Toast.makeText(parent.getContext(), "Mohon periksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // memunculkan alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        return row;
    }

    private static class ViewHolder {
        TextView nama;
        TextView username;
        TextView role;
        Button hapus;
    }

    // kelas AsyncTask untuk mengakses URL
    private class DeleteHelper extends AsyncTask<String, String, String> {
        private ProgressDialog pDialog;
        ViewGroup parent;

        public DeleteHelper(ViewGroup parent) {
            this.parent = parent;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(parent.getContext());
            pDialog.setMessage("menghapus role...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                return JSONParser.getNotifFromURL(args[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String data) {
            if (data==null) {
                pDialog.dismiss();
                Toast.makeText(getContext(),"gagal terhubung ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (data.trim().equals("\"sukses\"")){
                Toast.makeText(parent.getContext(), "User berhasil dihapus", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(),MainActivityA.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("navPosition",1);
                getContext().startActivity(i);
            }
            else {
                Toast.makeText(parent.getContext(), "Error.", Toast.LENGTH_SHORT).show();
            }
            pDialog.dismiss();
        }
    }
}
