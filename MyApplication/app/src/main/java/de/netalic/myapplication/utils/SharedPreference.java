package de.netalic.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreference {

    private Context context;

    public SharedPreference(Context applicationContext) {
        this.context = applicationContext;
    }

    public void setToken(String token){
        getPreferences().edit().
                putString("token", token).
                apply();
    }

    public String getToken(){
        return getPreferences().getString("token", "default");
    }

    public void setPhoneNumber(String token){
        getPreferences().edit().
                putString(token, token).
                apply();
    }

    public String getPhoneNumber(String phoneNumber){
        return getPreferences().getString(phoneNumber, "default");
    }

    public void delete(String deletingItem){
        getPreferences().edit().remove(deletingItem);
    }

    private SharedPreferences getPreferences() {
        return context.getSharedPreferences("user_prefs", MODE_PRIVATE);
    }
}
