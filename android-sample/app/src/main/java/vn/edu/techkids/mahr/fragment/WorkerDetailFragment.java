package vn.edu.techkids.mahr.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.Cloud;
import vn.edu.techkids.mahr.enitity.JSONObjectDownloadTask;
import vn.edu.techkids.mahr.enitity.JSONObjectParser;
import vn.edu.techkids.mahr.enitity.JSONObjectPostDownloadHandler;
import vn.edu.techkids.mahr.enitity.JSONObjectPreDownloadHandler;
import vn.edu.techkids.mahr.enitity.MigrationProgress;
import vn.edu.techkids.mahr.enitity.Worker;


public class WorkerDetailFragment extends BaseFragment implements View.OnClickListener,
        HttpPutOnPostHandler, JSONObjectParser, JSONObjectPostDownloadHandler, JSONObjectPreDownloadHandler{

    private Worker mWorker;
    private WebView mWvWorkerDetail;
    private Button mBtnShare;
    private Button mBtnConfirm;
    private Button mBtnUse;

    private final String HTTP_PUT_ON_POST_CONFIRM_TAG = "confirm";
    private final String HTTP_PUT_ON_POST_USE_TAG = "use";
    private final  String HTTP_GET_MIGRATION_PROCESS = "migration process";

    private ProgressDialog mProgressDialog = null;

    public WorkerDetailFragment() {
        // Required empty public constructor
        mWorker = new Worker();
        mWorker.setExcel_path("");
        mWorker.setId(1);
    }

    public void setWorker(Worker worker) {
        this.mWorker = worker;
        Log.d("setWorker", mWorker.getExcel_path());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_worker_detail, container, false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        mWvWorkerDetail = (WebView)view.findViewById(R.id.wvWorkerDetail);
        mWvWorkerDetail.setWebViewClient(new AppWebViewClients());
        mWvWorkerDetail.getSettings().setBuiltInZoomControls(true);
        mWvWorkerDetail.getSettings().setSupportZoom(true);
        mWvWorkerDetail.getSettings().setJavaScriptEnabled(true);
        mWvWorkerDetail.getSettings().setDisplayZoomControls(false);

        mWvWorkerDetail.loadUrl("https://docs.google.com/gview?embedded=true&url=" + mWorker.getExcel_path());

        Cloud.getInstance().setUrlLink(mWorker.getExcel_path());

        mBtnConfirm = (Button)view.findViewById(R.id.btnConfirm);
        mBtnShare = (Button)view.findViewById(R.id.btnShare);
        mBtnUse = (Button)view.findViewById(R.id.btnUse);

        mBtnUse.setOnClickListener(this);
        mBtnShare.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("onOptionsItemSelected", "2");
        if (item.getItemId() == R.id.action_share){
            Log.e("onOptionsItemSelected", "3");
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mWorker.getExcel_path());
            startActivity(Intent.createChooser(sharingIntent, mWorker.getExcel_path()));
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareExcelLink() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mWorker.getExcel_path());
        startActivity(Intent.createChooser(sharingIntent, mWorker.getExcel_path()));
    }

    private void sendConfirmCommand() {
        try {
            mProgressDialog = ProgressDialog.show(this.getActivity(), getString(R.string.worker),
                    getString(R.string.loading), true);
            HttpPutTask httpPutTask = new HttpPutTask(new HttpPutQueryBuilder() {
                @Override
                public String buildQuery() {
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter(Constants.API_PUT_STATUS, Constants.API_PUT_WORKER_STATUS_CONFIRM);
                    String query = builder.build().getEncodedQuery();
                    return query;
                }
            },
                    this,
                    HTTP_PUT_ON_POST_CONFIRM_TAG);

            httpPutTask.execute(new URL(String.format(Constants.API_URL_PROFILE_PUT_FORMAT,
                    mWorker.getId())));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void startMigrationProcessDownload() {
        try {
            new JSONObjectDownloadTask(HTTP_GET_MIGRATION_PROCESS, this, this, this).execute(
                    new URL(String.format(Constants.API_URL_PROGRESSES_GET_FORMAT, mWorker.getId()))
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            getScreenManager().setTitleOfActionBar(getString(R.string.detail));
            getScreenManager().showShareButtonOnRightActionBar();
            setHasOptionsMenu(true);
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShare:
                shareExcelLink();
                break;
            case R.id.btnUse:
                sendUseCommand();
                break;
            case R.id.btnConfirm:
                sendConfirmCommand();
                break;
        }
    }

    private void sendUseCommand() {
        try {
            HttpPutTask httpPutTask = new HttpPutTask(new HttpPutQueryBuilder() {
                @Override
                public String buildQuery() {
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter(Constants.API_PUT_STATUS, Constants.API_PUT_WORKER_STATUS_USE);
                    String query = builder.build().getEncodedQuery();
                    return query;
                }
            },
            this,
            HTTP_PUT_ON_POST_USE_TAG);

            httpPutTask.execute(new URL(String.format(Constants.API_URL_PROFILE_PUT_FORMAT,
                    mWorker.getId())));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPost(String tag, Object result) {

        Worker worker;
        switch (tag) {
            case HTTP_PUT_ON_POST_CONFIRM_TAG:
                worker = (Worker)result;
                if(worker != null && worker.getStatus() == Integer.valueOf(Constants.API_PUT_WORKER_STATUS_CONFIRM)) {
                    mWorker.setStatus(Integer.valueOf(Constants.API_PUT_WORKER_STATUS_CONFIRM));
                    showToastMessage(getString(R.string.confirm));
                    startMigrationProcessDownload();
                }
                else {
                    showToastMessage(getString(R.string.message_download_failed));
                }

                break;
            case HTTP_PUT_ON_POST_USE_TAG:
                worker = (Worker)result;
                if(worker != null && worker.getStatus() == Integer.valueOf(Constants.API_PUT_WORKER_STATUS_USE)) {
                    mWorker.setStatus(Integer.valueOf(Constants.API_PUT_WORKER_STATUS_USE));
                    showToastMessage(getString(R.string.use));
                }
                else {
                    showToastMessage(getString(R.string.message_download_failed));
                }
                break;
            case HTTP_GET_MIGRATION_PROCESS:
                if(mProgressDialog != null) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
                MigrationProgress migrationProgress = (MigrationProgress)result;
                if(migrationProgress != null) { /* OK */
                    // bị/mProgressDialog.dismiss();
                    MigrationProcessFragment migrationProcessFragment = new MigrationProcessFragment();
                    //migrationProcessFragment.setMigrationProgress(migrationProgress);
                    getScreenManager().openFragment(migrationProcessFragment, true);
                }
                else {
                    showToastMessage(getString(R.string.message_download_failed));
                }
                break;

        }
    }

    @Override
    public Object parse(String tag, InputStreamReader inputStreamReader) {
        return MigrationProgress.loadFromJSON(inputStreamReader);
    }

    @Override
    public void onPreDownload(String tag) {
        switch (tag) {
            case HTTP_GET_MIGRATION_PROCESS:
                break;
        }
    }

    private class AppWebViewClients extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
        }
    }

    private interface HttpPutQueryBuilder {
        String buildQuery();
    }

    private class HttpPutTask extends AsyncTask<URL, Integer, Object> {

        private HttpPutQueryBuilder mHttpPutQueryBuilder;
        private HttpPutOnPostHandler mHttpPutOnPostHandler;
        private String mTag;

        public HttpPutTask(HttpPutQueryBuilder httpPutQueryBuilder, HttpPutOnPostHandler httpPutOnPostHandler, String tag) {
            this.mHttpPutQueryBuilder = httpPutQueryBuilder;
            this.mHttpPutOnPostHandler = httpPutOnPostHandler;
            this.mTag = tag;
        }

        @Override
        protected Worker doInBackground(URL... params) {
            try {

                URL url = params[0];

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("PUT");
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                if(mHttpPutQueryBuilder != null) {
                    String query = mHttpPutQueryBuilder.buildQuery();
                    Log.d("sendUseCommand", query);
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
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    Worker updatedWorker = (new Gson()).fromJson(reader, Worker.class);
                    Log.d("doInBackground", updatedWorker.getName());
                    return updatedWorker;
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
            if(mHttpPutOnPostHandler != null) {
                mHttpPutOnPostHandler.onPost(mTag, object);
            }

            super.onPostExecute(object);
        }
    }
}

interface HttpPutOnPostHandler {
    void onPost(String tag, Object result);
}