package vn.edu.techkids.mahr.fragment.MigrationProgressFragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.enitity.MigrationProgress;
import vn.edu.techkids.mahr.fragment.MigrationParam1Fragment;
import vn.edu.techkids.mahr.fragment.MigrationParam3Fragment;

/**
 * Created by qhuydtvt on 3/17/2016.
 */
public class FinishDatePickerFragment extends MigrationParam3Fragment {

    @Override
    protected void fillData() {
        mCheckBoxDone.setChecked(MigrationProgress.getInst().getFinish() != 0);
//        mEndDateTextView.setText(MigrationProgress.getInst().getFi());

        /* TODO Remove if nce*/
    }

    @Override
    public String buildQuery() {
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_FINISH,
                        getCheckedString());
//                .appendQueryParameter(MigrationProgress.API_MIG_PROG_FILE_END_DATE,
//                        getEndDateString());
        String query = builder.build().getEncodedQuery();
        return query;
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_migration_param3, container, false);
//        initLayout(view);
//        fillData();
//        return view;
////        return super.onCreateView(inflater, container, savedInstanceState);
//    }
}
