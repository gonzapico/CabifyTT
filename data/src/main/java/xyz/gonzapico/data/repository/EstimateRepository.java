package xyz.gonzapico.data.repository;

import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.exceptions.Exceptions;
import xyz.gonzapico.data.entity.mapper.EstimateDataMapper;
import xyz.gonzapico.data.repository.datasource.EstimateDataStore;
import xyz.gonzapico.data.repository.datasource.EstimateDataStoreFactory;
import xyz.gonzapico.domain.model.DomainModelBodyRequestStops;
import xyz.gonzapico.domain.model.DomainModelEstimateVehicle;
import xyz.gonzapico.domain.repository.EstimateDomainRepository;

/**
 * Created by gfernandez on 3/11/16.
 */

public class EstimateRepository implements EstimateDomainRepository {

  private final EstimateDataStoreFactory estimateDataStoreFactory;
  private final EstimateDataMapper estimateDataMapper;

  @Inject public EstimateRepository(EstimateDataStoreFactory estimateDataStoreFactory,
      EstimateDataMapper estimateDataMapper) {
    this.estimateDataStoreFactory = estimateDataStoreFactory;
    this.estimateDataMapper = estimateDataMapper;
  }

  @Override public Observable<List<DomainModelEstimateVehicle>> getEstimationVehicleList(
      DomainModelBodyRequestStops bodyRequest) {
    /**
     * First of all we need to transform the {@link url} of the new to let the app knows what type of new has to request to the API
     */
    final EstimateDataStore estimateDataStore =
        this.estimateDataStoreFactory.createCloudDataStore();
    return estimateDataStore.estimationVehicleList(
        estimateDataMapper.transformToDataBodyRequest(bodyRequest)).map(listResponse -> {
      try {
        return this.estimateDataMapper.transformToDomainVehicleList(listResponse);
      } catch (Throwable throwable) {
        throw Exceptions.propagate(throwable);
      }
    });
  }
}
