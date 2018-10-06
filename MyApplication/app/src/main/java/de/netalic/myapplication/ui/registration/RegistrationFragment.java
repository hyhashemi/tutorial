package de.netalic.myapplication.ui.registration;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import de.netalic.myapplication.R;
import de.netalic.myapplication.ui.phoneconfirm.PhoneConfirmActivity;
import de.netalic.myapplication.ui.phoneconfirm.PhoneConfirmFragment;

public class RegistrationFragment extends Fragment implements RegistrationContract.View {

    public View mRootView;
    public RegistrationPresenter mRegistrationPresenter;
    public String mPhoneNumber;
    public EditText mEditText;
    public String mActivationCode;
    public String mToken;
    public Button mButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.registration_fragment_layout, null);
        mRegistrationPresenter = new RegistrationPresenter(this);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mEditText = mRootView.findViewById(R.id.phone_number);
        mPhoneNumber = mEditText.getText().toString();
        mButton = mRootView.findViewById(R.id.button_registration_send);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegistrationPresenter.claimRequest(mEditText.getText().toString());
                //refreshSmsInbox();
            }
        });

    }

    public void getActivationCode(String activationCode){
        this.mActivationCode = activationCode;
    }

    public void getToken(String token){
        this.mToken = token;
    }


    public void navigateToPhoneConfirm(){
        Intent confirm = new Intent(getContext(), PhoneConfirmActivity.class);
        confirm.putExtra("code", mActivationCode );
        startActivity(confirm);
    }

    public void refreshSmsInbox() {
        StringBuilder smsBuilder = new StringBuilder();
        final String SMS_URI_INBOX = "content://sms/inbox";
        final String SMS_URI_ALL = "content://sms/";
        try {
            Uri uri = Uri.parse(SMS_URI_INBOX);
            String[] projection = new String[] { "_id", "address", "person", "body", "date", "type" };
            Cursor cur = getActivity().getContentResolver().query(uri, projection, "address=''", null, "date desc");
            if (cur.moveToFirst()) {
                int index_Address = cur.getColumnIndex("address");
                int index_Person = cur.getColumnIndex("person");
                int index_Body = cur.getColumnIndex("body");
                int index_Date = cur.getColumnIndex("date");
                int index_Type = cur.getColumnIndex("type");
                do {
                    String strAddress = cur.getString(index_Address);
                    int intPerson = cur.getInt(index_Person);
                    mActivationCode = cur.getString(index_Body);
                    long longDate = cur.getLong(index_Date);
                    int int_Type = cur.getInt(index_Type);

                    smsBuilder.append("[ ");
                    smsBuilder.append(strAddress + ", ");
                    smsBuilder.append(intPerson + ", ");
                    smsBuilder.append(mActivationCode + ", ");
                    smsBuilder.append(longDate + ", ");
                    smsBuilder.append(int_Type);
                    smsBuilder.append(" ]\n\n");
                } while (cur.moveToNext());

                if (!cur.isClosed()) {
                    cur.close();
                    cur = null;
                }
            } else {
                smsBuilder.append("no result!");
            } // end if
        } catch (SQLiteException ex) {
        Log.d("SQLiteException", ex.getMessage());
        }

    }
}
