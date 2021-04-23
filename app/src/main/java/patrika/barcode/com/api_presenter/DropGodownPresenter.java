package patrika.barcode.com.api_presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import patrika.barcode.com.R;
import patrika.barcode.com.activity.AppController;
import patrika.barcode.com.model.DropDownList;
import patrika.barcode.com.model.DropGodownList;
import patrika.barcode.com.model.LoginResponse;
import patrika.barcode.com.view.IDropGodownView;
import patrika.barcode.com.webservice.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DropGodownPresenter  extends BasePresenter<IDropGodownView> {


    public void GetGodown(final Context context, JSONObject jsonObject) {
        getView().enableLoadingBar(context, true, context.getString(R.string.msg_please_wait));
        AppController.getInstance().getApiInterface().Godown(RestService.requestBodyJsonObject("" + jsonObject))
                .enqueue(new Callback<DropGodownList>() {
                    @Override
                    public void onResponse(@NonNull Call<DropGodownList> call, @NonNull Response<DropGodownList> response) {
                        getView().enableLoadingBar(context, false, "");
                        if (response.isSuccessful() && response.code() == 200) {
                            getView().onSecondDrop(response.body());
                        }
                        else {
                            getView().onError(response.message());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<DropGodownList> call, @NonNull Throwable t) {
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
