package net.is.ps.addameer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Administrator on 11/24/2016.
 */

public class GCMInstanceIDListener extends InstanceIDListenerService {
    SharedPreferences preferences;
    SharedPreferences.Editor prefEditor;

    @Override
    public void onTokenRefresh() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        prefEditor = preferences.edit();
        prefEditor.putBoolean("token_sent", false).apply();
        startService(new Intent(this, RegistrationService.class));

    }
}
