package vn.edu.techkids.mahr.enitity;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by qhuydtvt on 3/18/2016.
 */
//public class JSONObjectPutTask {
//}

public class JSONObjectPutTask extends AsyncTask<URL, Integer, Object> {

    private HttpQueryBuilder mHttpQueryBuilder;

    private JSONObjectParser mJSONObjectParser;
    private JSONObjectPreDownloadHandler mJSONObjectPreDownloadHandler;
    private JSONObjectPostDownloadHandler mJSONObjectPostDownloadHandler;
    private String mTag;

    public  JSONObjectPutTask(HttpQueryBuilder httpQueryBuilder,
                              JSONObjectParser jsonObjectParser) {
        this.mHttpQueryBuilder = httpQueryBuilder;
        this.mJSONObjectParser = jsonObjectParser;
    }

    public JSONObjectPutTask(HttpQueryBuilder httpQueryBuilder,
                             JSONObjectPreDownloadHandler jsonObjectPreDownloadHandler,
                             JSONObjectParser jsonObjectParser,
                             JSONObjectPostDownloadHandler jsonObjectPostDownloadHandler,
                             String tag) {
        this.mHttpQueryBuilder = httpQueryBuilder;
        this.mJSONObjectParser = jsonObjectParser;
        this.mJSONObjectPreDownloadHandler = jsonObjectPreDownloadHandler;
        this.mJSONObjectPostDownloadHandler = jsonObjectPostDownloadHandler;
        this.mTag = tag;
    }

    public void setHttpPutQueryBuilder(HttpQueryBuilder mHttpQueryBuilder) {
        this.mHttpQueryBuilder = mHttpQueryBuilder;
    }

    public void setJSONObjectParser(JSONObjectParser mJSONObjectParser) {
        this.mJSONObjectParser = mJSONObjectParser;
    }

    public void setJSONObjectPreDownloadHandler(JSONObjectPreDownloadHandler mJSONObjectPreDownloadHandler) {
        this.mJSONObjectPreDownloadHandler = mJSONObjectPreDownloadHandler;
    }

    public void setJSONObjectPostDownloadHandler(JSONObjectPostDownloadHandler mJSONObjectPostDownloadHandler) {
        this.mJSONObjectPostDownloadHandler = mJSONObjectPostDownloadHandler;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    @Override
    protected void onPreExecute() {
        if(mJSONObjectPreDownloadHandler !=null) {
            mJSONObjectPreDownloadHandler.onPreDownload(mTag);
        }
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(URL... params) {
        try {

            URL url = params[0];

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            Log.d("doInBackground", url.toString());
            if(mHttpQueryBuilder != null) {
                String query = mHttpQueryBuilder.buildQuery();
                Log.d("doInBackground", query);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);

                writer.flush();
                writer.close();
                os.close();
            }

            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                if(mJSONObjectParser != null) {
                    Object object = mJSONObjectParser.parse(mTag, inputStreamReader);
                    if (object != null)
                        return object;
                }
//                Worker updatedWorker = (new Gson()).fromJson(reader, Worker.class);
//                Log.d("doInBackground", updatedWorker.getChineseName());
////                    StringBuffer response = new StringBuffer();
////                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
////                    String line;
////                    while ((line = bufferedReader.readLine()) != null) {
////                        response.append(line);
////                    }
////
////                    Log.d("HttpPutTask", response.toString());
//
//                    /* Check OK or not here */
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object object) {
        if(this.mJSONObjectPostDownloadHandler != null) {
            mJSONObjectPostDownloadHandler.onPost(mTag, object);
        }

        super.onPostExecute(object);
    }
}
