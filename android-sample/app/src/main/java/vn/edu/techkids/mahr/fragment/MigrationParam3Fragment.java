package vn.edu.techkids.mahr.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class MigrationParam3Fragment extends MigrationParamFragment implements
        View.OnClickListener {

    protected Button mOKButton;

    protected CheckBox mCheckBoxDone;

    public MigrationParam3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_migration_param3, container, false);
        initLayout(view);
        fillData();
        return view;
    }

    protected void initLayout(View view) {
        mOKButton = (Button)view.findViewById(R.id.btn_OK);
        mCheckBoxDone = (CheckBox)view.findViewById(R.id.chb_progress_done);
        mOKButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        callPutAPI();
    }

    protected String getCheckedString(){
        return mCheckBoxDone.isChecked() ? Constants.API_PUT_STATUS_DONE : Constants.API_PUT_STATUS_NOT_DONE;
    }
}
