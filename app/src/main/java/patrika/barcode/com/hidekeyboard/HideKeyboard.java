package patrika.barcode.com.hidekeyboard;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class HideKeyboard {


    public static void setupUI(View view, final AppCompatActivity activity) {
        try {
            if (!(view instanceof EditText)) {
                view.setOnTouchListener(new OnTouchListener() {
                    @Override
                    @SuppressLint("ClickableViewAccessibility")
                    public boolean onTouch(View v, MotionEvent event) {
                        hideSoftKeyboard(activity);
                        return false;
                    }
                });
            }
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    View innerView = ((ViewGroup) view).getChildAt(i);
                    setupUI(innerView, activity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hideSoftKeyboard(AppCompatActivity activity) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

