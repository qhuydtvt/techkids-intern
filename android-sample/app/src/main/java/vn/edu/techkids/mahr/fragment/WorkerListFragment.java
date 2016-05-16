package vn.edu.techkids.mahr.fragment;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.adapter.WorkerRecyclerViewAdapter;
import vn.edu.techkids.mahr.constants.Constants;
import vn.edu.techkids.mahr.enitity.JobCriteria;
import vn.edu.techkids.mahr.enitity.Worker;

/**
 * A fragment representing a list of Items.
 * <p />
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WorkerListFragment extends BaseFragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    private OnListFragmentInteractionListener mListener;

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WorkerListFragment newInstance(int columnCount) {
        WorkerListFragment fragment = new WorkerListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WorkerListFragment() {

        mListener = new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Worker item) {
                int workerStatus = item.getStatus();
                if(workerStatus == Worker.STATUS_WAITING || workerStatus == Worker.STATUS_CONFIRM) {
                    showToastMessage(getString(R.string.not_allowed));
                } else {
                    WorkerDetailFragment fragment = new WorkerDetailFragment();
                    fragment.setWorker(item);
                    getScreenManager().openFragment(fragment, true);
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out);

        //getScreenManager().setTitleOfActionBar(getString(R.string.list_employee));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_worker_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
            recyclerView.setHasFixedSize(true);
            gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(gaggeredGridLayoutManager);
            recyclerView.setAdapter(new WorkerRecyclerViewAdapter(mListener));
        }
        return view;

    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {

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

        super.onViewStateRestored(savedInstanceState);
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnAgencyListFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Worker item);
    }
}
