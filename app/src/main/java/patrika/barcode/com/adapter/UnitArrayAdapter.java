package patrika.barcode.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import patrika.barcode.com.R;
import patrika.barcode.com.model.DropDownList;

public class UnitArrayAdapter extends BaseAdapter {
    Context context;
    ArrayList<DropDownList.ResponseBean.DataBean> BranchNames;
    LayoutInflater inflter;

    public UnitArrayAdapter(Context applicationContext, ArrayList<DropDownList.ResponseBean.DataBean> BranchNames) {
        this.context = applicationContext;
        this.BranchNames = BranchNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return BranchNames.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.layout_unit, null);
        TextView names = (TextView) view.findViewById(R.id.text_unit);
        names.setText(BranchNames.get(i).getCentname());
        return view;
    }
}