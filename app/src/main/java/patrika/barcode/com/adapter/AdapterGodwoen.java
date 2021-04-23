package patrika.barcode.com.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import patrika.barcode.com.R;
import patrika.barcode.com.model.DropGodownList;

public class AdapterGodwoen extends BaseAdapter {

    Context context;
    ArrayList<DropGodownList.ResponseBean.DataBean> countryNames;
    LayoutInflater inflter;

    public AdapterGodwoen(Context applicationContext, ArrayList<DropGodownList.ResponseBean.DataBean> countryNames) {
        this.context = applicationContext;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return countryNames.size();
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
        names.setText(countryNames.get(i).getGodownname());
        return view;
    }
}