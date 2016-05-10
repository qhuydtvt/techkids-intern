package vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.enitity.JobCriteria;

/**
 * Created by qhuydtvt on 3/7/2016.
 */
public class DegreeEditFragment extends DialogFragment implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private final int[] mItems = new int[]{
            R.string.primary_school,
            R.string.secondary_school,
            R.string.high_school,
            R.string.vocational,
            R.string.college,
            R.string.university

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_degree, container, false);
        initLayout(view);
        return view;
    }

    private void initLayout(View view) {
        mListView = (ListView)view.findViewById(R.id.ltv);

        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mItems.length;
            }

            @Override
            public Object getItem(int position) {
                return mItems[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                int titleId = mItems[position];
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(
                            R.layout.list_item_simple, parent, false);
                }
                TextView txvExpertise = (TextView) convertView.findViewById(R.id.txvTitle);
                txvExpertise.setText(getString(titleId));
                return convertView;
            }
        });

        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.degree));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JobCriteria.getInst().setDegree(mItems[position]);
        this.dismiss();
    }
}
