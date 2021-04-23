package patrika.barcode.com.view;

import patrika.barcode.com.model.LoginResponse;
import patrika.barcode.com.model.UpdateData;

public interface IPostView extends IView {

    void onPostData(UpdateData body);

}
