package vn.edu.techkids.mahr.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.enitity.Worker;
import vn.edu.techkids.mahr.fragment.WorkerListFragment.OnListFragmentInteractionListener;

import java.io.InputStream;
import java.util.List;

public class WorkerRecyclerViewAdapter extends RecyclerView.Adapter<WorkerRecyclerViewAdapter.ViewHolder> {

    /*private final List<Person> mValues;*/
    private final OnListFragmentInteractionListener mListener;
    private List<Worker> mWorkerArrayList;

    public WorkerRecyclerViewAdapter(/*List<Person> items, */OnListFragmentInteractionListener listener) {
        /*mValues = items;*/
        mListener = listener;
        mWorkerArrayList = Worker.getWorkerList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_worker, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Worker worker = mWorkerArrayList.get(position);

        holder.txtName.setText(String.valueOf(worker.getId()));
        holder.txtAge.setText(String.valueOf(worker.getAge()));
        holder.txtHeight.setText(" - " + String.valueOf(worker.getHeight()) + "cm");
        holder.txtWeight.setText(" - " + String.valueOf(worker.getWeight()) + "kg");

        int imgStatusId = -1;
        switch (worker.getStatus()) {
            case Worker.STATUS_FREE:
                imgStatusId = R.mipmap.free;
                break;
            case Worker.STATUS_WAITING:
                imgStatusId = R.mipmap.waiting;
                break;
            case Worker.STATUS_CONFIRM:
                imgStatusId = R.mipmap.confirm;
                break;
        }

        Log.d("onBindViewHolder", String.format("Status = %d", worker.getStatus()));

        if(imgStatusId != -1)
            holder.imgViewStatus.setImageResource(imgStatusId);

        new DownloadImageTask(holder.imgViewPersonPhoto).execute(worker.getAgetAvatarWithFixedSize());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click", "Click");
                if (null != mListener) {
                    Log.d("Click", "null != mListener");
                    mListener.onListFragmentInteraction(worker);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWorkerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView txtName;
        TextView txtAge;
        TextView txtHeight;
        TextView txtWeight;
        ImageView imgViewPersonPhoto;
        ImageView imgViewStatus;

        public ViewHolder(View view) {
            super(view);

            cv = (CardView)view.findViewById(R.id.cv);
            txtName = (TextView) view.findViewById(R.id.person_name);

            txtAge = (TextView) view.findViewById(R.id.person_age);
            txtHeight = (TextView) view.findViewById(R.id.person_height);
            txtWeight = (TextView) view.findViewById(R.id.person_weight);
            imgViewPersonPhoto = (ImageView) view.findViewById(R.id.person_photo);
            imgViewStatus = (ImageView)view.findViewById(R.id.person_status);

            /*view.setOnClickListener(this);*/
        }

        /*@Override
        public void onClick(View v) {

        }*/
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
