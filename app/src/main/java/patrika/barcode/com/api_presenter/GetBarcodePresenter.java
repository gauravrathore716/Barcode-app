package patrika.barcode.com.api_presenter;

import android.content.Context;
import androidx.annotation.NonNull;
import org.json.JSONObject;
import patrika.barcode.com.R;
import patrika.barcode.com.activity.AppController;
import patrika.barcode.com.model.BarcodeDetails;
import patrika.barcode.com.view.IBarcodeView;
import patrika.barcode.com.webservice.RestService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetBarcodePresenter  extends BasePresenter<IBarcodeView> {


    public void BarcodeList(final Context context,JSONObject jsonObject) {

            getView().enableLoadingBar(context, true, context.getString(R.string.msg_please_wait));

            AppController.getInstance().getApiInterface().GetReelDetail(RestService.requestBodyJsonObject("" + jsonObject))
                    .enqueue(new Callback<BarcodeDetails>() {
                        @Override
                        public void onResponse(@NonNull Call<BarcodeDetails> call, @NonNull Response<BarcodeDetails> response) {
                            getView().enableLoadingBar(context, false, "");
                            if (response.isSuccessful() && response.code() == 200) {
                                getView().onBarCodeSuccess(response.body());

                            } else {
                                getView().onError(response.message());
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<BarcodeDetails> call, @NonNull Throwable t) {
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



