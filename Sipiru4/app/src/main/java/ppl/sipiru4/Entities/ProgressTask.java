package ppl.sipiru4.Entities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by User on 15/04/2015.
 */
public class ProgressTask extends AsyncTask<String, Void, Boolean> {

    private String url;
    private Activity activity;
    private Context context;
    private ProgressDialog progressDialog;
    private JSONArray jsonArray;

    public ProgressTask(String url, Activity activity, Context context) {
        this.url = url;
        this.activity = activity;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            jsonArray = JSONHelper.getArrayFromUrl(url);
        } catch (JSONException|IOException e) {
            Log.e("-->","Mengambil json gagal");
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Mengambil JSON");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        if(progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public JSONArray getJsonArray() {
        execute();
        return jsonArray;
    }
}
