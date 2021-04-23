package patrika.barcode.com.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;
import patrika.barcode.com.R;
import patrika.barcode.com.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {

    public static int timeout = 2300;
    ActivitySplashBinding  binding;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        Animation in = AnimationUtils.loadAnimation(this, R.anim.alpha);
        binding.image.startAnimation(in);

        NetworkAlertUtility receiver = new NetworkAlertUtility();
        if (!receiver.isOnline(Splash.this)) {
            Toast.makeText(Splash.this, "Internet Connection Is Required", Toast.LENGTH_LONG).show();

            try {

                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Connection Lost");
                alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                ///alertDialog.setCustomTitle(R.anim.backslide_in);
                alertDialog.setButton("OK", (dialog, which) -> finish());
                alertDialog.show();

            } catch (Exception e) {

              //  Log.d("", "Show Dialog: " + e.getMessage());
            }
        }

        else {
            new android.os.Handler().postDelayed(() -> {

                Intent i;

                if (SessionManager.getString(getApplicationContext(), "user_id").equalsIgnoreCase("")) {

                    i = new Intent(Splash.this, LoginActivity.class);
                    startActivity(i);
                    finishAffinity();
                }
                else

                    i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);

                finishAffinity();
            }, timeout);

    }}}


