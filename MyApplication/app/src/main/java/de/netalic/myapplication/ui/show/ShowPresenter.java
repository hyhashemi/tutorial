package de.netalic.myapplication.ui.show;

import java.util.List;

import de.netalic.myapplication.data.model.Speciality;
import de.netalic.myapplication.data.remote.ApiInterface;

public class ShowPresenter implements ShowContract.Presenter {
    private ShowContract.View mShowFragment;
    public List<Speciality> mData;
    private ApiInterface mApiInterface;

    public ShowPresenter(ShowFragment showFragment) {
        this.mShowFragment = showFragment;
            }

    public List<Speciality> getData() {
        return mData;
    }
}


