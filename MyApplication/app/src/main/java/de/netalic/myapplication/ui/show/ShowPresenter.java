package de.netalic.myapplication.ui.show;

public class ShowPresenter implements ShowContract.Presenter{
    private ShowContract.View mShowFragment;

    public ShowPresenter(ShowFragment showFragment) {
        this.mShowFragment = showFragment;
    }
}
