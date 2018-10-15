package de.netalic.myapplication.ui.wallet;

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
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import de.netalic.myapplication.R;
import de.netalic.myapplication.data.model.Wallet;

public class WalletFragment extends Fragment implements WalletContract.View{

    private View mRootView;
    private WalletPresenter mWalletPresenter;
    private List<Wallet> mData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.wallet_fragment_layout, container, false );
        mWalletPresenter = new WalletPresenter(this);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = mRootView.findViewById(R.id.wallet_recyclerView_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mData = getArguments().getParcelableArrayList("data");
        WalletRecyclerAdapter walletRecyclerAdapter = new WalletRecyclerAdapter(getContext(), mData);
        recyclerView.setAdapter(walletRecyclerAdapter);
        Toolbar toolbar = mRootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(R.string.wallet_title);
    }

    public static WalletFragment newInstance(List<Wallet> data) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) data);
        WalletFragment fragment = new WalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
