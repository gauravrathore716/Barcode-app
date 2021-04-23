package patrika.barcode.com.api_presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import org.json.JSONObject;
import java.util.HashMap;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import patrika.barcode.com.R;
import patrika.barcode.com.activity.AppController;
import patrika.barcode.com.activity.LoginActivity;
import patrika.barcode.com.model.LoginResponse;
import patrika.barcode.com.view.ILoginView;
import patrika.barcode.com.view.IView;
import patrika.barcode.com.webservice.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

public class LoginPresenter extends BasePresenter<ILoginView> {

    public void login(final Context context, JSONObject jsonObject) {
        getView().enableLoadingBar(context, true, context.getString(R.string.msg_please_wait));
        AppController.getInstance().getApiInterface().Login(RestService.requestBodyJsonObject("" + jsonObject))
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                        getView().enableLoadingBar(context, false, "");
                            if (response.isSuccessful() && response.code() == 200) {
                                getView().onSuccess(response.body());
                            }
                            else {
                                getView().onError(response.message());
                            }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
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