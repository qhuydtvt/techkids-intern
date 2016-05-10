package vn.edu.techkids.mahr.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.JobCriteria;

/**
 * Created by qhuydtvt on 3/7/2016.
 */
public class InRangeEditFragment extends DialogFragment implements View.OnClickListener {

    protected NumberPicker mFromPicker;
    protected NumberPicker mToPicker;
    private Button mOKButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_edit_in_range, container, false);
        initLayout(view);
        return view;
    }

    protected void initLayout(View view) {
        mFromPicker = (NumberPicker)view.findViewById(R.id.npFrom);
        mToPicker = (NumberPicker)view.findViewById(R.id.npTo);
        mOKButton = (Button)view.findViewById(R.id.btnOK);

        mOKButton.setOnClickListener(this);
    }

    protected void setRange(int min, int max){
        mFromPicker.setMinValue(min);
        mFromPicker.setMaxValue(max);
        mToPicker.setMinValue(min);
        mToPicker.setMaxValue(max);
    }

    protected void setDefaultValues(int min, int max) {
        mFromPicker.setValue(min);
        mToPicker.setValue(max);
    }

    protected  void setTitle(String title) {
        getDialog().setTitle(title);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle(getString(R.string.experience));
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }
}
