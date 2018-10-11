package de.netalic.myapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class ISmsBroadcastReceiver extends BroadcastReceiver {


    ISmsInterface mISmsInterface;

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

        Bundle myBundle = intent.getExtras();
        SmsMessage[] messages = null;
        String stringMessage;

        if (myBundle != null) {
            Object[] pdus = (Object[]) myBundle.get("pdus");
            messages = new SmsMessage[pdus.length];

            for (int i = 0; i < messages.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                if (messages[i].getOriginatingAddress().equals("+9810004346")) {
                    stringMessage = messages[i].getMessageBody();
                    String[] splitedOnce = stringMessage.split(":");
                    String[] splitedTwice = splitedOnce[1].split(",");
                    String substring = splitedTwice[0].substring(1, splitedTwice[0].length());
                    mISmsInterface.onDone(substring);
                }
            }
        }
    }
}
