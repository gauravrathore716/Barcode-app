package patrika.barcode.com.activity;


import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import patrika.barcode.com.webservice.ApiInterface;
import patrika.barcode.com.webservice.CustomInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    private ApiInterface service;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        service = createRetrofitService(ApiInterface.class, ApiInterface.BASE_URL);
//        mSharedPreferences = this.getSharedPreferences(AppConstants.sharedPrefName, Context.MODE_PRIVATE);
    }

    public <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

        Gson gson = new GsonBuilder().create();

        File httpCacheDirectory = new File(getCacheDir(), "cache_file");
        Cache cache = new Cache(httpCacheDirectory, 20 * 1024 * 1024);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .connectionPool(new ConnectionPool(0, 5 * 60 * 1000, TimeUnit.SECONDS))
                .addInterceptor(new CustomInterceptor(getInstance(), Locale.getDefault().getLanguage(), ""))
                .cache(cache)
                .build();


        //init retrofit
        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl(endPoint)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(clazz);
    }


    public ApiInterface getApiInterface() {

        return service;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
