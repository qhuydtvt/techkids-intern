package vn.edu.techkids.mahr.fragment.MigrationProgressFragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.HttpQueryBuilder;
import vn.edu.techkids.mahr.enitity.JSONObjectPostDownloadHandler;
import vn.edu.techkids.mahr.enitity.JSONObjectPutTask;
import vn.edu.techkids.mahr.enitity.MigrationProgresJSONParser;
import vn.edu.techkids.mahr.enitity.MigrationProgress;
import vn.edu.techkids.mahr.fragment.BaseDialogFragment;
import vn.edu.techkids.mahr.fragment.BaseFragment;
import vn.edu.techkids.mahr.fragment.MigrationParamFragment;

/**
 * Created by hungtran on 3/18/16.
 */
public class EditProfilesFragment extends MigrationParamFragment
        implements View.OnClickListener {
    String mTitle;
    TextView txtDepartment;
    TextView txtCompany;
    TextView txtFactory;
    Button btnOK;

    public EditProfilesFragment() {

    }

    @Override
    protected void fillData() {
        MigrationProgress migrationProgress = MigrationProgress.getInst();
        txtCompany.setText(migrationProgress.getCompany());
        txtDepartment.setText(migrationProgress.getDepartment());
        txtFactory.setText(migrationProgress.getFactory());
    }

    private void connectView(View view){
        txtDepartment = (TextView) view.findViewById(R.id.txt_department);
        txtCompany = (TextView) view.findViewById(R.id.txt_company);
        txtFactory = (TextView) view.findViewById(R.id.txt_factory);
        btnOK = (Button) view.findViewById(R.id.btn_fragment_profile_edit_OK);


        btnOK.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_migration_change_profiles, container, false);
        if(mTitle != null) {
            getDialog().setTitle(mTitle);
        }
        connectView(view);
        fillData();
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fragment_profile_edit_OK:
                callPutAPI();
                break;
        }
    }


    @Override
    public String buildQuery() {
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_COMPANY,
                        txtCompany.getText().toString())
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_DEPARTMENT,
                        txtDepartment.getText().toString())
                .appendQueryParameter(MigrationProgress.API_MIG_PROG_FACTORY,
                        txtFactory.getText().toString());
        String query = builder.build().getEncodedQuery();
        return query;
    }

}
