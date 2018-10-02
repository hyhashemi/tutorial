package de.netalic.myapplication.ui.show;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
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
import android.view.MotionEvent;
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
    private int counter = 34;
    private View mViewClicked;
    private Menu mMenu;
    private AlertDialog.Builder mBuilder;
    private AlertDialog.Builder mBuilder2;
    private EditText input;
    private EditText input2;
    RecyclerAdapter adapter;

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
        this.mMenu = menu;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mData = getArguments().getParcelableArrayList(DATA);
        RecyclerView recyclerView = mRootView.findViewById(R.id.recyclerView_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        int row_index = -1;
        adapter = new RecyclerAdapter(getContext(), mData);
        adapter.setClickListener(new RecyclerAdapter.ItemClickListener() {
                                     @Override
                                     public void onItemClick(View view, int position) {
                                         mPositionClicked = position;
                                         mViewClicked = view;
                                     }
                                 });
        recyclerView.setAdapter(adapter);
        Toolbar toolbar = mRootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        mBuilder = new AlertDialog.Builder(getContext());
        mBuilder2 = new AlertDialog.Builder(getContext());

        input = new EditText(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);

        input2 = new EditText(getContext());
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input2.setLayoutParams(lp2);
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
                editAlart();
                break;
            }
            case R.id.delete: {
                if (mPositionClicked != -1){
                mData.remove(mPositionClicked);
                adapter.notifyDataSetChanged();
                mPositionClicked = -1;
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void editAlart() {

        mBuilder.setTitle("Edit this speciality:").setView(input).setMessage("Are you sure you want to edit this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mData.get(mPositionClicked).setTitle(input.getText().toString());
                        ((ViewGroup)input.getParent()).removeView(input);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        adapter.notifyDataSetChanged();
    }

    private void addAlert(){
        mBuilder2.setTitle("Add this speciality:").setView(input2).setMessage("Are you sure you want to add this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mData.add(new Speciality(counter, input2.getText().toString()));
                        adapter.notifyDataSetChanged();
                        ((ViewGroup)input2.getParent()).removeView(input2);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        counter++;
    }

    private void pushToDatabase() {
        MyDatabase db = new MyDatabase(getContext());
        db.deleteDatabase();
        for (int i = 0; i < mData.size(); i++){
            db.createRecords(mData.get(i).getId(), mData.get(i).getTitle());
        }
    }

}