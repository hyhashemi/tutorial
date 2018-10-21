package de.netalic.myapplication.ui.wallet;

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
import de.netalic.myapplication.data.model.Wallet;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class WalletFragment extends Fragment implements WalletContract.View{

    private View mRootView;
    private WalletPresenter mWalletPresenter;
    private List<Wallet> mData;
    private WalletRecyclerAdapter mWalletRecyclerAdapter;
    private int mPositionClicked;
    private AlertDialog.Builder mBuilderEdit;
    private AlertDialog.Builder mBuilderAdd;
    private EditText mAlertEditName;
    private EditText mAlertEditBalance;
    private EditText mAlertAddName;
    private EditText mAlertAddBalance;
    private LinearLayout addAlertLayout;
    private LinearLayout editAlertLayout;
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.wallet_fragment_layout, container, false );
        mWalletPresenter = new WalletPresenter(this);
        setHasOptionsMenu(true);
        Realm.init(getContext());
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = mRootView.findViewById(R.id.wallet_recyclerView_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mData = getArguments().getParcelableArrayList("data");
        mWalletRecyclerAdapter = new WalletRecyclerAdapter(getContext(), mData);
        recyclerView.setAdapter(mWalletRecyclerAdapter);
        Toolbar toolbar = mRootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle(R.string.wallet_title);
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (int i=0; i < mData.size(); i++){
            realm.copyToRealm(mData.get(i));
        }
        realm.commitTransaction();
        mBuilderEdit = new AlertDialog.Builder(getContext());
        mBuilderAdd = new AlertDialog.Builder(getContext());
    }

    public static WalletFragment newInstance(List<Wallet> data) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) data);
        WalletFragment fragment = new WalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu_wallet, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add: {
                addAlert();
                break;
            }

            case R.id.edit: {
                mPositionClicked = mWalletRecyclerAdapter.getSelectedItem();
                editAlert();
                break;
            }
            case R.id.delete: {
                mPositionClicked = mWalletRecyclerAdapter.getSelectedItem();
                if (mPositionClicked != -1) {
                    delete(mPositionClicked);
                }
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void editAlert() {
        editAlertLayout = new LinearLayout(getContext());
        editAlertLayout.setOrientation(LinearLayout.VERTICAL);
        mAlertEditName = new EditText(getContext());
        mAlertEditName.setHint("name");
        mAlertEditBalance = new EditText(getContext());
        mAlertEditBalance.setHint("balance");
        editAlertLayout.addView(mAlertEditName);
        editAlertLayout.addView(mAlertEditBalance);

        mBuilderEdit.setTitle(R.string.show_edittitle).setView(editAlertLayout).setMessage(R.string.show_editalert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mData.get(mPositionClicked).setName(mAlertEditName.getText().toString());
                        mData.get(mPositionClicked).setBalance(Integer.valueOf(mAlertEditBalance.getText().toString()));
                        realm.beginTransaction();
                        realm.insertOrUpdate(mData.get(mPositionClicked));
                        realm.commitTransaction();
                        mWalletRecyclerAdapter.notifyDataSetChanged();
                        editAlertLayout.removeAllViews();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void delete(int mPositionClicked) {
        RealmQuery<Wallet> query = realm.where(Wallet.class);
        RealmResults<Wallet> skuItems1 = query.findAll();
        query.equalTo("mName", mData.get(mPositionClicked).getName());
        realm.beginTransaction();
        skuItems1.deleteAllFromRealm();
        realm.commitTransaction();
        mData.remove(mPositionClicked);
        mWalletRecyclerAdapter.notifyDataSetChanged();
    }


    private void addAlert() {

        addAlertLayout = new LinearLayout(getContext());
        addAlertLayout.setOrientation(LinearLayout.VERTICAL);
        mAlertAddName = new EditText(getContext());
        mAlertAddName.setHint("name");
        mAlertAddBalance = new EditText(getContext());
        mAlertAddBalance.setHint("balance");
        addAlertLayout.addView(mAlertAddName);
        addAlertLayout.addView(mAlertAddBalance);

        mBuilderAdd.setTitle(R.string.show_addtitle).setView(addAlertLayout).setMessage(R.string.show_addalert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Wallet wallet = new Wallet();
                        wallet.setName(mAlertAddName.getText().toString());
                        wallet.setBalance(Integer.valueOf(mAlertAddBalance.getText().toString()));
                        realm.beginTransaction();
                        realm.insertOrUpdate(wallet);
                        realm.commitTransaction();
                        mData.add(wallet);
                        mWalletRecyclerAdapter.notifyDataSetChanged();
                        addAlertLayout.removeAllViews();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
