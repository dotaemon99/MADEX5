package com.mad.exercise5;


import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * TrainAdapter class provides controller of recyclerview list
 */
public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ViewHolder> {

    private List<Train> mTrainList;
    private Context mContext;
    private final static String LATE = "Late";
    private final static String RED = "#b13032";
    private final static String GREEN = "#5f8133";

    /**
     * ViewHolder class for the reference of list
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mStatus, mDestination, mArrivalTime, mPlatformPlace, mDestinationTime;
        public View mTimeTable;
        public ProgressBar mSingleRefresh;
        public ViewHolder(View v) {
            super(v);
            mStatus = (TextView) v.findViewById(R.id.status);
            mDestination = (TextView) v.findViewById(R.id.destination);
            mArrivalTime = (TextView) v.findViewById(R.id.arrivalTime);
            mPlatformPlace = (TextView) v.findViewById(R.id.platformPlace);
            mDestinationTime = (TextView) v.findViewById(R.id.destinationTime);
            mTimeTable = v.findViewById(R.id.timeTable);
            mSingleRefresh = (ProgressBar) v.findViewById(R.id.singleRefresh);
        }
    }

    /**
     * constructor mTrain for adapter
     * @param context for getting task
     * @param trains for getting array of mTrain
     */
    public TrainAdapter(Context context, ArrayList<Train> trains){
        mContext = context;
        mTrainList = trains;
    }

    /**
     * gets amounts of data in mTrain arraylist
     * @return total data of mTrain
     */
    @Override
    public int getItemCount() {
        return mTrainList.size();
    }

    /**
     * for creation of the view with the layout item
     * @param parent get the mContext for parent
     * @param viewType get the view
     * @return viewholder item
     */
    @Override
    public TrainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.train_item, parent, false);

        return new ViewHolder(itemView);
    }

    /**
     * binds the view of the trainlist
     * @param holder for getting the view
     * @param position for getting current position
     */
    @Override
    public void onBindViewHolder(final TrainAdapter.ViewHolder holder, final int position) {
        final Train train = mTrainList.get(position);
        // check the text to determine the color
        boolean checkText = train.getStatus().equals(LATE);
        String color = checkText ? RED : GREEN;
        holder.mStatus.setTextColor(Color.parseColor(color));
        holder.mStatus.setText(train.getStatus());
        holder.mDestination.setText(train.getDestination());
        holder.mArrivalTime.setText(train.getArrivalTime() + mContext.getString(R.string.mins));
        holder.mPlatformPlace.setText(train.getPlatform() + mContext.getString(R.string.empty));
        holder.mDestinationTime.setText(train.getDestinationTime() + mContext.getString(R.string.empty));
        //click listener for the refresh in each list
        holder.mTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefreshSingleAsyncTask refreshSingle = new RefreshSingleAsyncTask(holder, train);
                refreshSingle.execute();
            }
        });
    }

    /**
     * RefreshSingleAsyncTask for refresh just one list for arrival time
     */
    private class RefreshSingleAsyncTask extends AsyncTask<Void, Void, Void>{

        private TrainAdapter.ViewHolder mHolder;
        private Train mTrain;

        /**
         * constructor provides data to the class
         * @param holder for getting current view
         * @param train for getting current mTrain
         */
        RefreshSingleAsyncTask(TrainAdapter.ViewHolder holder, Train train){
            mHolder = holder;
            mTrain = train;
        }

        /**
         * Displays before executing task
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mHolder.mSingleRefresh.setVisibility(View.VISIBLE);
            mHolder.mArrivalTime.setVisibility(View.GONE);
            Toast.makeText(mContext, mContext.getString(R.string.refreshing) + mContext.getString(R.string.space) + mHolder.mDestination.getText().toString(), Toast.LENGTH_LONG).show();
        }

        /**
         * displays after executing the task
         * @param aVoid for void parameter
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mHolder.mSingleRefresh.setVisibility(View.GONE);
            mHolder.mArrivalTime.setVisibility(View.VISIBLE);
            notifyDataSetChanged();
        }

        /**
         * processes all task in asynctask
         * @param params for object purpose
         * @return void for nothing
         */
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Random rand = new Random();
            mTrain.setArrivalTime(rand.nextInt(MainActivity.MAX_TIME) + MainActivity.MIN_TIME);
            return null;
        }
    }
}
