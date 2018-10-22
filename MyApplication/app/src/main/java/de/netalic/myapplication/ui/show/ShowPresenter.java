package de.netalic.myapplication.ui.show;

import java.util.List;

import de.netalic.myapplication.data.database.MyDatabase;
import de.netalic.myapplication.data.model.Speciality;

public class ShowPresenter implements ShowContract.Presenter {
    private ShowContract.View mShowFragment;
    public List<Speciality> mData;

    public ShowPresenter(ShowFragment showFragment) {
        this.mShowFragment = showFragment;
        }

    public void pushToDataBase(MyDatabase db){
        db.deleteDatabase();
        for (int i = 0; i < mData.size(); i++) {
            db.createRecords(mData.get(i).getId(), mData.get(i).getTitle());
        }
    }
}


