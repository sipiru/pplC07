package ppl.sipiru4.Entity;

import android.util.Log;

import ppl.sipiru4.Constants;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
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

/**
 * Created by User on 11/04/2015.
 */
public class JSONHelper {
    public JSONHelper() {}

    public JSONArray getArrayFromUrl(String url)
            throws IOException, JSONException {
        JSONArray jarray = null;

        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        HttpResponse response = client.execute(httpGet);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } else {
            Log.e("==>", "Failed to download file");
        }

        // Parse String to JSON object
        jarray = new JSONArray(builder.toString());

        // return JSON Object
        return jarray;
    }

    public boolean post(String url, String object) throws IOException {

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams,Constants.TIMEOUT);
        HttpConnectionParams.setSoTimeout(httpParams,Constants.TIMEOUT);
        HttpClient client = new DefaultHttpClient(httpParams);

        HttpPost request = new HttpPost(url);
        request.setEntity(new ByteArrayEntity(object.getBytes("UTF-8")));

        HttpResponse response= client.execute(request);
        return response.getStatusLine().getStatusCode() == 200;
    }
}
