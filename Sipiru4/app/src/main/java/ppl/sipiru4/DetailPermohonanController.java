package ppl.sipiru4;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailPermohonanController extends FragmentActivity {



    public DetailPermohonanController() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.validasi_mr, container, false);


        return rootView;
    }
}


