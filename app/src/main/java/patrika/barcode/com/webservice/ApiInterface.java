package patrika.barcode.com.webservice;

import java.lang.ref.Reference;
import java.util.HashMap;
import java.util.Map;
import okhttp3.RequestBody;
import patrika.barcode.com.model.BarcodeDetails;
import patrika.barcode.com.model.DepthList;
import patrika.barcode.com.model.DepthReason;
import patrika.barcode.com.model.DepthReel;
import patrika.barcode.com.model.DropDownList;
import patrika.barcode.com.model.DropGodownList;
import patrika.barcode.com.model.LoginResponse;
import patrika.barcode.com.model.ModelDeatails;
import patrika.barcode.com.model.UpdateData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface ApiInterface {

    String BASE_URL = "http://barcode.wfhrpn.in/api/";
 //  String BASE_URL = "http://10.30.8.66/website/api/";
   //     String BASE_URL = "http://10.30.8.66:8089/api/";
    //String BASE_URL = "http://app.audience.events/webservices/";

    String Values= "Login";
    String CategoryList = "Values";
    String BarcodeList   ="GetReelDetail";
    String Login  = "Unit";
    String Godam  = "Godown";
    String PostScanReel  = "PostScanReel";
    String GetAllContainerReel  = "GetAllContainerReel";
    String GetDepthReason  = "GetDepthReason";
    String GetAllPendingReel  = "GetAllPendingReel";
   /* String FORGETPASSWORD = "forgotpassword";
    String REGISTER = "register";
    String LOGOUT = "logout";
    String CHANGEPASSWORD = "changePassword";
    String EDITPROFILE = "editSetting";
    String VIEWPROFILE = "viewProfile";
    String CATEGORYLIST = "categoryList";
    String EVENTLIST = "eventList";*/

   // @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @POST(Values)
    Call<LoginResponse> Login(@Body RequestBody params);

    @GET(CategoryList)
    Call<ModelDeatails> Values();

    @POST(BarcodeList)
    Call<BarcodeDetails> GetReelDetail(@Body RequestBody params);

    @GET(Login)
    Call<DropDownList> Unit();

    @POST(Godam)
    Call<DropGodownList> Godown(@Body RequestBody params);


    @POST(PostScanReel)
    Call<UpdateData> PostScanReel(@Body RequestBody params);

    @GET(GetDepthReason)
    Call<DepthReason> GetDepthReason();


    @POST(GetAllContainerReel)
    Call<DepthList> GetAllContainerReel(@Body  RequestBody requestBody);

    @POST(GetAllPendingReel)
    Call<DepthReel> GetAllPendingReel(@Body  RequestBody requestBody);

}