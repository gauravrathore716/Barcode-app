package patrika.barcode.com.api_presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import patrika.barcode.com.R;
import patrika.barcode.com.activity.AppController;
import patrika.barcode.com.activity.MainActivity;
import patrika.barcode.com.model.ModelDeatails;
import patrika.barcode.com.view.IMobView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMobilPresenter extends BasePresenter<IMobView> {

    public void categoryList(final Context context) {
        getView().enableLoadingBar(context, true, context.getString(R.string.msg_please_wait));

        AppController.getInstance().getApiInterface().Values()
                .enqueue(new Callback<ModelDeatails>() {
                    @Override
                    public void onResponse(@NonNull Call<ModelDeatails> call, @NonNull Response<ModelDeatails> response) {
                        getView().enableLoadingBar(context, false, "");
                            if (response.isSuccessful() && response.code() == 200) {
                                getView().onSuccess(response.body());

                        } else {
                            getView().onError(response.message());
                        }
                    }

                    @Override

                    public void onFailure(@NonNull Call<ModelDeatails> call, @NonNull Throwable t) {
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
