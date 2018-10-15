package de.netalic.myapplication.ui.show;

import java.util.List;

import de.netalic.myapplication.data.model.Speciality;

public interface ShowContract {

    interface View{
    }

    interface Presenter{
        List<Speciality> getData();

    }
}
