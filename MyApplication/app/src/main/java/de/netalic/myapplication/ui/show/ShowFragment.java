package de.netalic.myapplication.ui.show;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.database.MyDatabase;
import de.netalic.myapplication.data.model.Speciality;

public class ShowFragment extends Fragment implements ShowContract.View {

    private View mRootView;
    private ShowContract.Presenter mShowPresenter;
    private static final String DATA = "data";
    private List<Speciality> mData;
    private int mPositionClicked;
    private int mCounter = 34;
    private AlertDialog.Builder mBuilderEdit;
    private AlertDialog.Builder mBuilderAdd;
    private EditText mAlertEdit;
    private EditText mAlertAdd;
    private RecyclerAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_show, null);
        mShowPresenter = new ShowPresenter(this);
        setHasOptionsMenu(true);
        return mRootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mData = getArguments().getParcelableArrayList(DATA);
        RecyclerView recyclerView = mRootView.findViewById(R.id.recyclerView_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new RecyclerAdapter(getContext(), mData);
        recyclerView.setAdapter(mAdapter);

        Toolbar toolbar = mRootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Specialities");

        mBuilderEdit = new AlertDialog.Builder(getContext());
        mBuilderAdd = new AlertDialog.Builder(getContext());

        mAlertEdit = new EditText(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        mAlertEdit.setLayoutParams(lp);

        mAlertAdd = new EditText(getContext());
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        mAlertAdd.setLayoutParams(lp2);
    }

    public static ShowFragment newInstance(List<Speciality> specialities) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(DATA, (ArrayList<? extends Parcelable>) specialities);
        ShowFragment fragment = new ShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.push:
                pushToDatabase();
                break;

            case R.id.add: {
                addAlert();
                break;
            }
            case R.id.edit: {
                mPositionClicked = mAdapter.getSelectedItem();
                if (mPositionClicked != -1){
                    Log.e("edit:", String.valueOf(mPositionClicked));
                    editAlert();
                }
                break;
            }
            case R.id.delete: {
                mPositionClicked = mAdapter.getSelectedItem();
                if (mPositionClicked != -1) {
                    mData.remove(mPositionClicked);
                    mAdapter.notifyDataSetChanged();
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void editAlert() {
        Log.e("edit:", String.valueOf(mPositionClicked));
        mBuilderEdit.setTitle(R.string.show_edittitle).setView(mAlertEdit).setMessage(R.string.show_editalert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("edit:", String.valueOf(mPositionClicked));
                        mData.get(mPositionClicked).setTitle(mAlertEdit.getText().toString());
                        ((ViewGroup) mAlertEdit.getParent()).removeView(mAlertEdit);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        mAdapter.notifyDataSetChanged();
    }

    private void addAlert() {
        mBuilderAdd.setTitle(R.string.show_addtitle).setView(mAlertAdd).setMessage(R.string.show_addalert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mData.add(new Speciality(mCounter, mAlertAdd.getText().toString()));
                        mAdapter.notifyDataSetChanged();
                        ((ViewGroup) mAlertAdd.getParent()).removeView(mAlertAdd);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        mCounter++;
    }

    private void pushToDatabase() {
        MyDatabase db = new MyDatabase(getContext());
        db.deleteDatabase();
        for (int i = 0; i < mData.size(); i++) {
            db.createRecords(mData.get(i).getId(), mData.get(i).getTitle());
        }
    }

}