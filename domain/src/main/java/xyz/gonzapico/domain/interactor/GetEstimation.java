package xyz.gonzapico.domain.interactor;

import javax.inject.Inject;
import rx.Observable;
import xyz.gonzapico.domain.executor.PostExecutionThread;
import xyz.gonzapico.domain.executor.ThreadExecutor;
import xyz.gonzapico.domain.model.DomainModelBodyRequestStops;
import xyz.gonzapico.domain.repository.EstimateDomainRepository;

/**
 * Created by gfernandez on 7/11/16.
 */

public class GetEstimation extends BaseUseCase {
  private final EstimateDomainRepository mRepository;
  private DomainModelBodyRequestStops mBodyRequestStops;

  @Inject public GetEstimation(EstimateDomainRepository frontPageNewsRepository,
      ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mRepository = frontPageNewsRepository;
  }

  public void setBodyRequestStops(DomainModelBodyRequestStops bodyRequestStops) {
    this.mBodyRequestStops = bodyRequestStops;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.mRepository.getEstimationVehicleList(mBodyRequestStops);
  }
}
