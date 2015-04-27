package ppl.sipiru4.Entity;

/**
 * Created by ACER on 22/04/2015.
 */
import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by Evan Octavius S on 4/6/2015.
 */
public class JSONParser extends AsyncTask {
    static HttpClient httpclient;
    @Override
    protected Object doInBackground(Object[] url) {
        JSONArray temp = getJSONfromURL((String) url[0]);
        return temp;
    }


    @Override
    protected void onPostExecute(Object o) {
        httpclient.getConnectionManager().shutdown();
    }

    public static JSONArray getJSONfromURL(String url) {
        Log.e("URL", url);
        InputStream is = null;
        String result = "";
        JSONArray jArray = null;

        HttpResponse response;

        httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        // Download JSON data from URL
        try {
            Log.e("proses", "1");
            response = httpclient.execute(httpget);
            Log.e("proses","2");
            HttpEntity entity = response.getEntity();
            if(entity!= null) {
                is = entity.getContent();
            }
            else{
            }

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
            return null;
        }

        // Convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader( is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
            return null;
        }

        try {
            jArray = new JSONArray(result);
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
            return null;
        }
        Log.e("log_tag", "gak kena error");
        Log.e("log JSON", jArray.toString());
        return jArray;
    }
}