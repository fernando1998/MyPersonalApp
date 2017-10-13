package com.cruz.mypersonalapp.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.util.Log;

import com.cruz.mypersonalapp.R;

/**
 * Created by FERNANDO on 12/10/2017.
 */

public class MyPreferencesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment implements
            SharedPreferences.OnSharedPreferenceChangeListener
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Log.d("settings", "preference changed: " + s);

            if("username".equals(s)){
                Log.d("settings", "new value for username: " + sharedPreferences.getString(s, null));
                String newusername = sharedPreferences.getString(s, null);
                editor.putString("username", newusername);
                editor.commit();
                Log.d("valor nuevo", newusername);
            }else if("password".equals(s)){
                editor.putString("password", sharedPreferences.getString(s, null));
                editor.commit();
            }else if("fuenteL".equals(s)){
                Log.d("settings", "new value for downloadType: " + sharedPreferences.getString(s, null));
                String update = sharedPreferences.getString(s, null);
                boolean succes = editor
                        .putString("cambioEstilo", update)
                        .commit();
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }

    }


}


