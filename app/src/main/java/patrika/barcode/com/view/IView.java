package patrika.barcode.com.view;


import android.content.Context;

import patrika.barcode.com.model.LoginResponse;

public interface IView {

    Context getContext();

    void enableLoadingBar(Context context, boolean enable, String s);

    void onError(String reason);

   }
