package vn.edu.techkids.mahr.fragment.MigrationProgressFragments;

import android.net.Uri;

import vn.edu.techkids.mahr.enitity.MigrationProgress;
import vn.edu.techkids.mahr.fragment.MigrationParam1Fragment;

/**
 * Created by qhuydtvt on 3/17/2016.
 */
public class PassportPickerFragment extends MigrationParam1Fragment {

    @Override
    protected void fillData() {
        mCheckBoxDone.setChecked(MigrationProgress.getInst().getPassportStatus() != 0);
        mEndDateTextView.setText(getDateString(MigrationProgress.getInst().getPassportEndDate()));
    }

    @Override
    public String buildQuery() {
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_PASSPORT_STATUS,
                        getCheckedString())
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_PASSPORT_END_DATE,
                        getEndDateString());
        String query = builder.build().getEncodedQuery();
        return query;
    }

//    @Override
//    protected void initLayout(View view) {
//        super.initLayout(view);
//        mCheckBoxDone.setChecked(MigrationProgress.getInst().getPassportStatus() != 0);
//    }
//
//    @Override
//    protected void callPutAPI() {
//        JSONObjectPutTask putTask = new JSONObjectPutTask(this, this);
//        try {
//            putTask.execute(new URL(
//                    String.format(Constants.API_URL_PROGRESSES_PUT_FORMAT,
//                    MigrationProgress.getInst().getProfileId())));
//        } catch (MalformedURLException e) {
//        }
//    }
//
//    @Override
//    public String buildQuery() {
//        Uri.Builder builder = new Uri.Builder()
//                .appendQueryParameter(MigrationProgress.API_MIG_PROG_PASSPORT_STATUS, getCheckedString())
//                .appendQueryParameter(MigrationProgress.API_MIG_PROG_PASSPORT_END_DATE, getEndDateString());
//        return builder.build().getEncodedQuery();
//    }
//
//    @Override
//    public Object parse(String tag, InputStreamReader inputStreamReader) throws IOException {
//        MigrationProgress migrationProgress = (new Gson()).fromJson(inputStreamReader,
//                MigrationProgress.class);
//        if(migrationProgress != null){
//            this.dismiss();
//        }
//        return migrationProgress;
//    }
//
//    @Override
//    public void onDataChange(Object object) {
//
//    }
}
