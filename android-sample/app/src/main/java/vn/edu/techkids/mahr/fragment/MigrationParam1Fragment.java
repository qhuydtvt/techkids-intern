package vn.edu.techkids.mahr.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class MigrationParam1Fragment
        extends MigrationParamFragment implements
        View.OnClickListener, DatePickerDialog.OnDateSetListener {

    protected Button mOKButton;

    protected TextView mEndDateTextView;
    protected CheckBox mCheckBoxDone;

//    protected ProgressDialog progressDialog;

//    public MigrationParam1Fragment() {
//        super();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_date_time_picker, container, false);
        initLayout(view);
        fillData();
        return view;
    }

    protected void initLayout(View view) {
        getDialog().setTitle(mTitle);

        mOKButton = (Button) view.findViewById(R.id.btn_OK);
        mEndDateTextView = (TextView) view.findViewById(R.id.tv_end_date);
        mCheckBoxDone = (CheckBox) view.findViewById(R.id.chb_progress_done);

        mOKButton.setOnClickListener(this);
        mEndDateTextView.setOnClickListener(this);
//        mEndDateTextView.setOnClickListener(this);
//        mEndDateTextView.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        showDatePicker(mEndDateTextView.getText().toString(),"TAG");
//                    }
//                }
//        );

//        if (mEndDateTextView != null) {

//        }

//        registerListeners();
    }
//    }

//    protected abstract void fillData();

    //
//    protected void registerListeners() {
//        MigrationProgress.getInst().setmDataChangeListener(this);
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_OK:
//                handleOKClick();
//                break;
//            case R.id.tv_end_date:
//                handleEndDateClick();
//                break;
//        }
//    protected void handleOKClick() {
//        JSONObjectPutTask jsonObjectPutTask = new JSONObjectPutTask(this, new MigrationProgresJSONParser());
//        jsonObjectPutTask.setJSONObjectPostDownloadHandler(this);
//        try {
//            jsonObjectPutTask.execute(new URL(String.format(
//                    Constants.API_URL_PROGRESSES_PUT_FORMAT,
//                    MigrationProgress.getInst().getProfileId())));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected void handleEndDateClick() {
//        final Calendar c = Calendar.getInstance();
//
//        int yy = c.get(Calendar.YEAR);
//        int mm = c.get(Calendar.MONTH);
//        int dd = c.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerFragment datePickerFragment = new DatePickerFragment();
//        datePickerFragment.setDate(yy, mm, dd);
//        datePickerFragment.setOnDateSetListener(this);
//        getScreenManager().showDialogFragment(datePickerFragment, "");
//    }
//
//    public MigrationParam1Fragment setTitle(String title) {
//        this.mTitle = title;
//        return this;
//    }
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        this.mEndDateTextView.setText(String.format(Constants.API_DATE_FORMAT, dayOfMonth, monthOfYear, year));

    protected String getCheckedString() {
        return mCheckBoxDone.isChecked() ? Constants.API_PUT_STATUS_DONE : Constants.API_PUT_STATUS_NOT_DONE;
    }

    protected String getEndDateString() {
        return mEndDateTextView.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_OK:
                callPutAPI();
                break;
            case R.id.tv_end_date:
                showDatePicker(getString(R.string.end), mEndDateTextView.getText().toString(), this,"TAG");
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mEndDateTextView.setText(String.format(Constants.API_DATE_FORMAT, year, monthOfYear, dayOfMonth));
    }

//    }

//    }

//    @Override
//    public void onPreDownload(String tag) {
//        progressDialog = ProgressDialog.show(getScreenManager().getContext(),
//                mTitle,
//                getString(R.string.loading));
//    }

//    @Override
//    public void onPost(String tag, Object object) {
//        if(progressDialog != null){
//            progressDialog.dismiss();
//            progressDialog = null;
//        }
//        if(object != null){
//            this.dismiss();
//        }
//        else {
//            getScreenManager().showMessage(getString(R.string.message_download_failed));
//        }
//    }
//
//    @Override
//    public void onDataChange(Object object) {
//        fillData();
//    }
}