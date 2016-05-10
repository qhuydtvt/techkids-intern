package vn.edu.techkids.mahr.activity;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.adapter.AgencyRecyclerViewAdapter;
import vn.edu.techkids.mahr.enitity.Agency;
import vn.edu.techkids.mahr.enitity.Cloud;
import vn.edu.techkids.mahr.fragment.BaseDialogFragment;
import vn.edu.techkids.mahr.fragment.NationalitySelectionFragment;
import vn.edu.techkids.mahr.fragment.ScreenManager;

public class MainActivity extends AppCompatActivity implements ScreenManager, AgencyRecyclerViewAdapter.OnAgencyListFragmentInteractionListener {

    private FragmentManager mFragmentManager;
    private Menu menu;
    private MenuItem mShareMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.AppTheme_NoActionBar);
        this.setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#29B6F6")));

        /*hideActionBar();*/

        getIntances();

//        Worker worker = new Worker();
//        worker.setId(0);
//        worker.setExcel_path("");
//

        openFragment(new NationalitySelectionFragment(), true);

        BaseDialogFragment.setScreenManager(this);
        //openFragment(new WorkerDetailFragment(), true);
    }

    private void getIntances() {
        mFragmentManager = getFragmentManager();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() != R.id.action_share){
            onBackPressed();
            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        } else if (item.getItemId() == R.id.action_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Cloud.getInstance().getUrlLink());
            startActivity(Intent.createChooser(sharingIntent, Cloud.getInstance().getUrlLink()));
            //openFragment(new WorkerListFragment(), true);
            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
        }
        return true;
    }

    @Override

    /**
     * Open new screen
     * @param fragment
     * @param addToBackStack
     */
    public void openFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out);
        fragmentTransaction.replace(R.id.fl_job_list, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }

        fragmentTransaction.commit();
    }

    @Override
    public boolean back() {
        if (mFragmentManager.getBackStackEntryCount() > 1) {
            mFragmentManager.popBackStack();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void showActionBar() {
        getSupportActionBar().show();
    }

    @Override
    public void hideDisplayHomeButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void showDisplayHomeButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void hideActionBar() {
        getSupportActionBar().hide();
    }

    @Override
    public void setActionBarColor(String color) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(color)));
    }


    @Override
    public void showDialogFragment(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(mFragmentManager, tag);
    }

    @Override
    public void setTitleOfActionBar(String titles) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View view = getLayoutInflater().inflate(R.layout.action_bar_home, null);
        TextView title = (TextView) view.findViewById(R.id.ma_action_bar_title);
        actionBar.setCustomView(view,
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        Gravity.CENTER
                )
        );

        title.setText(titles);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public void showShareButtonOnRightActionBar() {
//        MenuItem item = (MenuItem)this.menu.findItem(R.id.action_share);
//        item.setVisible(true);

        mShareMenuItem.setVisible(true);
        this.invalidateOptionsMenu();
    }

    @Override
    public void hidenButtonActionBar() {

        MenuItem itemShare = (MenuItem) menu.findItem(R.id.action_share);
        itemShare.setVisible(false);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list_jobs, menu);
        this.menu = menu;
        mShareMenuItem = (MenuItem) menu.findItem(R.id.action_share);
        mShareMenuItem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if(!back())
            super.onBackPressed();
    }

    @Override
    public void onListFragmentInteraction(Agency item) {

    }
}
