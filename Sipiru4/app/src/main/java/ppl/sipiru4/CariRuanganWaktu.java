package ppl.sipiru4;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Gina on 4/9/2015.
 */
public class CariRuanganWaktu extends Fragment {

    Button btnCari;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.cari_ruangan_waktu_ui, container, false);

        //TODO : get daftar ruangan berdasarkan masukkan pengguna dan simpan

        EditText tglMulai = (EditText)rootView.findViewById(R.id.TglMulai);
        EditText tglSelesai = (EditText)rootView.findViewById(R.id.TglSelesai);
        EditText jamMulai = (EditText)rootView.findViewById(R.id.jamMulai);
        EditText jamSelesai = (EditText)rootView.findViewById(R.id.jamSelesai);
        Button button = (Button) rootView.findViewById(R.id.buttonCari);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO : get daftar ruangan yang bisa dipinjam sesuai dengan tgl mulai ...dst yang diinput pengguna
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new DaftarRuangan());
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
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
