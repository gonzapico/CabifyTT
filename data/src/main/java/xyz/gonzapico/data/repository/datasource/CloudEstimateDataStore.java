package xyz.gonzapico.data.repository.datasource;

import android.content.Context;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import xyz.gonzapico.data.Config;
import xyz.gonzapico.data.cloud.CabifyAPI;
import xyz.gonzapico.data.di.CloudModule;
import xyz.gonzapico.data.di.DaggerCloudComponent;
import xyz.gonzapico.data.entity.EstimateVehicle;
import xyz.gonzapico.data.entity.StopsBodyRequest;

/**
 * Created by gfernandez on 16/08/16.
 */
public class CloudEstimateDataStore implements EstimateDataStore {

  @Inject Retrofit retrofit;
  private CabifyAPI restApi;
  private Context context;

  /**
   * Construct a {@link EstimateDataStore} based on connections to the api (Cloud).
   */
  @Inject public CloudEstimateDataStore(Context context) {
    this.context = context;
    initDagger();
    initRetrofit();
  }

  private void initRetrofit() {
    restApi = retrofit.create(CabifyAPI.class);
  }

  @Override public Observable<Response<List<EstimateVehicle>>> estimationVehicleList(StopsBodyRequest bodyOfRequest) {
    return restApi.estimate(bodyOfRequest);
  }

  private void initDagger() {
    DaggerCloudComponent.builder()
        // list of modules that are part of this component need to be created here too
        .cloudModule(new CloudModule(Config.API_URL_BASE, this.context)).build().inject(this);
  }
}
