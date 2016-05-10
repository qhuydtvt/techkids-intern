package vn.edu.techkids.mahr.fragment.MigrationProgressFragments;

import android.net.Uri;

import vn.edu.techkids.mahr.enitity.MigrationProgress;
import vn.edu.techkids.mahr.fragment.MigrationParam1Fragment;

/**
 * Created by qhuydtvt on 3/17/2016.
 */
public class FlightDatePickerFragment extends MigrationParam1Fragment {

    @Override
    protected void fillData() {
        mCheckBoxDone.setChecked(MigrationProgress.getInst().getFlightStatus() != 0);
        mEndDateTextView.setText(getDateString(MigrationProgress.getInst().getFlightEndDate()));
    }

    @Override
    public String buildQuery() {
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_FLIGHT_STATUS,
                        getCheckedString())
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_FLIGHT_END_DATE,
                        getEndDateString());
        String query = builder.build().getEncodedQuery();
        return query;
    }
}
