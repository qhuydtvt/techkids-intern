package vn.edu.techkids.mahr.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.MalformedURLException;
import java.net.URL;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.DataChangeListener;
import vn.edu.techkids.mahr.enitity.HttpQueryBuilder;
import vn.edu.techkids.mahr.enitity.JSONObjectPostDownloadHandler;
import vn.edu.techkids.mahr.enitity.JSONObjectPreDownloadHandler;
import vn.edu.techkids.mahr.enitity.JSONObjectPutTask;
import vn.edu.techkids.mahr.enitity.MigrationProgresJSONParser;
import vn.edu.techkids.mahr.enitity.MigrationProgress;

/**
 * Created by qhuydtvt on 3/18/2016.
 */
public abstract class MigrationParamFragment extends BaseDialogFragment implements
        HttpQueryBuilder,
        JSONObjectPreDownloadHandler,
        JSONObjectPostDownloadHandler {

    protected String mTitle;

    protected ProgressDialog progressDialog;

    protected abstract void fillData();

    protected void callPutAPI() {
        JSONObjectPutTask jsonObjectPutTask = new JSONObjectPutTask(this, new MigrationProgresJSONParser());
        jsonObjectPutTask.setJSONObjectPreDownloadHandler(this);
        jsonObjectPutTask.setJSONObjectPostDownloadHandler(this);
        try {
            jsonObjectPutTask.execute(new URL(String.format(
                    Constants.API_URL_PROGRESSES_PUT_FORMAT,
                    MigrationProgress.getInst().getProfileId())));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected void showDatePicker(String title, String dateString,
                                  DatePickerDialog.OnDateSetListener onDateSetListener,
                                  String tag) {

        String[] splitedDateString = dateString.split("-");
        int yy = Integer.valueOf(splitedDateString[0]);
        int mm = Integer.valueOf(splitedDateString[1]);
        int dd = Integer.valueOf(splitedDateString[2]);

        DatePickerFragment datePickerFragment = new DatePickerFragment();

        datePickerFragment.setDate(yy, mm, dd);
        datePickerFragment.setTagForDatePicker(tag);
        datePickerFragment.setOnDateSetListener(onDateSetListener);
        getScreenManager().showDialogFragment(datePickerFragment, "");
    }

    @Override
    public void onPreDownload(String tag) {
        progressDialog = ProgressDialog.show(getScreenManager().getContext(),
                mTitle,
                getString(R.string.loading));
    }

    @Override
    public void onPost(String tag, Object object) {
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if(object != null){
            MigrationProgress.notifyDataChanged();
            this.dismiss();
        }
        else {
            getScreenManager().showMessage(getString(R.string.message_download_failed));
        }
    }

    protected String getDateString(String dateString) {
        if(dateString == null || dateString == "") return "0000-00-00";
        return dateString;
    }
}

