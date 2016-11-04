package xyz.gonzapico.data.cloud;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xyz.gonzapico.data.entity.EstimateVehicle;
import xyz.gonzapico.data.entity.StopsBodyRequest;

/**
 * Created by gfernandez on 2/11/16.
 */

public interface CabifyAPI {
  @POST("/api/v2/estimate") rx.Observable<Response<EstimateVehicle>> estimate(
      @Body StopsBodyRequest body);
}
