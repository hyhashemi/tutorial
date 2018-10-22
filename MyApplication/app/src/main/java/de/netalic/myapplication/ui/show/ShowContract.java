package de.netalic.myapplication.ui.show;

import de.netalic.myapplication.data.database.MyDatabase;

public interface ShowContract {

    interface View{
    }

    interface Presenter{
        void pushToDataBase(MyDatabase db);
    }
}
