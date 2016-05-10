package vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.adapter.AgencyRecyclerViewAdapter;
import vn.edu.techkids.mahr.enitity.JobCriteria;

public class AgencyEditFragment extends DialogFragment {

    // TODO: Customize parameters
    private int mColumnCount = 2;

    private AgencyRecyclerViewAdapter.OnAgencyListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AgencyEditFragment() {
//        mListener = new AgencyRecyclerViewAdapter.OnAgencyListFragmentInteractionListener() {
//            @Override
//            public void onListFragmentInteraction(Agency item) {
//
//            }
//        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_agency, container, false);

        View agencyListView = view.findViewById(R.id.list);
        Button mOkButton = (Button)view.findViewById(R.id.btnOK);

        // Set the adapter
        if (agencyListView instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) agencyListView;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setAdapter(new AgencyRecyclerViewAdapter(JobCriteria.getInst().getAgencyList(),
                    mListener));
        }

        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobCriteria.getInst().notifyListener();
                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.agency));
        super.onViewCreated(view, savedInstanceState);
    }
}
