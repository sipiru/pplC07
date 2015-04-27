package ppl.sipiru4;

/**
 * Created by Gina on 4/9/2015.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

public class JadwalRuangan extends Fragment {
    Button btnSearch;

    public JadwalRuangan() {
        // TODO Auto-generated constructor stub
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.jadwal_suatu_ruangan, container, false);
        Spinner ruang = (Spinner)rootView.findViewById(R.id.ruang);
        btnSearch = (Button)rootView.findViewById(R.id.btnTglMulai);
        //TODO get semua jadwal ruang yang click
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("MyTag", "TabFragment0--onDestroyView");
    }
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d("MyTag", "TabFragment0--onViewStateRestored");
    }
}
