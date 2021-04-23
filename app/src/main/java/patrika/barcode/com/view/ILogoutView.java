package patrika.barcode.com.view;

import patrika.barcode.com.model.LoginResponse;

public interface ILogoutView extends IView {

    void onSuccess(LoginResponse body);

}
