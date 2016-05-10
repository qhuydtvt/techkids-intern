package vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.enitity.JobCriteria;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApprenticeshipEditFragment extends DialogFragment {

    private CheckBox mApprenticeshipCheckBox;
    private Button mOKButton;

    public ApprenticeshipEditFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_apprenticeship_edit, container, false);
        mApprenticeshipCheckBox = (CheckBox)view.findViewById(R.id.chb_check);
        mOKButton = (Button)view.findViewById(R.id.btn_OK);

        mApprenticeshipCheckBox.setChecked(JobCriteria.getInst().isApprenticeship());

        mApprenticeshipCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobCriteria.getInst().setApprenticeship(((CheckBox) v).isChecked() ? 1 : 0);
            }
        });

        mOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }

}
