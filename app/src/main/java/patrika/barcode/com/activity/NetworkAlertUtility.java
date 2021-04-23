package patrika.barcode.com.activity;

import android.app.Activity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Button;
import patrika.barcode.com.R;

public class NetworkAlertUtility {


    private static AlertDialog alert;

    public static void showAlert(Context context, String title, String message) {
        if (alert != null && alert.isShowing())
            return;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setTitle(title)
                .setCancelable(false)
                .setNegativeButton(context.getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
//        nbutton.setTextColor(context.getResources().getColor(R.color.colorAccent));
    }

    public static void showAlert(Context context, String title, String message, DialogInterface.OnClickListener okListner) {
        if (alert != null && alert.isShowing())
            return;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setTitle(title)
                .setCancelable(false)
                .setNegativeButton(context.getString(R.string.btn_ok), okListner);
        alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
//        nbutton.setTextColor(context.getResources().getColor(R.color.colorAccent));
    }

    public static boolean isConnectingToInternet(Context context) {
        if (context != null) {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null)
                    for (NetworkInfo anInfo : info) {

                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;

                        }
                    }
            }
        }

        return false;
    }

    public static boolean isConnectingToInternet2(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isInternetConnection(Context mContext) {
        final ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            final NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            final NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return wifi.isAvailable() && wifi.getState() == NetworkInfo.State.CONNECTED || mobile.isAvailable() && mobile.getState() == NetworkInfo.State.CONNECTED;
        }
        return false;
    }

    public static void showNetworkFailureAlert(Context context) {
        if (context != null && !((Activity) context).isFinishing() && (alert == null || !alert.isShowing())) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(context.getResources().getString(R.string.no_network_message)).setTitle(context.getResources().getString(R.string.no_internet))
                    .setCancelable(false)
                    .setNegativeButton(context.getString(R.string.btn_ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            alert = builder.create();
            alert.show();
            Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
//            nbutton.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }
    }

    public boolean isOnline(Context context) {

        try {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return (netInfo != null && netInfo.isConnected());

        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    }
