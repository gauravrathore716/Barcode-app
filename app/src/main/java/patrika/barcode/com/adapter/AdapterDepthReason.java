package patrika.barcode.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import patrika.barcode.com.R;
import patrika.barcode.com.model.DepthReason;
import patrika.barcode.com.model.DropDownList;

public class AdapterDepthReason extends BaseAdapter {

    Context context;
    ArrayList<DepthReason.ResponseBean.DataBean> depthcutreason;
    LayoutInflater inflter;

    public AdapterDepthReason(Context applicationContext, ArrayList<DepthReason.ResponseBean.DataBean> depthcutreason) {
        this.context = applicationContext;
        this.depthcutreason = depthcutreason;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {

        return depthcutreason.size();
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.depth_reason, null);
        TextView names = (TextView) view.findViewById(R.id.text_unit);
        names.setText(depthcutreason.get(i).getReason_Name());
        return view;
    }
}