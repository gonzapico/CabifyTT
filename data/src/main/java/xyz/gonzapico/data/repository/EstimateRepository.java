package xyz.gonzapico.data.repository;

import javax.inject.Inject;
import xyz.gonzapico.data.entity.mapper.EstimateDataMapper;
import xyz.gonzapico.data.repository.datasource.EstimateDataStoreFactory;
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

  @Override public void getEstimationVehicelList() {

  }
}
