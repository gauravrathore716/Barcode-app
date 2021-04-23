package patrika.barcode.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import org.json.JSONObject;
import java.util.ArrayList;
import patrika.barcode.com.R;
import patrika.barcode.com.adapter.AdapterGodwoen;
import patrika.barcode.com.adapter.UnitArrayAdapter;
import patrika.barcode.com.api_presenter.DropDownPresenter;
import patrika.barcode.com.api_presenter.DropGodownPresenter;
import patrika.barcode.com.api_presenter.LoginPresenter;
import patrika.barcode.com.databinding.ActivityLoginBinding;
import patrika.barcode.com.hidekeyboard.HideKeyboard;
import patrika.barcode.com.model.DepthList;
import patrika.barcode.com.model.DropDownList;
import patrika.barcode.com.model.DropGodownList;
import patrika.barcode.com.model.LoginResponse;
import patrika.barcode.com.view.IDropGodownView;
import patrika.barcode.com.view.IDropView;
import patrika.barcode.com.view.ILoginView;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView, IDropView, AdapterView.OnItemSelectedListener, IDropGodownView {

    private LoginPresenter presenter;
    private DropDownPresenter dropDownPresenter;
    // private GetBarcodePresenter presenterr;
    ActivityLoginBinding binding;
    ArrayList<DropDownList.ResponseBean.DataBean> list = new ArrayList<>();
    ArrayList<DropGodownList.ResponseBean.DataBean> godList = new ArrayList<>();
    String item,unitname;
    DropGodownPresenter godownPresenter;
    String god_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        // list = new ArrayList<>();
//        getlist();
        presenter = new LoginPresenter();
        presenter.setView(this);
        dropDownPresenter= new DropDownPresenter();
        dropDownPresenter.setView(this);
        godownPresenter = new DropGodownPresenter();
        godownPresenter.setView(this);
        callFirstlist();
        HideKeyboard.setupUI(binding.view, LoginActivity.this);

        binding.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    binding.edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    binding.edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
    private void callFirstlist() {

        if (NetworkAlertUtility.isConnectingToInternet2(this)) {
            dropDownPresenter.Unit(LoginActivity.this);
        } else {
            NetworkAlertUtility.showNetworkFailureAlert(this);
        }
    }

    private void validtion() {

        String emailString = binding.edtUsername.getText().toString().trim();
        String passString = binding.edtPassword.getText().toString().trim();

//        if (binding.spDowoun.getSelectedItem().toString().trim().equals("")) {
//            Toast.makeText(getApplicationContext(), "please select Unit", Toast.LENGTH_SHORT).show();
//        }
        if (TextUtils.isEmpty(unitname)) {
            Toast.makeText(getApplicationContext(),"Please Select Unit",Toast.LENGTH_SHORT).show();

        }
       else if(TextUtils.isEmpty(god_id)){
            Toast.makeText(getApplicationContext(),"Please Select Godown",Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(emailString)) {
            Toast.makeText(getApplicationContext(),"Enter Username",Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(passString)){
            Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
        }
        else {
            if (NetworkAlertUtility.isConnectingToInternet2(this)) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("username", emailString);
                    jsonObject.put("password", passString);
                    presenter.login(LoginActivity.this, jsonObject);
                    // presenterr.BarcodeList(LoginActivity.this,jsonObject);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                NetworkAlertUtility.showNetworkFailureAlert(this);
            }
        }
    }

    @Override
    public Context getContext()
    {
        return this;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                // Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                // startActivity(intent);
                validtion();
                break;
            case R.id.llmain:
                break;
        }
    }

    @Override
    public void onSuccess(LoginResponse body) {
        if (body.getResponse().getIsSuccess().equalsIgnoreCase("true")) {
            SessionManager.user_id(getApplicationContext(),body.getResponse().getData().getUserid());
            SessionManager.user_name(getApplicationContext(),body.getResponse().getData().getUsername());
            SessionManager.comp_code(getApplicationContext(),body.getResponse().getData().getCompcode());
            SessionManager.godam(getApplicationContext(),item);
            SessionManager.unit(getApplicationContext(),unitname);
            SessionManager.god_id(getApplicationContext(),god_id);
            Log.e("user_id",body.getResponse().getData().getUserid());
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            // Log.e("body", "" + body.getResponse().getData().getUserid());
        } else {
            Toast.makeText(getApplicationContext(), "Invalid username password", Toast.LENGTH_SHORT).show();

        }//  binding.spin.setOnItemClickListener(new V);
    }


    @Override
    public void OnFirstDrop(DropDownList defaultResponse) {
        if (defaultResponse.getResponse()!=null) {
            list.addAll(defaultResponse.getResponse().getData());
            Log.e("my_list", list.toString());
            binding.spin.setOnItemSelectedListener(this);
            UnitArrayAdapter customAdapter=new UnitArrayAdapter(getApplicationContext(),list);
            binding.spin.setAdapter(customAdapter);
        }
    }

    @Override
    public void onSecondDrop(DropGodownList defaultResponse) {
        if (defaultResponse.getResponse()!=null) {
            godList.clear();

            try {

                godList.addAll(defaultResponse.getResponse().getData());
                AdapterGodwoen customAdapter=new AdapterGodwoen (getApplicationContext(),godList);
                binding.spDowoun.setAdapter(customAdapter);
                String itemmm=defaultResponse.getResponse().getData().get(0).getGodowncode();
                Log.e("yyyyy",""+itemmm);
                binding.spDowoun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                      //  Toast.makeText(getApplicationContext(),""+list.get(arg2).centcode,Toast.LENGTH_SHORT).show();

                         god_id  = list.get(arg2).centcode;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub.

                    }

                });
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
        }}

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//       if (adapterView.getId()==R.id.spin){
//           unitname=list.get(i).centname;
//           item=list.get(i).centcode;
//           callgodoam(item,unitname);
//       }
//       else if (adapterView.getId()==R.id.spDowoun){
//           Log.e("item__",""+list.get(i).centcode);
//       }

        switch (adapterView.getId()){
            case R.id.spin:
            unitname=list.get(i).centname;
            item=list.get(i).centcode;
           callgodoam(item,unitname);
                break;
            case R.id.spDowoun:
                Log.e("item__",""+list.get(i).centcode);
                break;
        }

    }

    private void callgodoam(String item, String unitname) {
        if (NetworkAlertUtility.isConnectingToInternet2(this)) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject();
                jsonObject.put("centcode", item);
                jsonObject.put("centname","");
                godownPresenter.GetGodown(LoginActivity.this, jsonObject);
                // presenterr.BarcodeList(LoginActivity.this,jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            NetworkAlertUtility.showNetworkFailureAlert(this);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
