package patrika.barcode.com.webservice;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import static patrika.barcode.com.activity.AppController.getInstance;

public class RestService {

//    private static Retrofit retrofit;

  /*  public RestService() {

        retrofit = new Retrofit.Builder()
                .baseUrl(AppURL.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }*/

    private static OkHttpClient getRequestHeader() {
        return new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(6, TimeUnit.MINUTES)
                .build();
    }

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

        Gson gson = new GsonBuilder().create();

//        File httpCacheDirectory = new File(getCacheDir(), "cache_file");
//        Cache cache = new Cache(httpCacheDirectory, 20 * 1024 * 1024);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .connectionPool(new ConnectionPool(0, 5 * 60 * 1000, TimeUnit.SECONDS))
                .addInterceptor(new CustomInterceptor(getInstance(), Locale.getDefault().getLanguage(), ""))
//                .cache(cache)
                .build();


        //init retrofit
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(endPoint)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(clazz);
        /*
         *//*Retrofit*//*
        Retrofit retrofit = (new Retrofit.Builder())
                .baseUrl(endPoint)
                .client(getRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//        T service = retrofit.create(clazz);

//        AppLogger.d("RestService", "response service:- " + service);

        return retrofit.create(clazz);*/
    }


    /*
     *  Sending parameter in raw in JSON String
     * */

    public static RequestBody requestBodyJsonObject(String jsonParams) {

//        Log.d("RestService", "" + jsonParams);

        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonParams);
    }

}