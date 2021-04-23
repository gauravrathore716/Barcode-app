package patrika.barcode.com.api_presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import org.json.JSONObject;
import patrika.barcode.com.R;
import patrika.barcode.com.activity.AppController;
import patrika.barcode.com.model.LoginResponse;
import patrika.barcode.com.model.UpdateData;
import patrika.barcode.com.view.ILoginView;
import patrika.barcode.com.view.IPostView;
import patrika.barcode.com.webservice.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDataPresenter extends BasePresenter<IPostView> {

    public void PostScanReel(final Context context, JSONObject jsonObject) {
        getView().enableLoadingBar(context, true, context.getString(R.string.msg_please_wait));
        AppController.getInstance().getApiInterface().PostScanReel(RestService.requestBodyJsonObject("" + jsonObject))
                .enqueue(new Callback<UpdateData>() {
                    @Override
                    public void onResponse(@NonNull Call<UpdateData> call, @NonNull Response<UpdateData> response) {
                        getView().enableLoadingBar(context, false, "");
                            if (response.isSuccessful() && response.code() == 200) {
                                getView().onPostData(response.body());
                            }
                            else {
                                getView().onError(response.message());
                            }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UpdateData> call, @NonNull Throwable t) {
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