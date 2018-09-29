package de.netalic.myapplication.ui.show;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

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
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), mData);
        recyclerView.setAdapter(adapter);
        Toolbar toolbar = mRootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
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

        }
        return super.onOptionsItemSelected(item);
    }

    private void pushToDatabase() {
        MyDatabase db = new MyDatabase(getContext());
        db.deleteDatabase();
        for (int i = 0; i < mData.size(); i++){
            db.createRecords(mData.get(i).getId(), mData.get(i).getTitle());
        }
    }

}