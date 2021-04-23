package patrika.barcode.com.view;

import patrika.barcode.com.model.BarcodeDetails;
import patrika.barcode.com.model.DepthList;

public interface IDepthView extends IView {

    void onBarCodeSuccess(DepthList body);

}
