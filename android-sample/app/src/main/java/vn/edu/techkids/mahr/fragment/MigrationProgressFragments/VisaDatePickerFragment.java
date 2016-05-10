package vn.edu.techkids.mahr.fragment.MigrationProgressFragments;

import android.net.Uri;

import vn.edu.techkids.mahr.enitity.MigrationProgress;
import vn.edu.techkids.mahr.fragment.MigrationParam2Fragment;

/**
 * Created by qhuydtvt on 3/17/2016.
 */
public class VisaDatePickerFragment extends MigrationParam2Fragment {

    @Override
    protected void fillData() {
        mStartDateTextView.setText( getDateString(MigrationProgress.getInst().getVisaStartDate()));
        mEndDateTextView.setText(getDateString(MigrationProgress.getInst().getVisaEndDate()));
        mProgressDoneCheckBox.setChecked(MigrationProgress.getInst().getVisaStatus() != 0);
    }

    @Override
    public String buildQuery() {
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_VISA_STATUS, getCheckedString())
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_VISA_END_DATE, getEndDateString())
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_VISA_START_DATE, getStartDateString());
        String query = builder.build().getEncodedQuery();
        return query;
    }
}
