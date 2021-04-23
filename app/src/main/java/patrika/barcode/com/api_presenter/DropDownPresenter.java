package patrika.barcode.com.api_presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import patrika.barcode.com.R;
import patrika.barcode.com.activity.AppController;
import patrika.barcode.com.model.DropDownList;
import patrika.barcode.com.model.ModelDeatails;
import patrika.barcode.com.view.IDropView;
import patrika.barcode.com.view.ILoginView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DropDownPresenter extends  BasePresenter<IDropView> {
    public void Unit(final Context context) {
        getView().enableLoadingBar(context, true, context.getString(R.string.msg_please_wait));

        AppController.getInstance().getApiInterface().Unit()
                .enqueue(new Callback<DropDownList>() {
                    @Override
                    public void onResponse(@NonNull Call<DropDownList> call, @NonNull Response<DropDownList> response) {
                        getView().enableLoadingBar(context, false, "");
                        if (response.isSuccessful() && response.code() == 200) {
                            getView().OnFirstDrop(response.body());

                        } else {
                            getView().onError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<DropDownList> call, @NonNull Throwable t) {
                        getView().enableLoadingBar(context, false, "");
                        try {
                            t.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        getView().onError(t.getMessage());
                    }
                });

    }


}


