package patrika.barcode.com.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.sdsmdg.tastytoast.TastyToast;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;
import org.json.JSONObject;
import java.util.ArrayList;
import patrika.barcode.com.R;
import patrika.barcode.com.adapter.AdapterDepthReason;
import patrika.barcode.com.adapter.AdapterGodwoen;
import patrika.barcode.com.adapter.UnitArrayAdapter;
import patrika.barcode.com.api_presenter.DepthReasonPresenter;
import patrika.barcode.com.api_presenter.DropDownPresenter;
import patrika.barcode.com.api_presenter.DropGodownPresenter;
import patrika.barcode.com.api_presenter.GetBarcodePresenter;
import patrika.barcode.com.api_presenter.UpdateDataPresenter;
import patrika.barcode.com.databinding.ActivityMainBinding;
import patrika.barcode.com.hidekeyboard.HideKeyboard;
import patrika.barcode.com.model.BarcodeDetails;
import patrika.barcode.com.model.DepthList;
import patrika.barcode.com.model.DepthReason;
import patrika.barcode.com.model.DropDownList;
import patrika.barcode.com.model.DropGodownList;
import patrika.barcode.com.model.UpdateData;
import patrika.barcode.com.view.IBarcodeView;
import patrika.barcode.com.view.IDepthReasonView;
import patrika.barcode.com.view.IDropGodownView;
import patrika.barcode.com.view.IDropView;
import patrika.barcode.com.view.IPostView;

public class MainActivity extends BaseActivity implements  View.OnClickListener, IBarcodeView, IPostView, IDepthReasonView ,AdapterView.OnItemSelectedListener {

    ActivityMainBinding binding;
    private DropDownPresenter dropDownPresenter;

    ArrayList<DepthReason.ResponseBean.DataBean> list = new ArrayList<>();
    ArrayList<DropGodownList.ResponseBean.DataBean> godList = new ArrayList<>();
    private Context context;
    // ArrayList<DropGodownList.ResponseBean.DataBean> godList = new ArrayList<>();
    public static final String BARCODE_KEY = "BARCODE";
  //  private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
   // private static final int REQUEST_PERMISSION_SETTING = 101;
    private boolean sentToSettings = false;
    private final int MY_PERMISSION_REQUEST_CAMERA = 1001;
    private SharedPreferences permissionStatus;

    private com.google.android.gms.vision.barcode.Barcode barcodeResult;

    private final String TAG = MainActivity.class.getSimpleName();
    //  private final int MY_PERMISSION_REQUEST_CAMERA = 1001;
    // private ItemScanned itemScanned;
    String strBarcode;
    //  DropGodownPresenter godownPresenter;

    GetBarcodePresenter presenter;
    // DropDepthPresenter presenterr;
    UpdateDataPresenter postPrsentetr;
    DepthReasonPresenter depthReasonPresenter;
    String item,unitname;
    String receipt_wt, reel_gsm, reel_mm, depthcut;
    String getItem, DepthReason;
    public static int timeout = 2300;
    private  String godam="god_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Toolbar my_tool = (Toolbar)findViewById(R.id.tool);
        //setSupportActionBar(my_tool);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        context = this;
        presenter = new GetBarcodePresenter();
        presenter.setView(this);
        postPrsentetr = new UpdateDataPresenter();
        postPrsentetr.setView(this);
        depthReasonPresenter = new DepthReasonPresenter();
        depthReasonPresenter.setView(this);
        callFirstlistt();
        HideKeyboard.setupUI(binding.view, MainActivity.this);
        try {

            String user_id = SessionManager.getString(MainActivity.this, "user_id");
            godam = SessionManager.getString(MainActivity.this, "godam");
            Log.e("user_idd", user_id);
            binding.tool.txtUserName.setText(SessionManager.getString(getApplicationContext(), "user_name"));
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        //  String user_name = SessionManager.getString(MainActivity.this,"key");
        // binding.txtView.setText(user_name);
        //getmob();
    }

