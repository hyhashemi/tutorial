package de.netalic.myapplication.ui.registration;

public interface RegistrationContract {

    interface View{
        void notFound();
        void navigateToShowActivity();
    }

    interface Presenter{

        void request();
    }
}
