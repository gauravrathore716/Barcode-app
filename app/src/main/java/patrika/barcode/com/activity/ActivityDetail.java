package patrika.barcode.com.activity;


import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import org.json.JSONObject;
import java.util.ArrayList;
import patrika.barcode.com.adapter.AdapterAllReel;
import patrika.barcode.com.adapter.AdapterItemlist;
import patrika.barcode.com.R;
import patrika.barcode.com.api_presenter.DropDepthPresenter;
import patrika.barcode.com.api_presenter.GetMobilPresenter;
import patrika.barcode.com.databinding.ActivityTableBinding;
import patrika.barcode.com.model.DepthList;
import patrika.barcode.com.model.ModelDeatails;
import patrika.barcode.com.view.IDepthView;
import patrika.barcode.com.view.IMobView;

public class ActivityDetail extends BaseActivity implements IDepthView {

    ActivityTableBinding binding;
  //  GetMobilPresenter presenter;
    ArrayList<DepthList.ResponseBean.DataBean> list;
    AdapterItemlist adapter;
    DropDepthPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_table);
        presenter = new DropDepthPresenter();
        presenter.setView(this);

      //  presenter = new GetMobilPresenter();
      //  presenter.setView(this);

        list = new ArrayList<>();
        getlist();


        binding.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getlist();
                binding.swipe.setColorSchemeColors(Color.RED, Color.YELLOW, Color.RED);
                binding.swipe.setRefreshing(false);
            }
        });

    }

    private void getlist() {
        if (NetworkAlertUtility.isConnectingToInternet2(this)) {
            JSONObject jsonObject = null;
            try{
                jsonObject = new JSONObject();
                jsonObject.put("PCOMP_CODE", SessionManager.getString(ActivityDetail.this, "comp_code"));
                jsonObject.put("PUNIT_CODE", SessionManager.getString(getContext(), "godam"));
                jsonObject.put("PCONTAINER_NO",SessionManager.getString(getContext(), "con_no"));
                jsonObject.put("PSUPPLIER_REEL_NO", SessionManager.getString(getContext(), "sup_no"));
                jsonObject.put("PRESSREELNO", SessionManager.getString(getContext(), "pressreelno"));

                // jsonObject.put("PCONTAINER_NO","TEMU8330241");
                //jsonObject.put("PSUPPLIER_REEL_NO","5146093407151558");
                //  jsonObject.put("PGROSSWEIGHT",SessionManager.getString(getContext(),""));
                jsonObject.put("PDEPTHCUT", SessionManager.getString(getContext(),"depth_cut"));
                jsonObject.put("PDEPTHCUTREASON", SessionManager.getString(getContext(),"depth_reason"));
                jsonObject.put("PDATEFORMAT", "dd/mm/yyyy");
                jsonObject.put("PUSERID", SessionManager.getString(ActivityDetail.this, "user_id"));
                presenter.GetAllContainerReel(ActivityDetail.this, jsonObject);
            //pass data
//            jsonObject.put("PCOMP_CODE", SessionManager.getString(MainActivity.this, "comp_code"));
//            jsonObject.put("PUNIT_CODE", SessionManager.getString(getContext(), "godam"));
//            jsonObject.put("PCONTAINER_NO", binding.edit.getText().toString());

        }catch (Exception e) {
                e.printStackTrace();
            }}
        else {
            NetworkAlertUtility.showNetworkFailureAlert(this);
        }
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img:
                finish();
                break;
        }
    }

  /*  @Override
    public void onSuccess(DepthList body) {

           // list.addAll(defaultResponse.getResponse().getData());
            adapter = new AdapterItemlist(ActivityDetail.this, list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ActivityDetail.this);
            binding.recycle.setLayoutManager(mLayoutManager);
            binding.recycle.setAdapter(adapter);
        }
    }*/

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onBarCodeSuccess(DepthList body) {
          list.clear();
        if (body != null && body.getResponse().getData() != null) {
            list.addAll(body.getResponse().getData());
            adapter = new AdapterItemlist(ActivityDetail.this, list);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ActivityDetail.this);
            binding.recycle.setLayoutManager(mLayoutManager);
            binding.recycle.setAdapter(adapter);

        } else if (body.getResponse().getIsSuccess().equalsIgnoreCase("false")) {
            Toast.makeText(getApplicationContext(), "No data Found", Toast.LENGTH_SHORT).show();
        }
    }
}
