package de.netalic.myapplication.ui.registration;

import java.util.List;

import de.netalic.myapplication.data.model.Speciality;

public interface RegistrationContract {

    interface View{
        void notFound();
        void navigateToShowActivity(List<Speciality> specialities);
    }

    interface Presenter{

        void request();
    }
}
