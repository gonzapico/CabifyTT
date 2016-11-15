package xyz.gonzapico.data.di;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.gonzapico.data.cloud.CabifyAPI;

/**
 * Created by gfernandez on 28/07/16.
 */
@Module public class CloudModule {

  String mBaseUrl;
  Context context;

  // Constructor needs one parameter to instantiate.
  public CloudModule(String baseUrl, Context context) {
    this.mBaseUrl = baseUrl;
    this.context = context;
  }

  @Provides @Singleton Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return gsonBuilder.create();
  }

  @Provides @Singleton Retrofit provideRetrofit(Gson gson, OkHttpClient.Builder okHttpBuilder) {

    Retrofit retrofit =
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(mBaseUrl)
            .client(okHttpBuilder.build())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    retrofit.create(CabifyAPI.class);

    return retrofit;
  }

  @Provides @Singleton Cache provideOkHttpCache() {
    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    Cache cache = new Cache(this.context.getCacheDir(), cacheSize);
    return cache;
  }

  @Provides @Singleton OkHttpClient.Builder provideOkHttpClient(Cache cache) {
    OkHttpClient.Builder client = new OkHttpClient.Builder();

    // Logging porpouses
    //HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    //logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    //client.addInterceptor(logging);
    //client.cache(cache);
    return client;
  }

  /**
   * Checks if the device has any active internet connection.
   *
   * @return true device with internet connection, otherwise false.
   */
  private boolean isThereInternetConnection() {
    boolean isConnected;

    ConnectivityManager connectivityManager =
        (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
    isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

    return isConnected;
  }
}
