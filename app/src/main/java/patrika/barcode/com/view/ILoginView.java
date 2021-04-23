package patrika.barcode.com.view;

;
import patrika.barcode.com.model.LoginResponse;

public interface ILoginView extends IView {

    void onSuccess(LoginResponse body);
}
