package vn.edu.techkids.mahr.fragment;

/*import android.support.app.Fragment;*/
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.JobCriteria;


/**
 * A placeholder fragment containing a simple view.
 */


public class MajorSelectionFragment extends BaseFragment implements OnClickListener{

    public MajorSelectionFragment() {

    }

    @Override
    public void onStart() {
        super.onStart();


        getScreenManager().showActionBar();
        /*getScreenManager().setTitleOfActionBar(getString(R.string.listJobMainName));*/
        String title = "";
        switch (JobCriteria.getInst().getNationality()) {
            case Constants.API_INDONESIA: title = getString(R.string.indonesia); break;
            case Constants.API_VIETNAM: title = getString(R.string.vietnam); break;
        }
        getScreenManager().setTitleOfActionBar(getString(R.string.action_bar_major_name));


        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.trans_left_in,R.anim.trans_left_out);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_list_jobs, container, false);
        this.addListener(view);
        return view;
    }



    private void addListener(View view){
        Button btnMaleEmployee = (Button) view.findViewById(R.id.btnMaleEmployee);
        Button btnFemaleEmployee = (Button) view.findViewById(R.id.btnFemaleEmployee);
        Button btnHousemaid = (Button) view.findViewById(R.id.btnHousemaid);

        btnMaleEmployee.setOnClickListener(this);
        btnFemaleEmployee.setOnClickListener(this);
        btnHousemaid.setOnClickListener(this);

        /*btnMaleEmployee.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getScreenManager().openFragment(new JobCriteriaFragment(), true);
            }
        });
        btnFemaleEmployee.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getScreenManager().openFragment(new JobCriteriaFragment(), true);
            }
        });
        btnHousemaid.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getScreenManager().openFragment(new JobCriteriaFragment(), true);
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        String major = null;
        switch (v.getId()) {
            case R.id.btnMaleEmployee:
                major = Constants.API_MAJOR_MALE_WORKER;
                break;
            case R.id.btnFemaleEmployee:
                major = Constants.API_MAJOR_FEMALE_WORKER;
                break;
            case R.id.btnHousemaid:
                major = Constants.API_MAJOR_HOUSEMAID;
                break;
        }
        JobCriteria.getInst().setMajor(major);
        getScreenManager().openFragment(new JobCriteriaFragment(), true);
    }
}
