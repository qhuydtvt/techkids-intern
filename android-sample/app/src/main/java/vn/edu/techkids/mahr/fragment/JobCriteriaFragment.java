package vn.edu.techkids.mahr.fragment;


import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.Expertise;
import vn.edu.techkids.mahr.enitity.JSONObjectDownloadTask;
import vn.edu.techkids.mahr.enitity.JSONObjectParser;
import vn.edu.techkids.mahr.enitity.JSONObjectPreDownloadHandler;
import vn.edu.techkids.mahr.enitity.JSONObjectPostDownloadHandler;
import vn.edu.techkids.mahr.enitity.JobCriteria;
import vn.edu.techkids.mahr.enitity.JobCriteriaListener;
import vn.edu.techkids.mahr.enitity.JobCriteriaViewModel;
import vn.edu.techkids.mahr.enitity.Worker;
import vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments.AgeEditFragment;
import vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments.AgencyEditFragment;
import vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments.ApprenticeshipEditFragment;
import vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments.DegreeEditFragment;
import vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments.ExperienceEditFragment;
import vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments.ExpertiseEditFragment;
import vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments.HeightEditFragment;
import vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments.LanguageEditFragment;
import vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments.WeightEditFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobCriteriaFragment extends BaseFragment implements
        AdapterView.OnItemClickListener, JSONObjectParser,
        JSONObjectPreDownloadHandler, JSONObjectPostDownloadHandler,View.OnClickListener {

    private Button floatingActionButton;
    private ListView mEmployeeProperitesListView;
    private JobCriteria mJobCriteria;

    private List<Expertise> expertiseArrayList;

    //private String[] mEmployeeProperites;

    private LayoutInflater mLayoutInflater;

    private ArrayList<JobCriteriaViewModel> mJobPropertyList;

    private final String DOWNLOAD_TAG_EXPERTISE = "download expertise";
    private final String DOWNLOAD_TAG_AGENCY = "download agency";

    private final String DOWNLOAD_TAG_WORKER = "worker";
    private boolean mDownloadExpertisePending = false;
    private boolean mDownloadAgencyPending = false;

    private ProgressDialog progress;

    public JobCriteriaFragment() {
        // Required empty public constructor
        mJobCriteria = JobCriteria.getInst();
        mJobPropertyList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vRet =  inflater.inflate(R.layout.fragment_job_criteria, container, false);
        initData();
        getIntances(vRet);
        setupView();

        return vRet;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getScreenManager().showActionBar();


    }

    @Override
    public void onStart() {

        String title = "";
        switch (JobCriteria.getInst().getNationality()) {
            case Constants.API_INDONESIA: title = getString(R.string.indonesia); break;
            case Constants.API_VIETNAM: title = getString(R.string.vietnam); break;
        }

        switch (JobCriteria.getInst().getMajor()) {
            case Constants.API_MAJOR_MALE_WORKER: title += " | " + getString(R.string.male_employee); break;
            case Constants.API_MAJOR_FEMALE_WORKER: title += " | " +getString(R.string.female_employee); break;
            case Constants.API_MAJOR_HOUSEMAID: title += " | " + getString(R.string.house_maid); break;
        }

        getScreenManager().setTitleOfActionBar(title);

        super.onStart();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;

    }
    private void getIntances(View vLayoutRoot) {
        mEmployeeProperitesListView = (ListView)vLayoutRoot.
                findViewById(R.id.ltvEmployeePropetiesList);
        mLayoutInflater = getActivity().getLayoutInflater();
        floatingActionButton = (Button) vLayoutRoot.findViewById(R.id.btnLoadWorker);
    }

    private void initData() {
        expertiseArrayList = JobCriteria.getInst().getExpertiseList();

        mJobPropertyList.clear();
        mJobPropertyList.add(new JobCriteriaViewModel(R.string.expertise, R.drawable.ic_build_black_24dp, JobCriteria.EXPERTISE));
        mJobPropertyList.add(new JobCriteriaViewModel(R.string.agency, R.drawable.ic_agency_24dp, JobCriteria.AGENCY));
        mJobPropertyList.add(new JobCriteriaViewModel(R.string.age, R.drawable.ic_person_black_24dp,JobCriteria.AGE));
        mJobPropertyList.add(new JobCriteriaViewModel(R.string.height, R.drawable.ic_height_24dp, JobCriteria.HEIGHT));
        mJobPropertyList.add(new JobCriteriaViewModel(R.string.weight, R.drawable.ic_view_module_black_24dp, JobCriteria.WEIGHT));
        mJobPropertyList.add(new JobCriteriaViewModel(R.string.language, R.drawable.ic_font_download_black_24dp, JobCriteria.LANG));
        mJobPropertyList.add(new JobCriteriaViewModel(R.string.degree, R.drawable.ic_group_work_black_24dp, JobCriteria.DEGREE));
        mJobPropertyList.add(new JobCriteriaViewModel(R.string.apprenticeship_or_not, R.drawable.ic_trending_up_black_24dp, JobCriteria.APPRENTICESHIP));
    }

    private void setupView() {
        JobCriteriaAdapter jobCriteriaAdapter = new JobCriteriaAdapter();
        mJobCriteria.setJobCriteriaListener(jobCriteriaAdapter);
        mEmployeeProperitesListView.setAdapter(jobCriteriaAdapter);
        mEmployeeProperitesListView.setOnItemClickListener(this);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public Object parse(String tag, InputStreamReader inputStreamReader) {
        Object object = null;
        switch (tag) {
            case DOWNLOAD_TAG_WORKER:
                object = Worker.loadJsonToList(inputStreamReader); break;
            case DOWNLOAD_TAG_EXPERTISE:
                object = JobCriteria.getInst().loadExpertiseFromJSON(inputStreamReader); break;
            case DOWNLOAD_TAG_AGENCY:
                object = JobCriteria.getInst().loadAgencyFromJSON(inputStreamReader); break;
        }
        return object;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoadWorker:
                try {
                    Log.d("APILoadWorker", JobCriteria.getInst().getAPIString());
                    JSONObjectDownloadTask downloadJSONTask = new JSONObjectDownloadTask(DOWNLOAD_TAG_WORKER,
                            this,
                            this,
                            this);
                    downloadJSONTask.execute(new URL(JobCriteria.getInst().getAPIString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private String getStringFromCriteria(int criteria) {
        switch (criteria) {
            case JobCriteria.EXPERTISE:
                return mJobCriteria.getExpertiseString();
            case JobCriteria.AGE:
                return mJobCriteria.getAgeRange();
            case JobCriteria.HEIGHT:
                String heightRangeString = mJobCriteria.getHeightRange();
                if(heightRangeString == null) {
                    return getString(R.string.default_type_filter);
                }
                return heightRangeString + String.format(" (%s)", Constants.HEIGHT_UNIT);
            case JobCriteria.WEIGHT:
                String weightRangeString = mJobCriteria.getWeightRange();
                if(weightRangeString == null) {
                    return getString(R.string.default_type_filter);
                }
                return weightRangeString + String.format(" (%s)", Constants.WEIGHT_UINT);
            case JobCriteria.LANG:
                List<Integer> idList = mJobCriteria.getLangIds();
                String ret = "";
                for (int id : idList) {
                    if(ret != "")
                        ret += " | ";
                    ret += getString(id);
                }
                return ret;
            case JobCriteria.EXP:
                return mJobCriteria.getExpRange();
            case JobCriteria.DEGREE:
                if (mJobCriteria.getDegree() == -1) return null;
                return getString(mJobCriteria.getDegree());
            case JobCriteria.APPRENTICESHIP:
                if(mJobCriteria.isApprenticeship()) {
                    return getString(R.string.yes);
                }
                return getString(R.string.no);
            case JobCriteria.AGENCY:
                return mJobCriteria.getAgencyString();

        }
        return null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("onItemClick", "onItemClick");
        JobCriteriaViewModel jobProperty = mJobPropertyList.get(position);
        DialogFragment dialogFragment = null;
        mDownloadExpertisePending = false;
        switch (jobProperty.getPropertyNameId()) {
            case R.string.expertise:
                if(JobCriteria.getInst().getExpertiseList() == null) {
                    try {
                        mDownloadExpertisePending = true;
                        new JSONObjectDownloadTask(DOWNLOAD_TAG_EXPERTISE, this, this, this).
                                execute(new URL(Constants.API_URL_EXPERTISE));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    dialogFragment = new ExpertiseEditFragment();
                }
                break;

            case R.string.agency:
                if(JobCriteria.getInst().getAgencyList() == null) {
                    try {
                        new JSONObjectDownloadTask(DOWNLOAD_TAG_AGENCY, this, this, this).
                                execute(new URL(Constants.API_URL_AGENCY));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    dialogFragment = new AgencyEditFragment();
                }
                break;
            case R.string.age:
                dialogFragment = new AgeEditFragment();
                break;
            case R.string.height:
                dialogFragment = new HeightEditFragment();
                break;
            case R.string.weight:
                dialogFragment = new WeightEditFragment();
                break;
            case R.string.language:
                dialogFragment = new LanguageEditFragment();
                break;
            case R.string.experience:
                dialogFragment = new ExperienceEditFragment();
                break;
            case R.string.degree:
                dialogFragment = new DegreeEditFragment();
                break;
            case R.string.apprenticeship_or_not:
                dialogFragment = new ApprenticeshipEditFragment();
            default:
                break;
        }

        if(dialogFragment != null) {
            getScreenManager().showDialogFragment(dialogFragment, "");
        }
    }

    @Override
    public void onPreDownload(String tag) {
        switch (tag) {
            case DOWNLOAD_TAG_WORKER:
                progress = ProgressDialog.show(this.getActivity(), getString(R.string.worker),
                    getString(R.string.loading), true);
                break;
            case DOWNLOAD_TAG_EXPERTISE:
                progress = ProgressDialog.show(this.getActivity(), getString(R.string.expertise),
                        getString(R.string.loading), true);
                break;
            case DOWNLOAD_TAG_AGENCY:
                progress = ProgressDialog.show(this.getActivity(), getString(R.string.agency),
                        getString(R.string.loading), true);
                break;
        }
    }

    @Override
    public void onPost(String tag, Object object) {
        switch (tag) {
            case DOWNLOAD_TAG_WORKER:
                if (progress != null) {
                    progress.dismiss();
                }
                /* Parse JSON to WorkerList */
                if (object == null) {
                    showToastMessage(getString(R.string.message_download_failed));
                } else {
                    getScreenManager().openFragment(new WorkerListFragment(), true);
                }

                break;
            case DOWNLOAD_TAG_EXPERTISE:
                if (progress != null) {
                    progress.dismiss();
                }
                if(object == null) {
                    showToastMessage(getString(R.string.message_download_expertise_failed));
                }
                else {
                    DialogFragment dialogFragment = new ExpertiseEditFragment();
                    getScreenManager().showDialogFragment(dialogFragment, "");
                }
                break;
            case DOWNLOAD_TAG_AGENCY:

                if (progress != null) {
                    progress.dismiss();
                }
                if(object == null) {
                    showToastMessage(getString(R.string.message_download_failed));
                }
                else {
                    DialogFragment dialogFragment = new AgencyEditFragment();
                    getScreenManager().showDialogFragment(dialogFragment, "");
                }
                break;
        }
    }

    private class JobCriteriaAdapter extends BaseAdapter implements JobCriteriaListener {

        @Override
        public int getCount() {
            return mJobPropertyList.size();
        }

        @Override
        public Object getItem(int position) {
            return mJobPropertyList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            JobCriteriaViewModel jobProperty = mJobPropertyList.get(position);
            if(convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.list_item_employee_property,
                        parent, false);
            }

            TextView txvEmployeeProperty = (TextView)convertView.findViewById (
                    R.id.txv_employee_property);
            ImageView imvJobProperty = (ImageView)convertView.findViewById(
                    R.id.imv_employee_property);
            TextView txtDetailForFilter = (TextView)convertView.findViewById(
                    R.id.txtFilerOfType);
            txvEmployeeProperty.setText(getString(jobProperty.getPropertyNameId()));
            imvJobProperty.setImageResource(jobProperty.getImageId());

                /*txtDetailForFilter.setText("Default");*/

            String selectedValue = getStringFromCriteria(jobProperty.getCriteria());
            if(selectedValue == null || selectedValue.isEmpty()) {
                txtDetailForFilter.setText(getString(R.string.default_type_filter));
            } else {
                txtDetailForFilter.setText(selectedValue);
            }

            return convertView;
        }

        @Override
        public void onJobCriteriaChange() {
            Log.d("onJobCriteriaChange","onJobCriteriaChange");
            notifyDataSetChanged();
        }
    }

}