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

import java.text.SimpleDateFormat;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;

import java.util.Calendar;
import java.util.Date;
/**
 * A simple {@link Fragment} subclass.
 */
public abstract class MigrationParam2Fragment extends MigrationParamFragment
        implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    protected Button mOKButton;
    protected TextView mStartDateTextView;
    protected TextView mEndDateTextView;
    protected CheckBox mProgressDoneCheckBox;


    public MigrationParam2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_date_picker, container, false);
        initLayout(view);
        fillData();
//        fillDates();
        return view;
    }



    protected void initLayout(View view) {
        mOKButton = (Button)view.findViewById(R.id.btn_OK);
        mStartDateTextView = (TextView)view.findViewById(R.id.tv_start_date);
        mEndDateTextView = (TextView)view.findViewById(R.id.tv_end_date);
        mProgressDoneCheckBox = (CheckBox)view.findViewById(R.id.chb_progress_done);

        mOKButton.setOnClickListener(this);
        mStartDateTextView.setOnClickListener(this);
        mEndDateTextView.setOnClickListener(this);


        if(mTitle != null) {
            getDialog().setTitle(mTitle);
        }
    }

//    protected void fillDates() {
//        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//        mStartDateTextView.setText(date);
//        mEndDateTextView.setText(date);
//    }

    private final String TAG_START_DATE = "StartDate";
    private final String TAG_END_DATE = "EndDate";

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_OK:
                callPutAPI();
                break;
            case R.id.tv_start_date:
                showDatePicker(getString(R.string.start), mStartDateTextView.getText().toString(), this, TAG_START_DATE);
                break;
            case R.id.tv_end_date:
                showDatePicker(getString(R.string.end), mEndDateTextView.getText().toString(), this, TAG_END_DATE);
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        switch ((String)view.getTag()) {
            case TAG_START_DATE:
                mStartDateTextView.setText(String.format(Constants.API_DATE_FORMAT, year, monthOfYear, dayOfMonth));
                break;
            case TAG_END_DATE:
                mEndDateTextView.setText(String.format(Constants.API_DATE_FORMAT, year, monthOfYear, dayOfMonth));
                break;

        }
    }

    protected String getCheckedString() {
        return mProgressDoneCheckBox.isChecked() ? Constants.API_PUT_STATUS_DONE : Constants.API_PUT_STATUS_NOT_DONE;
    }

    protected String getEndDateString() {
        return mEndDateTextView.getText().toString();
    }

    protected String getStartDateString() {
        return mStartDateTextView.getText().toString();
    }

    //    protected void handleOKClick(){
//        this.dismiss();
//    }
//
//    protected void handleEndDateClick() {
//        DatePickerFragment datePickerFragment = createDateTimePicker();
//        getScreenManager().showDialogFragment(datePickerFragment, "");
//    }
//
//    protected void handleStartDateClick() {
//        DatePickerFragment datePickerFragment = createDateTimePicker();
//        getScreenManager().showDialogFragment(datePickerFragment, "");
//    }

//    private DatePickerFragment createDateTimePicker() {
//        final Calendar c = Calendar.getInstance();
//        int yy = c.get(Calendar.YEAR);
//        int mm = c.get(Calendar.MONTH);
//        int dd = c.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerFragment datePickerFragment = new DatePickerFragment();
//        datePickerFragment.setDate(yy, mm, dd);
//        return datePickerFragment;
//    }

//    protected MigrationParam2Fragment setTitle(String title) {
//        mTitle = title;
//        return this;
//    }
}
