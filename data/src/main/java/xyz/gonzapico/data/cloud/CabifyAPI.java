package xyz.gonzapico.data.cloud;

import java.util.List;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import xyz.gonzapico.data.entity.EstimateVehicle;
import xyz.gonzapico.data.entity.StopsBodyRequest;

/**
 * Created by gfernandez on 2/11/16.
 */

public interface CabifyAPI {
  @Headers({
      "Content-type: application/json",
      "Authorization: Bearer 6o_FrppOEQ5RrCkBOEKaBM-puJleMKrRn5nW_cy7H9Y", "Accept-Language: en"
  }) @POST("/api/v2/estimate") rx.Observable<Response<List<EstimateVehicle>>> estimate(
      @Body StopsBodyRequest body);
}
