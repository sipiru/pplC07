package ppl.sipiru4.Entity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import java.util.HashMap;

import ppl.sipiru4.controller.PenggunaController;

public class SessionManager {
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "MyPreference";
    private static final String IS_USER_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_ROLE = "role";
    public static final String KEY_USER = "user";
    PenggunaController penggunaController;

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
    }

    //Create login session
    public void createLoginSession(User user){
        // Storing login value as TRUE
        editor = pref.edit();
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_NAME, user.getNama());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_ROLE, user.getRole());
        editor.apply();

        penggunaController = new PenggunaController(user);
    }

    /**
     * Get stored session data
     * */
    public HashMap<String, User> getUserDetails(){
        HashMap<String, User> user = new HashMap<>();
        user.put(KEY_USER, new User(pref.getString(KEY_USERNAME, null), pref.getString(KEY_NAME,null), pref.getString(KEY_ROLE,null)));

        return user;
    }

    public User getUser(){
        return getUserDetails().get(KEY_USER);
    }

    public void logoutUser(){
        editor = pref.edit();
        // Clearing all user data from Shared Preferences
        editor.clear();
        editor.apply();

        Log.e("data di session manager" , getUserDetails()+"");
//        // After logout redirect user to Login Activity
//        Intent i = new Intent(_context, LoginActivity.class);
//
//        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        // Staring Login Activity
//        _context.startActivity(i);
    }


//    Check for login
    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }
}