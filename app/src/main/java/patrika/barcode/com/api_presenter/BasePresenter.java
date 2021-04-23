package patrika.barcode.com.api_presenter;


import patrika.barcode.com.view.IView;

public abstract class BasePresenter<I extends IView> {

    private I iView;

    public BasePresenter() {
    }

    public I getView() {
        return iView;
    }

    public void setView(I iView) {
        this.iView =  iView;
    }

    boolean handleError(retrofit2.Response response) {
        /*if (response.code() == 203) {
           // return handleError(((BaseResponse) response.body()), false);
        }
        else if (response.code() == 440) {
            getView().onTokenExpired();
            return true;
        }
        else */
        if (response.errorBody() != null) {
            try {
                String error = response.errorBody().string();

//                Converter<ResponseBody, ErrorResponse> converter = retrofit2.responseBodyConverter(ErrorResponse.class, new Annotation[0]);

//                JSONObject jObjError = new JSONObject(error);
//                String jerror=  jObjError.getString("error");

              //  getView().onError(!error.isEmpty() ? error : null);
            } catch (Exception e) {
                e.printStackTrace();
                //getView().onError(null);
                return false;
            }
            return false;
        }
        return true;
    }

  /*  public boolean handleError(BaseResponse response, boolean success) {
        if (response != null && response.meta != null) {

            if (response.meta.getHasUpdate()) {
                if (response.meta.getForceUpdate())
                    getView().onForceUpdate();
                else
                    getView().onSoftUpdate();
                return true;
            }
        }
        if (success) {
            return false;
        } else {
            getView().onError(response != null ? response.replyMsg : null);
            return true;
        }

    }*/
}
