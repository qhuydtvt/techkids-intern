package vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments;

import android.view.View;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.JobCriteria;
import vn.edu.techkids.mahr.fragment.InRangeEditFragment;

/**
 * Created by qhuydtvt on 3/7/2016.
 */
public class AgeEditFragment extends InRangeEditFragment {
    @Override
    protected void initLayout(View view) {
        super.initLayout(view);
        setRange(Constants.MIN_AGE, Constants.MAX_AGE);
        int minAge = JobCriteria.getInst().getMinAge();
        int maxAge = JobCriteria.getInst().getMaxAge();

        if(minAge != -1 && maxAge != -1) {
            setDefaultValues(JobCriteria.getInst().getMinAge(),
                    JobCriteria.getInst().getMaxAge());
        }
        else {
            setDefaultValues(Constants.MIN_AGE, Constants.MAX_AGE);
        }
        setTitle(getString(R.string.age));
    }

    @Override
    public void onClick(View v) {
        JobCriteria.getInst().setAgeRange(mFromPicker.getValue(), mToPicker.getValue());
        super.onClick(v);
    }
}
