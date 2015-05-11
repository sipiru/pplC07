package ppl.sipiru4;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class HapusRuangan extends Fragment {

    Button delete;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View rootView = inflater.inflate(R.layout.delete_tabel_admin, container, false);
        delete = (Button)rootView.findViewById(R.id.btnDelete);
        delete.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getActivity(), "Tabel berhasil dihapus dari database", Toast.LENGTH_SHORT).show();
//                AlertDialog.Builder alert = new AlertDialog.Builder() {
//                });
//                alert.setTitle("Apakah anda yakin ingin menghapus tabel ini?");
//                alert
//                        .setMessage("Tekan Ya untuk menghapus tabel")
//                        .setCancelable(false)
//                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                //TODO : ngehapus tabelnya
//                            }
//                        })
//                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
            }
        });
        return rootView;
    }
}
