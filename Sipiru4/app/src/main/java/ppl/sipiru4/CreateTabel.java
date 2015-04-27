package ppl.sipiru4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Gina on 4/24/2015.
 */
public class CreateTabel extends Fragment {

    Button create;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.create_tabel_admin, container, false);
        create = (Button)rootView.findViewById(R.id.btnCreate);
        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getActivity(), "Tabel baru baru berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
