package vn.edu.techkids.mahr.fragment;

import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.io.InputStream;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.enitity.DataChangeListener;
import vn.edu.techkids.mahr.enitity.MigrationProgress;
import vn.edu.techkids.mahr.fragment.MigrationProgressFragments.EditProfilesFragment;
import vn.edu.techkids.mahr.fragment.MigrationProgressFragments.FileDatePickerFragment;
import vn.edu.techkids.mahr.fragment.MigrationProgressFragments.FinishDatePickerFragment;
import vn.edu.techkids.mahr.fragment.MigrationProgressFragments.FlightDatePickerFragment;
import vn.edu.techkids.mahr.fragment.MigrationProgressFragments.HealthDatePickerFragment;
import vn.edu.techkids.mahr.fragment.MigrationProgressFragments.LegalDatePickerFragment;
import vn.edu.techkids.mahr.fragment.MigrationProgressFragments.OfficeDatePickerFragment;
import vn.edu.techkids.mahr.fragment.MigrationProgressFragments.PassportPickerFragment;
import vn.edu.techkids.mahr.fragment.MigrationProgressFragments.VisaDatePickerFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MigrationProcessFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MigrationProcessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MigrationProcessFragment extends BaseFragment
        implements View.OnClickListener, DataChangeListener {

    CardView cardPassport;
    CardView cardLegal;
    CardView cardHeath;
    CardView cardFile;
    CardView cardOffice;
    CardView cardVisa;
    CardView cardFlight;
    CardView cardFinish;
    ImageView avatar;

    CheckBox imageDone;
    CheckBox imageDoneLegal;
    CheckBox imageDoneHealth;
    CheckBox imageDoneFile;
    CheckBox imageDoneTrinhCuc;
    CheckBox imageDoneVisa;
    CheckBox imageDoneDuKienBay;
    CheckBox imageDoneKetThuc;

    TextView txtS5Name;
    TextView txtS5ID;
    TextView txtS5Factory;
    TextView txtS5Department;
    TextView txtS5Company;

    TextView textViewPassport;
    TextView textViewLegal;
    TextView textViewHealth;
    TextView textViewFile;
    TextView textViewOffice;
    TextView textViewVisa;
    TextView textViewFlight;
    TextView textViewFinish;

    TextView textViewTimePassport;
    TextView textViewLegalEndDate;
    TextView textViewHealthEndDate;
    TextView textViewTimeFileEndDate;
    TextView textViewTimeOfficeEndDate;
    TextView textViewTimeVisa;
    TextView textViewTimeFlight;

    ImageButton buttonEdit;

    private MigrationProgress mMigrationProgress;
//
//    public void setMigrationProgress(MigrationProgress migrationProgress) {
//        this.mMigrationProgress = migrationProgress;
//    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private void initLayout(View view){
        avatar = (ImageView) view.findViewById(R.id.imageViewScreen5);
        buttonEdit = (ImageButton) view.findViewById(R.id.buttonEdit);

        cardPassport = (CardView) view.findViewById(R.id.cardPassport);
        cardLegal = (CardView) view.findViewById(R.id.cardLegal);
        cardHeath = (CardView) view.findViewById(R.id.cardHealth);
        cardFile = (CardView) view.findViewById(R.id.cardFile);
        cardOffice = (CardView) view.findViewById(R.id.cardOffice);
        cardVisa = (CardView) view.findViewById(R.id.cardVisa);
        cardFlight = (CardView) view.findViewById(R.id.cardFlight);
        cardFinish = (CardView) view.findViewById(R.id.cardFinish);

        imageDone = (CheckBox) view.findViewById(R.id.imageDone);
        imageDoneLegal = (CheckBox) view.findViewById(R.id.imageDoneLegal);
        imageDoneHealth = (CheckBox) view.findViewById(R.id.imageDoneHealth);
        imageDoneFile = (CheckBox) view.findViewById(R.id.imageDoneFile);
        imageDoneTrinhCuc = (CheckBox) view.findViewById(R.id.imageDoneOffice);
        imageDoneVisa = (CheckBox) view.findViewById(R.id.imageDoneVisa);
        imageDoneDuKienBay = (CheckBox) view.findViewById(R.id.imageDoneFlight);
        imageDoneKetThuc = (CheckBox) view.findViewById(R.id.imageDoneFinish);

        txtS5Name = (TextView) view.findViewById(R.id.txtS5Name);
        txtS5ID = (TextView) view.findViewById(R.id.txtS5ID);
        txtS5Factory = (TextView) view.findViewById(R.id.txtS5Bone);
        txtS5Department = (TextView) view.findViewById(R.id.txtS5Branch);
        txtS5Company = (TextView) view.findViewById(R.id.txtS5Company);

        textViewPassport = (TextView) view.findViewById(R.id.textViewPassport);
        textViewLegal = (TextView) view.findViewById(R.id.textViewLegal);
        textViewHealth = (TextView) view.findViewById(R.id.textViewHealth);
        textViewFile = (TextView) view.findViewById(R.id.textViewFile);
        textViewOffice = (TextView) view.findViewById(R.id.textViewOffice);
        textViewVisa = (TextView) view.findViewById(R.id.textViewVisa);
        textViewFlight = (TextView) view.findViewById(R.id.textViewFlight);
        textViewFinish = (TextView) view.findViewById(R.id.textViewFinish);


        textViewTimePassport = (TextView) view.findViewById(R.id.textViewPassportEndDate);
        textViewLegalEndDate = (TextView) view.findViewById(R.id.textViewLegalEndDate);
        textViewHealthEndDate = (TextView) view.findViewById(R.id.textViewHealthEndDate);
        textViewTimeFileEndDate = (TextView) view.findViewById(R.id.textViewTimeFileEndDate);
        textViewTimeOfficeEndDate = (TextView) view.findViewById(R.id.textViewTimeOfficeEndDate);
        textViewTimeVisa= (TextView) view.findViewById(R.id.textViewTimeVisa);
        textViewTimeFlight = (TextView) view.findViewById(R.id.textViewTimeFlight);
    }

    private void setListeners() {
        cardPassport.setOnClickListener(this);
        cardLegal.setOnClickListener(this);
        cardHeath.setOnClickListener(this);
        cardFile.setOnClickListener(this);
        cardOffice.setOnClickListener(this);
        cardVisa.setOnClickListener(this);
        cardFlight.setOnClickListener(this);
        cardFinish.setOnClickListener(this);

        MigrationProgress.setDataChangeListener(this);
    }

    private void connectData() {
        mMigrationProgress = MigrationProgress.getInst();

        new DownloadImageTask(avatar).execute(mMigrationProgress.getProfile().getAvatar());

        txtS5Name.setText(getString(R.string.name) + ": " + mMigrationProgress.getProfile().getName());
        txtS5ID.setText(getString(R.string.number_id) + ": " + String.valueOf(mMigrationProgress.getProfileId()));
        txtS5Factory.setText(getString(R.string.factory) + ": " + mMigrationProgress.getFactory());
        txtS5Department.setText(getString(R.string.department) + ": " + mMigrationProgress.getDepartment());
        txtS5Company.setText(getString(R.string.vietnam_company) + ": " +mMigrationProgress.getCompany());

        textViewTimePassport.setText(getString(R.string.finish) + " " + mMigrationProgress.getPassportEndDate());
        textViewLegalEndDate.setText(getString(R.string.finish) + " " + mMigrationProgress.getLegalEndDate());
        textViewHealthEndDate.setText(getString(R.string.finish) + " " + mMigrationProgress.getHealthEndDate());
        textViewTimeFileEndDate.setText(getString(R.string.finish) + " " + mMigrationProgress.getFileEndDate());
        textViewTimeOfficeEndDate.setText(getString(R.string.office_start) + " " + mMigrationProgress.getOfficeStartDate()
                + " - " + getString(R.string.office_end) + " " + mMigrationProgress.getOfficeEndDate());
        textViewTimeFlight.setText(getString(R.string.flight) + " " + mMigrationProgress.getFlightEndDate());
        textViewTimeVisa.setText(getString(R.string.visa_start) + " " + mMigrationProgress.getVisaStartDate()
                + " - " + getString(R.string.office_end) + " " + mMigrationProgress.getVisaEndDate());
        textViewFlight.setText(getString(R.string.flight));
        textViewFinish.setText(getString(R.string.end));

        textViewPassport.setText(getString(R.string.passport));
        textViewLegal.setText(getString(R.string.legal));
        textViewHealth.setText(getString(R.string.health));
        textViewFile.setText(getString(R.string.file));
        textViewOffice.setText(getString(R.string.work_department));
        textViewVisa.setText(getString(R.string.visa));


        imageDone.setChecked(intToBoolean(mMigrationProgress.getPassportStatus()));
        imageDoneLegal.setChecked(intToBoolean(mMigrationProgress.getLegalStatus()));
        imageDoneHealth.setChecked(intToBoolean(mMigrationProgress.getHealthStatus()));
        imageDoneFile.setChecked(intToBoolean(mMigrationProgress.getFileStatus()));
        imageDoneTrinhCuc.setChecked(intToBoolean(mMigrationProgress.getOfficeStatus()));
        imageDoneVisa.setChecked(intToBoolean(mMigrationProgress.getVisaStatus()));
        imageDoneDuKienBay.setChecked(intToBoolean(mMigrationProgress.getFlightStatus()));
        imageDoneKetThuc.setChecked(intToBoolean(mMigrationProgress.getFinish()));

        buttonEdit.setOnClickListener(this);
    }

    private boolean intToBoolean(int input){
        return input > 0 ? true : false;
    }

    private OnFragmentInteractionListener mListener;

    public MigrationProcessFragment() {
        // Required empty public constructor
    }

    public static MigrationProcessFragment newInstance(String param1, String param2) {
        MigrationProcessFragment fragment = new MigrationProcessFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_migration_process, container, false);
        initLayout(view);
        connectData();
        setListeners();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        DialogFragment dialogFragment = null;
        switch (v.getId()) {
            case R.id.buttonEdit:
                dialogFragment = new EditProfilesFragment().
                        setTitle(getString(R.string.edit_profile));
                break;
            case R.id.cardPassport:
                dialogFragment = new PassportPickerFragment().
                        setTitle(getString(R.string.passport));
                break;
            case R.id.cardLegal:
                dialogFragment = new LegalDatePickerFragment().
                        setTitle(getString(R.string.legal));
                break;
            case R.id.cardHealth:
                dialogFragment = new HealthDatePickerFragment().
                        setTitle(getString(R.string.health));
                break;
            case R.id.cardFile:
                dialogFragment = new FileDatePickerFragment().
                        setTitle(getString(R.string.file));
                break;
            case R.id.cardOffice:
                dialogFragment = new OfficeDatePickerFragment().
                        setTitle(getString(R.string.office));
                break;
            case R.id.cardVisa:
                dialogFragment = new VisaDatePickerFragment().
                        setTitle(getString(R.string.visa));
                break;
            case R.id.cardFlight:
                dialogFragment = new FlightDatePickerFragment().
                        setTitle(getString(R.string.flight));
                break;
            case R.id.cardFinish:
                dialogFragment = new FinishDatePickerFragment().
                        setTitle(getString(R.string.finish));
                break;
        }

        if(dialogFragment != null) {
            getScreenManager().showDialogFragment(dialogFragment, "");
        }
    }

    @Override
    public void onDataChange(Object object) {
//        updateStatuses(object);
        connectData();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
