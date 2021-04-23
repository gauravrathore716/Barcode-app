package patrika.barcode.com.view;

import patrika.barcode.com.model.BarcodeDetails;
import patrika.barcode.com.model.DropDownList;
import patrika.barcode.com.model.DropGodownList;

public interface IDropGodownView extends  IView {

    void onSecondDrop(DropGodownList defaultResponse);
}