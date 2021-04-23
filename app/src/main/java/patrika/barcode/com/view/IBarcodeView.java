package patrika.barcode.com.view;


import android.annotation.SuppressLint;

import patrika.barcode.com.model.BarcodeDetails;
import patrika.barcode.com.model.ModelDeatails;

public interface IBarcodeView extends IView {

    void onBarCodeSuccess(BarcodeDetails body);

}
