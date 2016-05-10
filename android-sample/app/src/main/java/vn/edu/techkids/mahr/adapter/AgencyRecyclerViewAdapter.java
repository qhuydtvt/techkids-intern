package vn.edu.techkids.mahr.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.enitity.Agency;

import java.util.ArrayList;
import java.util.List;

public class AgencyRecyclerViewAdapter extends RecyclerView.Adapter<AgencyRecyclerViewAdapter.ViewHolder> {

    private final List<Agency> mValues;
    private final OnAgencyListFragmentInteractionListener mListener;
    private List<ViewHolder> viewHolderList;

    public AgencyRecyclerViewAdapter(List<Agency> items, OnAgencyListFragmentInteractionListener listener) {
        viewHolderList = new ArrayList<>();
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_agency, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolderList.add(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameTextView;
        public final RadioButton mCheckRadioButton;
        public Agency mItem;

        public ViewHolder(final View view) {
            super(view);
            mView = view;
            mNameTextView = (TextView) view.findViewById(R.id.tv_agency_title);

            mCheckRadioButton = (RadioButton) view.findViewById(R.id.rb_agency_select);
            mCheckRadioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((RadioButton) v).isChecked()) {
                        for (ViewHolder viewHolder : viewHolderList) {
                            viewHolder.mItem.setSelected(false);
                            viewHolder.mCheckRadioButton.setChecked(false);
                        }
                        ((RadioButton) v).setChecked(true);
                        mItem.setSelected(true);
                    }
                }
            });
        }

        public void setData(Agency item) {
            mItem = item;

            mNameTextView.setText(mItem.getChineseName());
            mCheckRadioButton.setChecked(mItem.getSelected());

            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        mListener.onListFragmentInteraction(mItem);
                    }
                }
            });
        }


        @Override
        public String toString() {
            return super.toString() + " '" + mCheckRadioButton.getText() + "'";
        }
    }

    public interface OnAgencyListFragmentInteractionListener {
        void onListFragmentInteraction(Agency item);
    }
}
