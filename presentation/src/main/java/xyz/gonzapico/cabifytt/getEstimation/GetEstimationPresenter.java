package xyz.gonzapico.cabifytt.getEstimation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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

  private Collection<EstimateVechicle> listOfVehiclesResult = Collections.emptyList();

  @Inject public GetEstimationPresenter(@Named("estimation") BaseUseCase useCase,
      DomainEstimationMapper domainFrontPageNewsMapper) {
    this.mGetEstimationUseCase = useCase;
    this.mDomainEstimationMapper = domainFrontPageNewsMapper;
  }

  public void setEstimationView(EstimationView estimationView) {
    mEstimationView = estimationView;
  }

  public void getEstimation(RequestStops stopsRequested) {
    GetEstimationPresenter.this.mEstimationView.showLoading();
    try {
      ((GetEstimation) this.mGetEstimationUseCase).setBodyRequestStops(
          this.mDomainEstimationMapper.trasnformToBodyRequest(stopsRequested));
      this.mGetEstimationUseCase.execute(new EstimationSuscriber());
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

  private void renderListOfEstimateVehicles(Collection<EstimateVechicle> estimateVehicleList) {
    mEstimationView.renderResults((ArrayList<EstimateVechicle>) estimateVehicleList);
  }

  private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
    mEstimationView.showError(defaultErrorBundle.getErrorMessage());
  }

  public void orderByPrice() {
    Collections.sort((ArrayList<EstimateVechicle>) listOfVehiclesResult,
        new Comparator<EstimateVechicle>() {
          @Override public int compare(EstimateVechicle o1, EstimateVechicle o2) {
            if (o1.getTotalPrice() > o2.getTotalPrice()) {
              return 1;
            } else if (o2.getTotalPrice() > o1.getTotalPrice()) return -1;
            return 0;
          }
        });
    mEstimationView.renderResults((ArrayList<EstimateVechicle>) listOfVehiclesResult);
  }

  private final class EstimationSuscriber
      extends DefaultSubscriber<List<DomainModelEstimateVehicle>> {

    @Override public void onCompleted() {
      GetEstimationPresenter.this.mEstimationView.hideLoading();
    }

    @Override public void onError(Throwable e) {
      GetEstimationPresenter.this.mEstimationView.hideLoading();
      GetEstimationPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
    }

    @Override public void onNext(List<DomainModelEstimateVehicle> estimationVehicleList) {
      listOfVehiclesResult =
          mDomainEstimationMapper.transformListOfEstimationVehicle(estimationVehicleList);
      GetEstimationPresenter.this.renderListOfEstimateVehicles(listOfVehiclesResult);
      GetEstimationPresenter.this.mEstimationView.hideLoading();
    }
  }
}