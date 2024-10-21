package com.dungnt.intent_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Object[] smsExtras = (Object[]) extras.get("pdus");
            if (smsExtras != null) {
                for (Object obj : smsExtras) {
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) obj);
                    String address = sms.getOriginatingAddress();
                    String body = sms.getMessageBody();

                    String message = "Có 1 tin nhắn từ " + address + " với nội dung: " + body;
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
