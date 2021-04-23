package patrika.barcode.com.activity;

import android.content.Context;
import android.widget.Toast;

public class MyCustomToast {

    private static Toast toast;

    public static void showToast(Context context, String msg){

        cancelToast(context) ;
        toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);

//		 View view = toast.getView();
        // TextView text = (TextView) view.findViewById(android.R.id.message);
        // /* here you can do anything with text */
        toast.show();
    }

    private static void cancelToast(Context context){
        if (toast != null) {
            toast.cancel();
        }
    }

}
