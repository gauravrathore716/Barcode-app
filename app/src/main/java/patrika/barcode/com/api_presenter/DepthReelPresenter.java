package patrika.barcode.com.api_presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import org.json.JSONObject;
import patrika.barcode.com.activity.AppController;
import patrika.barcode.com.model.DepthList;
import patrika.barcode.com.model.DepthReel;
import patrika.barcode.com.view.IDepthReelView;
import patrika.barcode.com.webservice.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepthReelPresenter extends  BasePresenter<IDepthReelView>  {

    public void GetAllPendingReel(final Context context, JSONObject jsonObject) {
        //  getView().enableLoadingBar(context, true, context.getString(R.string.msg_please_wait));
        AppController.getInstance().getApiInterface().GetAllPendingReel(RestService.requestBodyJsonObject("" + jsonObject))
                .enqueue(new Callback<DepthReel>() {
                    @Override
                    public void onResponse(@NonNull Call<DepthReel> call, @NonNull Response<DepthReel> response) {
                        getView().enableLoadingBar(context, false, "");
                        if (response.isSuccessful() && response.code() == 200) {
                            getView().onSuccess(response.body());
                        }
                        else {
                            getView().onError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<DepthReel> call, @NonNull Throwable t) {
                        getView().enableLoadingBar(context, false, "");
                        try {
                            t.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        getView().onError(t.getMessage());
                    }
                });
    }}