    private void callFirstlistt() {
        if (NetworkAlertUtility.isConnectingToInternet2(this)) {

            depthReasonPresenter.GetDepthReason(MainActivity.this);
        } else {
            NetworkAlertUtility.showNetworkFailureAlert(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    /*  private void setupViewPager(ViewPager viewPager) {
          ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
          adapter.addFragment(new BarcodeFragment(), "Barcode Scanner");
          adapter.addFragment(new ProductListFragment(), "Scan Item");
          viewPager.setAdapter(adapter);
      }*/
    @Override
    public void onBackPressed() {

        new LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.HORIZONTAL)
                .setTopColorRes(R.color.colorPrimaryDark)
                .setButtonsColorRes(R.color.colorAccent)
                .setIcon(R.drawable.ic_clear_black_24dp)
                .setTitle("Exit")
                .setMessage("Are you sure want to exit?")
                .setPositiveButton("yes", new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {
                        finishAffinity();
                        // finish();
                        //  Toast.makeText(context, "positive clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
    private void startScanningBarcode() {
        try {

        MaterialBarcodeScannerBuilder materialBarcodeScannerBuilder = new MaterialBarcodeScannerBuilder();
        materialBarcodeScannerBuilder.withActivity(MainActivity.this);
        materialBarcodeScannerBuilder.withEnableAutoFocus(true);
        materialBarcodeScannerBuilder.withBleepEnabled(true);
        materialBarcodeScannerBuilder.withBackfacingCamera();
        materialBarcodeScannerBuilder.withCenterTracker();
        materialBarcodeScannerBuilder.withText("Scanning....");
        materialBarcodeScannerBuilder.withResultListener(new MaterialBarcodeScanner.OnResultListener() {
            @Override
            public void onResult(com.google.android.gms.vision.barcode.Barcode barcode) {

                try {

                        barcodeResult = barcode;
                        Log.e("result", "" + barcodeResult);
                        Log.e("barcode", "" + barcode.rawValue);
                        strBarcode = barcode.rawValue;
                        callBarCodeApi(strBarcode);
                }
                catch (NullPointerException e){
                    e.printStackTrace();
                }
                }

        });
        final MaterialBarcodeScanner materialBarcodeScanner = materialBarcodeScannerBuilder
                .build();
        materialBarcodeScanner.startScan();

    }
    catch (NullPointerException e){
        e.printStackTrace();}
    }

    private void callBarCodeApi(String strBarcode) {

        if (NetworkAlertUtility.isConnectingToInternet2(this)) {
            JSONObject jsonObject = null;

            try {

                jsonObject = new JSONObject();
                jsonObject.put("PCOMP_CODE", SessionManager.getString(MainActivity.this, "comp_code"));
                jsonObject.put("PUNIT_CODE", SessionManager.getString(getContext(), "godam"));
                jsonObject.put("PCONTAINER_NO", binding.edit.getText().toString());
                jsonObject.put("PSUPPLIER_REEL_NO", strBarcode);
                jsonObject.put("PDATEFORMAT", "dd/mm/yyyy");
                jsonObject.put("PUSERID", SessionManager.getString(MainActivity.this, "user_id"));
                presenter.BarcodeList(MainActivity.this, jsonObject);

            } catch (Exception e) {
                e.printStackTrace();

            }

        } else {
            NetworkAlertUtility.showNetworkFailureAlert(this);
        }
    }

    /*verride
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      //   if (requestCode==MY_PERMISSION_REQUEST_CAMERA && grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
             startScanningBarcode();
         } //else {
             // Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.sorry_for_not_permission), Snackbar.LENGTH_SHORT)
     //       .show();
 */
    @SuppressLint("ResourceAsColor")
    // @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClick(View view) {
        if (NetworkAlertUtility.isConnectingToInternet2(this)) {
            switch (view.getId()) {

                case R.id.image:
                    if (binding.edit.length() < 1) {
                        binding.edit.setError("Please Fill Container");
                        binding.edit.requestFocus();

                    } else if (TextUtils.isEmpty(godam)) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Please Select Godown", Toast.LENGTH_SHORT);

                        toast.setGravity(Gravity.CENTER, 0, 0);
                    //   View view =toast.getView();
                        toast.show();

                        // Toast.makeText(MainActivity.this, "fill fill colum", Toast.LENGTH_LONG).show();
                    } else {

                        startScanningBarcode();
                    }
                    break;

                    case R.id.edit:
                    break;
                case R.id.btnLogin: {

                    String receiptString = binding.txtReceipt.getText().toString().trim();
                    String gsmString = binding.txtGsm.getText().toString().trim();
                    String mmString = binding.txtmm.getText().toString().trim();
                    String SuppierString = binding.txtSuppierNo.getText().toString().trim();

//        if (binding.spDowoun.getSelectedItem().toString().trim().equals("")) {
//            Toast.makeText(getApplicationContext(), "please select Unit", Toast.LENGTH_SHORT).show();
//        }
                    if (TextUtils.isEmpty(SuppierString)) {

                        Toast.makeText(getApplicationContext(), "Supplier No Not Found", Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(gsmString)) {
                        Toast.makeText(getApplicationContext(), "please Enter Gsm", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(mmString)) {
                        Toast.makeText(getApplicationContext(), "please Enter mm", Toast.LENGTH_SHORT).show();

                    } else if (TextUtils.isEmpty(receiptString)) {
                        Toast.makeText(getApplicationContext(), "Please Enter Receipt ", Toast.LENGTH_SHORT).show();

                    } else if (NetworkAlertUtility.isConnectingToInternet2(this)) {

                        JSONObject jsonObject = null;

                        try {

                            jsonObject = new JSONObject();
                            jsonObject.put("compcode", SessionManager.getString(getContext(), "comp_code"));
                            jsonObject.put("unitcode", SessionManager.getString(getContext(), "godam"));
                            jsonObject.put("loccode", SessionManager.getString(getContext(), "god_id"));
                            jsonObject.put("godowncode", SessionManager.getString(getContext(), "god_id"));
                            jsonObject.put("containerno", binding.edit.getText().toString());
                            jsonObject.put("supplierreelno", strBarcode);
                            jsonObject.put("receipt_wt", binding.txtReceipt.getText().toString());
                            jsonObject.put("pressreelno", binding.RealNo.getText().toString());
                            //     jsonObject.put("reel_gsm",reel_gsm);

                            jsonObject.put("reel_gsm", binding.txtGsm.getText().toString());
                            jsonObject.put("reel_mm", binding.txtmm.getText().toString());
                            jsonObject.put("depthcut", binding.etDepth.getText().toString());
                            jsonObject.put("damagereason", DepthReason);
                            jsonObject.put("Userid", SessionManager.getString(getApplicationContext(), "user_id"));
                            postPrsentetr.PostScanReel(MainActivity.this, jsonObject);
                            //  binding.tvCounter.setText("0");
                            // counter = counter + 1;
                            // binding.tvCounter.setText(String.valueOf(counter));

                            binding.txtGsm.setText("");
                            binding.txtGrossW.setText("");
                            binding.txtNetWeight.setText("");
                            binding.txtSuppierNo.setText("");
                            binding.txtReceipt.setText("");
                            binding.txtmm.setText("");
                            binding.etDepth.setText("");
                            binding.RealNo.setText("");

                            // presenterr.BarcodeList(LoginActivity.this,jsonObject);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        NetworkAlertUtility.showNetworkFailureAlert(this);
                    }
                }

                break;
//            case R.id.txtView:
//                SessionManager.clearAll(MainActivity.this);
//                startActivity(new Intent(MainActivity.this,LoginActivity.class));
//                finishAffinity();
//                break;

                case R.id.img:

                    new LovelyStandardDialog(this, LovelyStandardDialog.ButtonLayout.HORIZONTAL)
                            .setTopColorRes(R.color.main4)
                            .setButtonsColorRes(R.color.colorAccent)
                            .setMessageGravity(20)
                            .setIcon(R.drawable.ic_exit_to_app_black_24dp)
                            .setTitle("Logout?")
                            .setMessage("Are you sure")
                            .setPositiveButton("yes", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SessionManager.clearAll(MainActivity.this);
                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                    finishAffinity();
                                    //finish();
                                    //  Toast.makeText(context, "positive clicked", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                    break;

                case R.id.viewall:
//                    if(binding.edit.length()>1) {
//
                    startActivity(new Intent(MainActivity.this, ActivityDetail.class));
//
//                    }else{
//                        Toast.makeText(getApplicationContext(),"Enter Container No",Toast.LENGTH_LONG).show();
//                    }
                    break;
                case R.id.remaning:

                    startActivity(new Intent(MainActivity.this, AllReel.class));

                    break;
            }

        } else {

            NetworkAlertUtility.showNetworkFailureAlert(this);

        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBarCodeSuccess(BarcodeDetails body) {
        if (body != null) {

            try {

                binding.txtSuppierNo.setText(body.getResponse().getData().getSupplierreelno());
                //  binding.txtSuppierNo.setBackground(getResources().getDrawable(R.color.main1));
                binding.txtNetWeight.setText("" + body.getResponse().getData().getSupp_net_wt());
                //binding.txtNetWeight.setBackground(getResources().getDrawable(R.color.main1));
                binding.txtGrossW.setText("" + body.getResponse().getData().getSupp_gross_wt());
                //binding.txtGrossW.setBackground(getResources().getDrawable(R.color.main1));
                binding.txtReceipt.setText("" + body.getResponse().getData().getSupp_net_wt());
                binding.txtGsm.setText("" + body.getResponse().getData().getReel_gsm());
                binding.txtmm.setText("" + body.getResponse().getData().getReel_mm());
                 binding.RealNo.setText(""+body.getResponse().getData().getPressreelno());
                // receipt_wt = String.valueOf(body.getResponse().getData().getSupp_net_wt());
                reel_gsm = String.valueOf(body.getResponse().getData().getReel_gsm());
                reel_mm = String.valueOf(body.getResponse().getData().getReel_mm());
                SessionManager.con_no(getApplicationContext(), body.getResponse().getData().getContainerno());
                SessionManager.sup_no(getApplicationContext(), body.getResponse().getData().getSupplierreelno());

             try {

                 final Toast toast = Toast.makeText(getApplicationContext(), " Match Data", Toast.LENGTH_SHORT);
                 toast.setGravity(Gravity.CENTER, 0, 0);
                 View view = toast.getView();
                 ViewGroup group = (ViewGroup) toast.getView();
                 TextView messageTextView = (TextView) group.getChildAt(0);
                 messageTextView.setTextSize(20);
                 messageTextView.setTextColor(Color.WHITE);
                 toast.getView().setBackgroundColor(Color.parseColor("#7ED618"));
                 toast.show();

             } catch (Exception e){
                 e.printStackTrace();
             }
               // Toast.makeText(getApplicationContext(),"Match Data !",Toast.LENGTH_SHORT).show();
             //   TastyToast.makeText(getApplicationContext(), "Match Data !", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS).setGravity(Gravity.CENTER, 0, 0);
                binding.edit.setEnabled(false);
                //  binding.edit.setFocusableInTouchMode(false);
                //   binding.edit.setFocusable(false);
                binding.edit.setBackgroundColor(Color.parseColor("#D5D6DA"));
                //  binding.edit.setBackgroundColor(Integer.parseInt("#4BB6BDBD"));

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            //

            // showDialog();
//           Log.e("dataaa",""+body.getResponse().getData());
            // Toast.makeText(getApplicationContext(),"Match",Toast.LENGTH_SHORT).show();
        }


        if (body != null && body.getResponse().getData() == null) {
            // TastyToast.makeText(getApplicationContext(), "Mis Match Data", TastyToast.LENGTH_LONG, TastyToast.ERROR).setGravity(Gravity.CENTER, 0, 0);

            final Toast toast = Toast.makeText(getApplicationContext(), "Mis Match Data", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            View view =toast.getView();
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(20);
            messageTextView.setTextColor(Color.WHITE);
            toast.getView().setBackgroundColor(Color.parseColor("#D51026"));
            toast.show();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //toast.cancel();
                }
            }, 300000);
            //    TastyToast.makeText(getApplicationContext(), "Mis Match Data", 5500, TastyToast.ERROR).setGravity(Gravity.CENTER, 0, 0);

            // Set the countdown to display the toast

        }
    }
    //   showDialog1();

//            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onPostData(UpdateData body) {
        if (body != null) {
            //showDialog();
            //  Toast.makeText(getApplicationContext(),""+body.getResponse().getData(),Toast.LENGTH_SHORT).show();
          // TastyToast.makeText(getApplicationContext(), "Updated Successfully !", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS).setGravity(Gravity.CENTER, 0, 0);

            try {
                final Toast toast = Toast.makeText(getApplicationContext(), "Updated Successfully !", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                View view = toast.getView();
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(20);
                messageTextView.setTextColor(Color.BLACK);
                toast.getView().setBackgroundColor(Color.parseColor("#7ED618"));
                toast.show();
            } catch (Exception e){
                e.printStackTrace();
            }

            binding.count.setText("" + body.getResponse().getData());
            // tastyToast.setGravity(Gravity.CENTER, 0, 0);
            //tastyToast.show();
        } else {
            Toast.makeText(getApplicationContext(), "" + body.getResponse().getData(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.Depth:
                DepthReason = list.get(i).getReason_Code();
                //  item =list.get(i).toString("Resson");
                // item = list.get(i).toString("De");
                item = list.get(i).getReason_Name();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void OnFirstDrop(DepthReason defaultResponse) {
        list.clear();
        if (defaultResponse.getResponse() != null) {
            list.addAll(defaultResponse.getResponse().getData());
            Log.e("my_list", list.toString());
            binding.Depth.setOnItemSelectedListener(this);
            AdapterDepthReason adapterDepthReason = new AdapterDepthReason(getApplicationContext(), list);
            binding.Depth.setAdapter(adapterDepthReason);
        }

        binding.finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edit.setText("");
                binding.count.setText("");
                binding.edit.setEnabled(true);
                binding.edit.setBackgroundColor(Color.parseColor("#ffffff"));
                //  binding.edit.isFocusableInTouchMode();

                // binding.edit.setBackgroundColor(Integer.parseInt(""));
            }
        });

        callgodamapi();
    }

    private void callgodamapi() {
        if (NetworkAlertUtility.isConnectingToInternet2(this)) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject();
                jsonObject.put("centcode", godam);
                jsonObject.put("centname","");
                depthReasonPresenter.GetGodown(MainActivity.this, jsonObject);
                // presenterr.BarcodeList(LoginActivity.this,jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            NetworkAlertUtility.showNetworkFailureAlert(this);
        }
    }

    @Override
    public void onSecondDrop(DropGodownList defaultResponse) {
        if (defaultResponse.getResponse()!=null) {
            godList.clear();
            try {
                godList.addAll(defaultResponse.getResponse().getData());
                AdapterGodwoen customAdapter=new AdapterGodwoen (getApplicationContext(),godList);
                binding.spinn.setAdapter(customAdapter);
                binding.spinn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        //  Toast.makeText(getApplicationContext(),""+list.get(arg2).centcode,Toast.LENGTH_SHORT).show();
                            String id= godList.get(arg2).getGodowncode();
//                          Toast.makeText(getApplfvicationContext(),""+godList.get(arg2).getGodowncode(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub.

                    }

                });
                Log.e("yyyyy",""+godList);
            }
            catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

}