package de.netalic.myapplication.ui.phoneconfirm;

public interface PhoneConfirmContract {

    interface View{
        void navigateToShow();
        void saveToken(String token);
        void snackbarError();
    }
    interface Presenter{

    }
}
