package vn.edu.techkids.mahr.fragment.JobCriteriaEditFragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.enitity.JobCriteria;
import vn.edu.techkids.mahr.enitity.Lang;

/**
 * Created by qhuydtvt on 3/7/2016.
 */
public class LanguageEditFragment extends DialogFragment implements View.OnClickListener {

    private ListView mListView;
    private Button mBtnOK;
    private Lang[] langs;

    /*private final int[] mItems = new int[]{
            R.string.indonesia,
            R.string.vietnam,
            R.string.taiwan
    };*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_language, container, false);
        initData();
        initLayout(view);
        return view;
    }

    private void initData() {
        langs = JobCriteria.getInst().getLangs();
    }

    private void initLayout(View view) {
        mListView = (ListView)view.findViewById(R.id.ltv);
        mBtnOK = (Button)view.findViewById(R.id.btnOK);

        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return langs.length;
            }

            @Override
            public Object getItem(int position) {
                return langs[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Lang lang = langs[position];
                if (convertView == null) {
                    convertView = getActivity().getLayoutInflater().inflate(
                            R.layout.list_item_multi_choice, parent, false);
                }


                TextView txvExpertise = (TextView) convertView.findViewById(R.id.txvTitle);
                CheckBox chbSelect = (CheckBox)convertView.findViewById(R.id.chbSelect);
                chbSelect.setChecked(lang.isSelected());
                chbSelect.setTag(lang);
                txvExpertise.setText(getString(lang.getid()));

                chbSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Lang refLang = (Lang)v.getTag();
                        refLang.setSelected(((CheckBox)v).isChecked());
                    }
                });


                return convertView;
            }
        });
        mBtnOK.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.language));
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        JobCriteria.getInst().notifyListener();
        this.dismiss();
    }

    /*@Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        JobCriteria.getInst().setLanguage(mItems[position]);
        this.dismiss();
    }*/
}
