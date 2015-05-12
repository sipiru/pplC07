package ppl.sipiru4.Entity;

import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSONParser {

    public static JSONArray getJSONfromURL(String url) throws Exception {
//        Log.e("URL", url);
        InputStream is;
        String result;
        JSONArray jArray;
        HttpResponse response;
        DefaultHttpClient httpClient;
        try {
            HttpGet httpget = new HttpGet(url);

            HttpParams httpParams = new BasicHttpParams();
            int timeOut = 5000;
            HttpConnectionParams.setConnectionTimeout(httpParams,timeOut);
            HttpConnectionParams.setSoTimeout(httpParams,timeOut);

            httpClient = new DefaultHttpClient(httpParams);
            response = httpClient.execute(httpget);
        } catch (Exception e) {
            Log.e("log_tag", "Error : " + e.toString());
            e.printStackTrace();
            return null;
        }
        if (response.getStatusLine().getStatusCode()==200) {
            is = response.getEntity().getContent();
            Log.e("isi", is.toString());
        }
        else {
            return null;
        }

        // Convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader( is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
            return null;
        }

        try {
            Log.e("respon",result);
            jArray = new JSONArray(result);
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
            return null;
        }
//        safeClose(httpclient);
        Log.e("log JSON", jArray.toString());
        return jArray;
    }

    public static String getNotifFromURL(String url) throws IOException {
        Log.e("URL", url);
        InputStream is;
        String result;
        HttpResponse response;
        DefaultHttpClient httpClient;
        try {
            HttpGet httpget = new HttpGet(url);

            HttpParams httpParams = new BasicHttpParams();
            int timeOut = 5000;
            HttpConnectionParams.setConnectionTimeout(httpParams,timeOut);
            HttpConnectionParams.setSoTimeout(httpParams,timeOut);

            httpClient = new DefaultHttpClient(httpParams);
            response = httpClient.execute(httpget);
        } catch (Exception e) {
            Log.e("log_tag", "Error : " + e.toString());
            e.printStackTrace();
            return null;
        }
        if (response.getStatusLine().getStatusCode()==200) {
            is = response.getEntity().getContent();
            Log.e("isi", is.toString());
        }
        else {
            return null;
        }

        // Convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader( is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            Log.e("log_tag", "Error converting result " + e.toString());
            return null;
        }
//        safeClose(httpclient);
        Log.e("result",result);
        return result.trim();
    }

//    public static void safeClose(HttpClient client) {
//        if(client != null && client.getConnectionManager() != null)
//        {
//            client.getConnectionManager().shutdown();
//        }
//    }

}