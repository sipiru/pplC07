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
public class UpdateTabel extends Fragment {

    Button update;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View rootView = inflater.inflate(R.layout.update_tabel_admin, container, false);
        update = (Button)rootView.findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getActivity(), "Update berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
