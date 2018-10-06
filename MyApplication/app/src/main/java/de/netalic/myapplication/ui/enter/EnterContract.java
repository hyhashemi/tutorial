package de.netalic.myapplication.ui.enter;

import java.util.List;

import de.netalic.myapplication.data.model.Speciality;

public interface EnterContract {

    interface View{
        void notFound();
        void navigateToShowActivity(List<Speciality> specialities);
    }

    interface Presenter{

        void request();
    }
}
