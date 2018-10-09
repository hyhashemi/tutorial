package de.netalic.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class ISmsBroadcastReceiver extends BroadcastReceiver {


    ISmsInterface mISmsInterface;
    private static final String TAG = ISmsBroadcastReceiver.class.getSimpleName();

    public interface ISmsInterface {
        void onDone(String text);
    }

    public ISmsBroadcastReceiver() {

    }

    public ISmsBroadcastReceiver(ISmsInterface iSmsInterface) {
        mISmsInterface = iSmsInterface;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "HERE");
        Bundle myBundle = intent.getExtras();
        SmsMessage[] messages = null;
        String strMessage;

        if (myBundle != null) {
            Object[] pdus = (Object[]) myBundle.get("pdus");
            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (messages[i].getOriginatingAddress().equals("+9810004346")) {
                    strMessage = messages[i].getMessageBody();
                    String[] str = strMessage.split(":");
                    String[] str2 = str[1].split(",");
                    String substring = str2[0].substring(1, str2[0].length());
                    Log.e(TAG, String.valueOf(substring));
                    mISmsInterface.onDone(substring);
                }
            }
        }
    }
}
