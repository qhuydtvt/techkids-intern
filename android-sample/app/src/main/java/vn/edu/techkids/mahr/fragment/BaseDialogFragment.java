package vn.edu.techkids.mahr.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qhuydtvt on 3/17/2016.
 */
public class BaseDialogFragment extends DialogFragment{

    protected static ScreenManager mScreenManager;

    protected String mTitle;

    public BaseDialogFragment setTitle(String title) {
        mTitle = title;
        return this;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mTitle != null) {
            getDialog().setTitle(mTitle);
        }
    }

    public static void setScreenManager(ScreenManager screenManager) {
        mScreenManager = screenManager;
    }


    protected static ScreenManager getScreenManager() {
        return mScreenManager;
    }
}
