package ppl.sipiru4;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class UpdateRole extends Fragment {

    Button update;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstancesState){
        View rootView = inflater.inflate(R.layout.update_akun_admin, container, false);
        update = (Button)rootView.findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getActivity(), "Role baru berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
