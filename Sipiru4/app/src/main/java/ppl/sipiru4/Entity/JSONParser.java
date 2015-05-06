package ppl.sipiru4.Entity;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONParser {
    static HttpClient httpclient;

    public static JSONArray getJSONfromURL(String url) {
        Log.e("URL", url);
        InputStream is = null;
        String result;
        JSONArray jArray;

        HttpResponse response;

        httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        // Download JSON data from URL
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if(entity!= null) {
                is = entity.getContent();
            }

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
            return null;
        }

        // Convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader( is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
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

    public static String getNotifFromURL(String url) {
        Log.e("URL", url);
        InputStream is = null;
        String result;

        HttpResponse response;

        httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        // Download JSON data from URL
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if(entity!= null) {
                is = entity.getContent();
            }

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
            return null;
        }

        // Convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader( is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
            return null;
        }

        Log.e("log_tag", "gak kena error");
        Log.e("result",result);
        return result.trim();
    }
}