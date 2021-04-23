package patrika.barcode.com.view;

import patrika.barcode.com.model.DepthReason;
import patrika.barcode.com.model.DropDownList;
import patrika.barcode.com.model.DropGodownList;

public interface IDepthReasonView extends IView{

    void  OnFirstDrop(DepthReason defaultResponse);
    void onSecondDrop(DropGodownList defaultResponse);

}
