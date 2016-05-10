package vn.edu.techkids.mahr.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.JSONObjectDownloadTask;
import vn.edu.techkids.mahr.enitity.JSONObjectParser;
import vn.edu.techkids.mahr.enitity.JSONObjectPostDownloadHandler;
import vn.edu.techkids.mahr.enitity.JSONObjectPreDownloadHandler;
import vn.edu.techkids.mahr.enitity.JobCriteria;


/**
 * A simple {@link Fragment} subclass.
 */
public class NationalitySelectionFragment extends BaseFragment
        implements View.OnClickListener, JSONObjectPreDownloadHandler, JSONObjectParser, JSONObjectPostDownloadHandler {

    private Button mVietnamSlectButton;
    private Button mIndoSelectButton;


    private static final String TAG_DOWNLOAD_EXPERTISE = "Download Expertise";
    private static final String TAG_DOWNLOAD_AGENCY = "Download Agency";

    public NationalitySelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nationality_selection, container, false);
        initLayout(view);

        try {
            new JSONObjectDownloadTask(TAG_DOWNLOAD_EXPERTISE, this, this, this).
                    execute(new URL(Constants.API_URL_EXPERTISE));
            new JSONObjectDownloadTask(TAG_DOWNLOAD_AGENCY, this, this, this).
                    execute(new URL(Constants.API_URL_AGENCY));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onStart() {
        /*getScreenManager().hideActionBar();*/
        getScreenManager().setTitleOfActionBar(getString(R.string.action_bar_name));
        getScreenManager().hideDisplayHomeButton();
        super.onStart();
    }

    private void initLayout(View view) {
        mVietnamSlectButton = (Button)view.findViewById(R.id.btnVietnam);
        mIndoSelectButton = (Button)view.findViewById(R.id.btnIndonesia);
        mVietnamSlectButton.setOnClickListener(this);
        mIndoSelectButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String nationality = null;
        switch (v.getId()) {
            case R.id.btnIndonesia:
                nationality = Constants.API_NATIONALITY_INDONESIA;
                break;
            case R.id.btnVietnam:
                nationality = Constants.API_NATIONALITY_VIETNAM;
                break;
        }
        JobCriteria.getInst().setNationality(nationality);
        getScreenManager().openFragment(new MajorSelectionFragment(), true);
    }

    @Override
    public void onPreDownload(String tag) {

    }

    @Override
    public Object parse(String tag, InputStreamReader inputStreamReader) {
        Object object = null;
        switch (tag) {
            case TAG_DOWNLOAD_EXPERTISE:
                object = JobCriteria.getInst().loadExpertiseFromJSON(inputStreamReader);
                break;
            case TAG_DOWNLOAD_AGENCY:
                object = JobCriteria.getInst().loadAgencyFromJSON(inputStreamReader);
                break;
        }
        return object;
    }

    @Override
    public void onPost(String tag, Object object) {

        switch (tag) {
            case TAG_DOWNLOAD_EXPERTISE:
                if(object == null) {
                    showToastMessage(getString(R.string.message_download_failed));
                }
                break;
            case TAG_DOWNLOAD_AGENCY:
                if(object == null) {
                    showToastMessage(getString(R.string.message_download_failed));
                }
                break;
        }


    }




}
