package ppl.sipiru4;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;
import org.json.JSONArray;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import ppl.sipiru4.Entity.JSONParser;

public class CariRuanganWaktu extends Fragment {
    ImageButton btnCari;
    static Button tglMulai;
    static Button tglSelesai;
    static Button jamMulai;
    static Button jamSelesai;
    static String tanggalMulai;
    static String tanggalSelesai;
    boolean tglMulaiClicked;
    boolean tglSelesaiClicked;
    boolean jamMulaiClicked;
    boolean jamSelesaiClicked;

    public CariRuanganWaktu(){}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cari_ruangan_waktu_ui, container, false);
        tglMulai = (Button)rootView.findViewById(R.id.btnTglMulai);
        tglMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DialogFragment newFragment = new SelectTglMulai();
                newFragment.show(getFragmentManager(),"");
                tglMulaiClicked=true;
            }
        });

        tglSelesai = (Button)rootView.findViewById(R.id.btnTglSelesai);
        tglSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DialogFragment newFragment = new SelectTglSelesai();
                newFragment.show(getFragmentManager(),"");
                tglSelesaiClicked=true;
            }
        });

        jamMulai = (Button)rootView.findViewById(R.id.btnJamMulai);
        jamMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DialogFragment newFragment = new SelectJamMulai();
                newFragment.show(getFragmentManager(),"");
                jamMulaiClicked=true;
            }
        });

        jamSelesai = (Button)rootView.findViewById(R.id.btnJamSelesai);
        jamSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DialogFragment newFragment = new SelectJamSelesai();
                newFragment.show(getFragmentManager(),"");
                jamSelesaiClicked=true;
            }
        });

        btnCari = (ImageButton) rootView.findViewById(R.id.buttonCari);
        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tglMulaiClicked&&tglSelesaiClicked&&jamMulaiClicked&&jamSelesaiClicked) {
                    Date now = new Date();
                    Date dateAwal;
                    Date dateAkhir;

                    SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                    try {
                        dateAwal = formatter.parse(tanggalMulai+ " " + jamMulai.getText());
                        dateAkhir = formatter.parse(tanggalSelesai + " " + jamSelesai.getText());

//                        Toast.makeText(getActivity().getApplicationContext(), dateAwal + " " + dateAkhir , Toast.LENGTH_LONG).show();

                        // pengecekan validasi input tanggal
                        if (dateAkhir.after(dateAwal) && dateAwal.after(now)) {
                            long seconds = dateAkhir.getTime() - dateAwal.getTime();
                            long days = seconds/(24*60*60*1000);
                            if (days > 2) {
                                Toast.makeText(getActivity(), "Tidak bisa meminjam ruangan dalam waktu lebih dari 2 hari", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                ConnectivityManager connMgr = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                                if (networkInfo!=null && networkInfo.isConnected()) {
                                    new TaskHelper().execute("http://ppl-c07.cs.ui.ac.id/connect/showDaftarRuangan/"
                                            + tanggalMulai + "%20" + jamMulai.getText() + "&" + tanggalSelesai + "%20" + jamSelesai.getText());
                                }
                                else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Mohon periksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        else {
                            Toast.makeText(getActivity(), "Pengisian tanggal tidak valid", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getActivity(),"mohon isi semua tangggal dan jam",Toast.LENGTH_SHORT).show();
                }
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

    public static class SelectTglMulai extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            String yearOutput = ""+year;
            String monthOutput = ""+month;
            String dayOutput = ""+day;
            if (month<10) {monthOutput = "0" + month;}
            if (day<10) {dayOutput = "0" + day;}
            tanggalMulai = yearOutput + "-" + monthOutput + "-" + dayOutput;

            String date = dayOutput + "-" + monthOutput + "-" + yearOutput;
            String dateView = null;
            try {
                Date init = new SimpleDateFormat("dd-MM-yyyy").parse(date);
                dateView = new SimpleDateFormat("dd-MMM-yyyy").format(init);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tglMulai.setText(dateView);
        }
    }

    public static class SelectTglSelesai extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }

        public void onDateSet(DatePicker view, int yy, int mm, int dd) {
            populateSetDate(yy, mm+1, dd);
        }

        public void populateSetDate(int year, int month, int day) {
            String yearOutput = ""+year;
            String monthOutput = ""+month;
            String dayOutput = ""+day;
            if (month<10) {monthOutput = "0" + month;}
            if (day<10) {dayOutput = "0" + day;}
            tanggalSelesai = yearOutput + "-" + monthOutput + "-" + dayOutput;

            String date = dayOutput + "-" + monthOutput + "-" + yearOutput;
            String dateView = null;
            try {
                Date init = new SimpleDateFormat("dd-MM-yyyy").parse(date);
                dateView = new SimpleDateFormat("dd-MMM-yyyy").format(init);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tglSelesai.setText(dateView);
        }
    }

    public static class SelectJamMulai extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar time = Calendar.getInstance();
            int minutes = (time.get(Calendar.MINUTE)/10 + 1) * 10;
            int hour = time.get(Calendar.HOUR_OF_DAY);
            if (minutes==60) {
                hour = hour+1;
            }
            return new TimePickerDialog(getActivity(),this,hour, minutes, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hour, int minutes){
            String hourOutput = ""+hour;
            String minutesOutput = ""+minutes;
            if (hour<10) {hourOutput="0"+hour;}
            if (minutes<10) {minutesOutput="0"+minutes;}
            jamMulai.setText(hourOutput + ":" + minutesOutput + ":00");
        }
    }

    public static class SelectJamSelesai extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar time = Calendar.getInstance();
            int minutes = (time.get(Calendar.MINUTE)/10 + 1) * 10;
            int hour = time.get(Calendar.HOUR_OF_DAY);
            if (minutes==60) {
                hour = hour + 1;
            }
            return new TimePickerDialog(getActivity(),this,hour, minutes, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hour, int minutes){
            String hourOutput = ""+hour;
            String minutesOutput = ""+minutes;
            if (hour<10) {hourOutput="0"+hour;}
            if (minutes<10) {minutesOutput="0"+minutes;}
            jamSelesai.setText(hourOutput + ":" + minutesOutput + ":00");
        }
    }

    // kelas AsyncTask untuk mengakses URL
    private class TaskHelper extends AsyncTask<String, String, JSONArray> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Mencari ruangan...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected JSONArray doInBackground(String... args) {
            try {
                return JSONParser.getJSONfromURL(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONArray hasil) {
            if (hasil == null) {
                pDialog.dismiss();
                Toast.makeText(getActivity(),"gagal terhubung ke server. coba lagi.", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent i = new Intent(getActivity(), DaftarRuangan.class);
            //mengoper JSONArray ruangan ke DaftarRuangan.class
            i.putExtra("daftarRuangan", hasil.toString());
            i.putExtra("waktuAwal", tanggalMulai+" "+jamMulai.getText().toString());
            i.putExtra("waktuAkhir", tanggalSelesai+" "+jamSelesai.getText().toString());
            startActivity(i);
            pDialog.dismiss();
        }
    }
}
