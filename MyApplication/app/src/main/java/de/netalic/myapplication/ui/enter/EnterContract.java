package de.netalic.myapplication.ui.enter;

import java.util.List;

import de.netalic.myapplication.data.model.Speciality;
import de.netalic.myapplication.data.model.Wallet;

public interface EnterContract {

    interface View{
        void navigateToShowActivity(List<Speciality> body);
        void navigateToWalletActivity(List<Wallet> body);
        void navigateToMapActivity();
        void snackbarError();
    }

    interface Presenter{
        void showRequest();
        void walletRequest();
    }
}
