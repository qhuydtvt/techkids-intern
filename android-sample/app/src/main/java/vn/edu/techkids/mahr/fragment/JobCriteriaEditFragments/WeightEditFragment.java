package vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments;

import android.view.View;

import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.JobCriteria;
import vn.edu.techkids.mahr.fragment.InRangeEditFragment;

/**
 * Created by qhuydtvt on 3/7/2016.
 */
public class WeightEditFragment extends InRangeEditFragment {

    @Override
    protected void initLayout(View view) {
        super.initLayout(view);
        setRange(Constants.MIN_WEIGHT, Constants.MAX_WEIGHT);
        setDefaultValues(Constants.MIN_WEIGHT, Constants.MAX_WEIGHT);
    }

    @Override
    public void onClick(View v) {
        JobCriteria.getInst().setWeightRange(mFromPicker.getValue(), mToPicker.getValue());
        super.onClick(v);
        /*JobCriteria.getInst().setMinWeight();
        JobCriteria.getInst().setMaxWeight();*/
    }
}

        /*extends DialogFragment implements View.OnClickListener {

    private NumberPicker mFromPicker;
    private NumberPicker mToPicker;
    private Button mOKButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_edit_weight, container, false);
        initLayout(view);
        return view;
    }



    *//*private void initLayout(View view) {
        mFromPicker = (NumberPicker)view.findViewById(R.id.npFrom);
        mToPicker = (NumberPicker)view.findViewById(R.id.npTo);
        mOKButton = (Button)view.findViewById(R.id.btnOK);

        mFromPicker.setMinValue(Constants.MIN_WEIGHT);
        mFromPicker.setMaxValue(Constants.MAX_WEIGHT);
        mToPicker.setMinValue(Constants.MIN_WEIGHT);
        mToPicker.setMaxValue(Constants.MAX_WEIGHT);

        mOKButton.setOnClickListener(this);
    }*//*

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().setTitle(getString(R.string.weight));
    }

    @Override
    public void onClick(View v) {
        this.dismiss();
    }
}*/
