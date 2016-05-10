package vn.edu.techkids.mahr;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import io.fabric.sdk.android.Fabric;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

//import vn.edu.techkids.mahr.enitity.DownloadJSONTask;
//import vn.edu.techkids.mahr.constants.Constants;
//import vn.edu.techkids.mahr.enitity.ExpertiseJSONPostDownloadHandler;
//import vn.edu.techkids.mahr.enitity.JSONPostDownloadHandler;

/**
 * Created by qhuydtvt on 3/9/2016.
 */
public class MAHARApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
    }

}
