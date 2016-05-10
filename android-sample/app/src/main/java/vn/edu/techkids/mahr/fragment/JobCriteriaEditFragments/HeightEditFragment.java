package vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments;

import android.view.View;

import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.JobCriteria;
import vn.edu.techkids.mahr.fragment.InRangeEditFragment;

/**
 * Created by qhuydtvt on 3/7/2016.
 */
public class HeightEditFragment extends InRangeEditFragment {

    @Override
    protected void initLayout(View view) {
        super.initLayout(view);
        setRange(Constants.MIN_HEIGHT, Constants.MAX_HEIGHT);
        setDefaultValues(Constants.MIN_HEIGHT, Constants.MAX_HEIGHT);
    }

    @Override
    public void onClick(View v) {
        JobCriteria.getInst().setHeightRange(mFromPicker.getValue(), mToPicker.getValue());
        super.onClick(v);
    }
}
