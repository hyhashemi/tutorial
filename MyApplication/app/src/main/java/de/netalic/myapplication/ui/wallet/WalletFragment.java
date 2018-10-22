package de.netalic.myapplication.ui.wallet;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import de.netalic.myapplication.ui.Base.BaseFragment;

public class WalletFragment extends BaseFragment implements WalletContract.View{

    private View mRootView;
    private WalletPresenter mWalletPresenter;
    public List<Wallet> mData;
    private WalletRecyclerAdapter mWalletRecyclerAdapter;
    private int mPositionClicked;
    private AlertDialog.Builder mBuilderEdit;
    private AlertDialog.Builder mBuilderAdd;
    private EditText mAlertEditName;
    private EditText mAlertEditBalance;
    private EditText mAlertAddName;
    private EditText mAlertAddBalance;
    private LinearLayout mAddAlertLayout;
    private LinearLayout editAlertLayout;
    private final static String DATA="data";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.wallet_fragment_layout, container, false );
        mData = getArguments().getParcelableArrayList(DATA);
        mWalletPresenter = new WalletPresenter(this);
        setHasOptionsMenu(true);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUi();
        mWalletPresenter.writeToRealm();
    }

    @Override
    public void initUi() {
        RecyclerView recyclerView = mRootView.findViewById(R.id.wallet_recyclerView_container);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mWalletRecyclerAdapter = new WalletRecyclerAdapter(getContext(), mData);
        recyclerView.setAdapter(mWalletRecyclerAdapter);

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

        mBuilderEdit.setTitle(R.string.wallet_title).setView(editAlertLayout).setMessage(R.string.wallet_editalert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try{
                            mData.get(mPositionClicked).setName(mAlertEditName.getText().toString());
                            mData.get(mPositionClicked).setBalance(Integer.valueOf(mAlertEditBalance.getText().toString()));
                            mWalletPresenter.editRealm(mPositionClicked);
                            mWalletRecyclerAdapter.notifyDataSetChanged();
                        }
                        catch (Exception e){
                            balanceSnackbarError();
                        }
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
        mWalletPresenter.deleteFromRealm(mPositionClicked);
        mData.remove(mPositionClicked);
        mWalletRecyclerAdapter.notifyDataSetChanged();
    }


    private void addAlert() {

        mAddAlertLayout = new LinearLayout(getContext());
        mAddAlertLayout.setOrientation(LinearLayout.VERTICAL);
        mAlertAddName = new EditText(getContext());
        mAlertAddName.setHint("name");
        mAlertAddBalance = new EditText(getContext());
        mAlertAddBalance.setHint("balance");
        mAddAlertLayout.addView(mAlertAddName);
        mAddAlertLayout.addView(mAlertAddBalance);

        mBuilderAdd.setTitle(R.string.wallet_addtitle).setView(mAddAlertLayout).setMessage(R.string.wallet_addalert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Wallet wallet = new Wallet();
                        try{
                            wallet.setBalance(Integer.valueOf(mAlertAddBalance.getText().toString()));
                            wallet.setName(mAlertAddName.getText().toString());
                            mWalletPresenter.addToRealm(wallet);
                            mData.add(wallet);
                            mWalletRecyclerAdapter.notifyDataSetChanged();
                        }
                        catch (Exception e){
                            balanceSnackbarError();
                        }
                        mAddAlertLayout.removeAllViews();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void balanceSnackbarError() {
        Snackbar snackbar = Snackbar.make(mRootView, R.string.wallet_balance_snackvarerror, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void nameSnackbarError() {
        Snackbar snackbar = Snackbar.make(mRootView, R.string.wallet_name_snackbarerror, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

}
