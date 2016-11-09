package xyz.gonzapico.cabifytt.getEstimation;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import xyz.gonzapico.cabifytt.di.PerActivity;
import xyz.gonzapico.cabifytt.getEstimation.mapper.DomainEstimationMapper;
import xyz.gonzapico.cabifytt.getEstimation.model.EstimateVechicle;
import xyz.gonzapico.cabifytt.getEstimation.model.RequestStops;
import xyz.gonzapico.domain.exception.DefaultErrorBundle;
import xyz.gonzapico.domain.interactor.BaseUseCase;
import xyz.gonzapico.domain.interactor.DefaultSubscriber;
import xyz.gonzapico.domain.interactor.GetEstimation;
import xyz.gonzapico.domain.model.DomainModelEstimateVehicle;

/**
 * Created by gfernandez on 8/11/16.
 */

@PerActivity public class GetEstimationPresenter {
  private DomainEstimationMapper mDomainEstimationMapper;

  private BaseUseCase mGetEstimationUseCase;

  private EstimationView mEstimationView;

  @Inject public GetEstimationPresenter(@Named("estimation") BaseUseCase useCase,
      DomainEstimationMapper domainFrontPageNewsMapper) {
    this.mGetEstimationUseCase = useCase;
    this.mDomainEstimationMapper = domainFrontPageNewsMapper;
  }

  public void setView(EstimationView estimationView) {
    mEstimationView = estimationView;
  }

  public void getEstimation(RequestStops stopsRequested) {
    try {
      ((GetEstimation) this.mGetEstimationUseCase).setBodyRequestStops(this.mDomainEstimationMapper.trasnformToBodyRequest(stopsRequested));
      this.mGetEstimationUseCase.execute(new NewsSuscriber());
    } catch (Exception e) {
      GetEstimationPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
    }
  }

  public void resume() {
  }

  public void pause() {
  }

  public void destroy() {
    this.mGetEstimationUseCase.unsubscribe();
  }

  private void renderListOfEstimateVehicles(List<EstimateVechicle> estimateVehicleList) {
    mEstimationView.renderResults(estimateVehicleList);
  }

  private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
    mEstimationView.showError(defaultErrorBundle.getErrorMessage());
  }

  private final class NewsSuscriber extends DefaultSubscriber<List<DomainModelEstimateVehicle>> {

    @Override public void onCompleted() {
    }

    @Override public void onError(Throwable e) {
      GetEstimationPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
    }

    @Override public void onNext(List<DomainModelEstimateVehicle> estimationVehicleList) {
      GetEstimationPresenter.this.renderListOfEstimateVehicles(
          mDomainEstimationMapper.transformListOfEstimationVehicle(estimationVehicleList));
      GetEstimationPresenter.this.mEstimationView.hideLoading();
    }
  }
}