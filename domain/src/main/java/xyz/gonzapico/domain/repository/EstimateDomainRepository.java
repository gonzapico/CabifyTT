package xyz.gonzapico.domain.repository;

import java.util.List;
import rx.Observable;
import xyz.gonzapico.domain.model.DomainModelBodyRequestStops;
import xyz.gonzapico.domain.model.DomainModelEstimateVehicle;

/**
 * Created by gfernandez on 3/11/16.
 */

public interface EstimateDomainRepository {

  Observable<List<DomainModelEstimateVehicle>> getEstimationVehicleList(
      DomainModelBodyRequestStops bodyRequestStops);
}
