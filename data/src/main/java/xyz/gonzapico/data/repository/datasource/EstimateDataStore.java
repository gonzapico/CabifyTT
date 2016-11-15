package xyz.gonzapico.data.repository.datasource;

import java.util.List;
import retrofit2.Response;
import rx.Observable;
import xyz.gonzapico.data.entity.EstimateVehicle;
import xyz.gonzapico.data.entity.StopsBodyRequest;

/**
 * Created by gfernandez on 16/08/16.
 */
public interface EstimateDataStore {

  /**
   * Get an {@link Observable} which will emit a List of {@link EstimateVehicle}.
   */
  Observable<Response<List<EstimateVehicle>>> estimationVehicleList(StopsBodyRequest body);
}
