package patrika.barcode.com.activity;

import android.content.Context;
import android.content.SharedPreferences;
import patrika.barcode.com.model.LoginResponse;

public class SessionManager {

    public static final String ID = "";
    public static final String FIRST_NAME = "";
    public static final String NAME = "";
    public static final String ComCode = "";
    public static final String EMAIL = "";
    public static final String DOB = "";
    public static final String PROFILE = "";
    private static final String PREF_DEVICE_TOKEN = "";
    private static final String PREF_NAME = "";
    private static SharedPreferences mSharedPreferences;
    private Context context;

    public SessionManager(Context context) {

        this.context = context;
    }

    private static SharedPreferences getSharedPref(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }

    public static void setDeviceToken(Context context, String id) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString(PREF_DEVICE_TOKEN, id);
        editor.apply();
    }

    public static String getDeviceToken(Context context) {
        return getSharedPref(context).getString(PREF_DEVICE_TOKEN, "");
    }

//    public static void saveUserInfo(Context context, ModelUserInfo userInfo) {
//
//        SharedPreferences.Editor editor = getSharedPref(context).edit();
//        //Storing login value as TRUE
//     //   editor.putInt(ID, userInfo.getId());
//       // editor.putString(FIRST_NAME, userInfo.getFirst_name());
//       // editor.putString(NAME, userInfo.getName());
//       // editor.putString(EMAIL, userInfo.getEmail());
//       // editor.putString(DOB, userInfo.getDob());
//       // editor.putString(PROFILE, userInfo.getProfile_pic());
//        //  commit changes
//        //editor.apply();
//    }

    public static void saveUserInfo(Context context, LoginResponse.ResponseBean.DataBean userInfo) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        //Storing login value as TRUE
        editor.putString(ID, userInfo.getUserid());
        editor.putString(NAME, userInfo.getUsername());
        editor.putString(ComCode, userInfo.getCompcode());
        //  commit changes
        editor.apply();
    }

    public static void godam(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("godam", user);
        editor.apply();
    }

    public static void unit(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("unit", user);
        editor.apply();
    }

    public static void god_id(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("god_id", user);
        editor.apply();
    }

    public static void user_id(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("user_id", user);
        editor.apply();
    }

    public static void user_name(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("user_name", user);
        editor.apply();
    }

    public static void comp_code(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("comp_code", user);
        editor.apply();
    }

    public static void con_no(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("con_no", user);
        editor.apply();
    }

    public static void sup_no(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("sup_no", user);
        editor.apply();
    }

    public static void depth_cut(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("depth_reason", user);
        editor.apply();
    }

    public static void depth_reason(Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("depth_reason", user);
        editor.apply();
    }

   public static void pressreelno (Context context, String user) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString("pressreelno", user);
        editor.apply();
    }


    public static String getString(Context context, String key) {

        return getSharedPref(context).getString(key, "");

    }

    public static int getInt(Context context, String key) {

        return getSharedPref(context).getInt(key, 0);
    }

    public static String getUserImage(Context context) {
        return getSharedPref(context).getString(PROFILE, "");
    }

    public static void setUserImage(Context context, String id) {
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString(PROFILE, id);
        editor.commit();
    }

    public static void clearAll(Context context) {

        getSharedPref(context).edit()
                .clear()
                .remove("id")
                .apply();


    }


}