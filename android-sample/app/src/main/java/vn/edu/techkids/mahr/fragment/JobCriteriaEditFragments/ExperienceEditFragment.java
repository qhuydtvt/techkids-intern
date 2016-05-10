package vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments;

import android.view.View;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.JobCriteria;
import vn.edu.techkids.mahr.fragment.InRangeEditFragment;

/**
 * Created by qhuydtvt on 3/7/2016.
 */
public class ExperienceEditFragment extends InRangeEditFragment {

    @Override
    protected void initLayout(View view) {
        super.initLayout(view);
        setRange(Constants.MIN_XP, Constants.MAX_XP);
        setDefaultValues(Constants.MIN_XP, Constants.MAX_XP);
        setTitle(getString(R.string.experience));
    }

    @Override
    public void onClick(View v) {
        JobCriteria.getInst().setExperienceRange(mFromPicker.getValue(), mToPicker.getValue());
        super.onClick(v);
    }
}
