package patrika.barcode.com.view;

import patrika.barcode.com.model.DepthList;
import patrika.barcode.com.model.DepthReel;

public interface IDepthReelView  extends  IView{

    void onSuccess(DepthReel body);
}
