package patrika.barcode.com.api_presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import org.json.JSONObject;
import patrika.barcode.com.R;
import patrika.barcode.com.activity.AppController;
import patrika.barcode.com.model.DepthList;
import patrika.barcode.com.model.LoginResponse;
import patrika.barcode.com.view.IDepthView;
import patrika.barcode.com.webservice.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DropDepthPresenter  extends  BasePresenter<IDepthView> {

    public void GetAllContainerReel(final Context context, JSONObject jsonObject) {
      // getView().enableLoadingBar(context, true, context.getString(R.string.msg_please_wait));
        AppController.getInstance().getApiInterface().GetAllContainerReel(RestService.requestBodyJsonObject("" + jsonObject))
                .enqueue(new Callback<DepthList>() {
                    @Override
                    public void onResponse(@NonNull Call<DepthList> call, @NonNull Response<DepthList> response) {
                        getView().enableLoadingBar(context, false, "");
                        if (response.isSuccessful() && response.code() == 200) {
                            getView().onBarCodeSuccess(response.body());
                        }
                        else {
                          //  getView().onError(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<DepthList> call, @NonNull Throwable t) {
                        getView().enableLoadingBar(context, false, "");
                        try {
                            t.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                     //   getView().onError(t.getMessage());
                    }
                });
}}
