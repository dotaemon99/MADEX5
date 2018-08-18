package com.mad.exercise5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Random;

/**
 * Mainactivity Class which provides controller of the screen
 */

public class MainActivity extends AppCompatActivity {

    public static final int MAX_TIME = 20;
    public static final int MIN_TIME = 1;
    private ArrayList<Train> mTrainList = new ArrayList<>();
    private RecyclerView mTrainRecyclerView;
    private TrainAdapter mAdapter;
    private ProgressBar mProgressActivity;
    private FloatingActionButton mFab;

    /**
     * displays when device is created
     * @param savedInstanceState for saving instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setReference();
        prepareTrainData();
    }

    /**
     * Sets reference of all components
     */
    private void setReference(){
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mTrainRecyclerView = (RecyclerView) findViewById(R.id.trainRecyclerView);
        mAdapter = new TrainAdapter(getApplicationContext(), mTrainList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mTrainRecyclerView.setLayoutManager(mLayoutManager);
        mTrainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTrainRecyclerView.setAdapter(mAdapter);
        mProgressActivity = (ProgressBar) findViewById(R.id.progressActivity);
    }

    /**
     * Adds train data for application
     */
    private void prepareTrainData() {
        Train train = new Train(getString(R.string.albionPlatform), 3, getString(R.string.onTime), getString(R.string.allawahDes), getString(R.string.firstTime));
        mTrainList.add(train);

        train = new Train(getString(R.string.ArncliffePlatform), 4, getString(R.string.late), getString(R.string.centralDes), getString(R.string.secondTime));
        mTrainList.add(train);

        train = new Train(getString(R.string.ArtarmionPlatform), 7, getString(R.string.onTime), getString(R.string.ahfieldDes), getString(R.string.thirdTime));
        mTrainList.add(train);

        train = new Train(getString(R.string.BerowraPlatform), 12, getString(R.string.late), getString(R.string.beverlyDes), getString(R.string.fourthTime));
        mTrainList.add(train);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Train train = new Train(getString(R.string.BerowraPlatform), 12, getString(R.string.late), getString(R.string.beverlyDes), getString(R.string.fourthTime));
                mTrainList.add(train);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * displays creation of menu
     * @param menu for displaying menubar
     * @return display of menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Determines all option clicks in menu
     * @param item for item in the menu
     * @return the item selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            mTrainList.clear();
            mAdapter.notifyDataSetChanged();
        } else if(id == R.id.action_refresh){
            RefreshAllAsyncTask refTask = new RefreshAllAsyncTask();
            refTask.execute();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * RefreshAllAsyncTask run all refresh task
     */
    private class RefreshAllAsyncTask extends AsyncTask<Object, Object, Void>
    {
        /**
         * Displays before executing task
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressActivity.setVisibility(View.VISIBLE);
            mTrainRecyclerView.setVisibility(View.GONE);
        }

        /**
         * displays after executing the task
         * @param s for void parameter
         */
        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            mProgressActivity.setVisibility(View.GONE);
            mTrainRecyclerView.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        }

        /**
         * processes all task in asynctask
         * @param params for object purpose
         * @return void for nothing
         */
        @Override
        protected Void doInBackground(Object... params) {
            // sleep for 3 seconds
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //provides random integer to all train arrival time
            Random rand = new Random();
            for(Train train : mTrainList){
                train.setArrivalTime(rand.nextInt(MAX_TIME) + MIN_TIME);
            }
            return null;
        }
    }

}
