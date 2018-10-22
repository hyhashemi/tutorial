package de.netalic.myapplication.ui.wallet;

import java.util.List;

import de.netalic.myapplication.data.model.Wallet;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class WalletPresenter implements WalletContract.Presenter {

    private WalletFragment mWalletFragment;
    private Realm mRealm;
    private List<Wallet> mData;

    public WalletPresenter(WalletFragment walletFragment){
        this.mWalletFragment = walletFragment;
        this.mData = mWalletFragment.mData;

    }

    public void writeToRealm(){
        Realm.init(mWalletFragment.getContext());
        mRealm = Realm.getDefaultInstance();
        mRealm.beginTransaction();
        for (int i=0; i < mData.size(); i++){
            mRealm.copyToRealm(mData.get(i));
        }
        mRealm.commitTransaction();
    }

    public void deleteFromRealm(int positionClicked){
        RealmQuery<Wallet> query = mRealm.where(Wallet.class);
        RealmResults<Wallet> skuItems1 = query.findAll();
        query.equalTo("mName", mData.get(positionClicked).getName());
        mRealm.beginTransaction();
        skuItems1.deleteAllFromRealm();
        mRealm.commitTransaction();
    }


    public void addToRealm(Wallet wallet) {
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(wallet);
        mRealm.commitTransaction();
    }

    public void editRealm(int positionClicked){
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(mData.get(positionClicked));
        mRealm.commitTransaction();
    }
}
