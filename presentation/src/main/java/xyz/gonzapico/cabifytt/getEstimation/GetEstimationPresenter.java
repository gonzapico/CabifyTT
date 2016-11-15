package xyz.gonzapico.cabifytt.getEstimation;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import xyz.gonzapico.cabifytt.PickActivity;
import xyz.gonzapico.cabifytt.di.PerActivity;
import xyz.gonzapico.cabifytt.getEstimation.mapper.DomainEstimationMapper;
import xyz.gonzapico.cabifytt.getEstimation.model.EstimateVechicle;
import xyz.gonzapico.cabifytt.getEstimation.model.RequestStops;
import xyz.gonzapico.cabifytt.getEstimation.model.Stop;
import xyz.gonzapico.domain.exception.DefaultErrorBundle;
import xyz.gonzapico.domain.interactor.BaseUseCase;
import xyz.gonzapico.domain.interactor.DefaultSubscriber;
import xyz.gonzapico.domain.interactor.GetEstimation;
import xyz.gonzapico.domain.model.DomainModelEstimateVehicle;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by gfernandez on 8/11/16.
 */

@PerActivity public class GetEstimationPresenter {
  private final int NUMBER_OF_STOPS_OF_REQUEST = 2;
  public boolean suggestedStartSearch = false;
  public boolean suggestedEndSearch = false;
  private DomainEstimationMapper mDomainEstimationMapper;
  private BaseUseCase mGetEstimationUseCase;
  private EstimationView mEstimationView;
  private RequestStops stopsRequest;
  private List<Stop> stopList = new ArrayList<>();
  private Collection<EstimateVechicle> listOfVehiclesResult = Collections.emptyList();

  @Inject public GetEstimationPresenter(@Named("estimation") BaseUseCase useCase,
      DomainEstimationMapper domainFrontPageNewsMapper) {
    this.mGetEstimationUseCase = useCase;
    this.mDomainEstimationMapper = domainFrontPageNewsMapper;

    this.stopsRequest = new RequestStops();
  }

  public void setEstimationView(EstimationView estimationView) {
    mEstimationView = estimationView;
  }

  public void getEstimation() {
    mEstimationView.hideKeyboard();
    mEstimationView.hideResults();
    if (stopsAreCorrect()) {
      GetEstimationPresenter.this.mEstimationView.showLoading();
      makeGetEstimationRequest();
    } else {
      Exception stopsException = new Exception("Debe seleccionar origen y destino debidamente");
      showErrorMessage(new DefaultErrorBundle(stopsException));
    }
  }

  public boolean stopsAreCorrect() {
    if ((this.stopsRequest.getStops().size() < NUMBER_OF_STOPS_OF_REQUEST) || (areStopsNull())) {
      return false;
    }

    return true;
  }

  private boolean areStopsNull() {
    for (int i = 0; i < this.stopsRequest.getStops().size(); i++) {
      if (this.stopsRequest.getStops().get(i) == null) return true;
    }

    return false;
  }

  public void makeGetEstimationRequest() {

    try {
      ((GetEstimation) this.mGetEstimationUseCase).setBodyRequestStops(
          this.mDomainEstimationMapper.trasnformToBodyRequest(this.stopsRequest));
      this.mGetEstimationUseCase.execute(new EstimationSuscriber());
    } catch (Exception e) {
      GetEstimationPresenter.this.showErrorMessage(new DefaultErrorBundle(e));
    }

    this.stopsRequest.getStops().clear();
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

  public void orderByPrice(boolean asc) {
    if (asc) {
      Collections.sort((ArrayList<EstimateVechicle>) listOfVehiclesResult,
          new Comparator<EstimateVechicle>() {
            @Override public int compare(EstimateVechicle o1, EstimateVechicle o2) {
              if (o1.getTotalPrice() < o2.getTotalPrice()) {
                return 1;
              } else if (o2.getTotalPrice() < o1.getTotalPrice()) return -1;
              return 0;
            }
          });
    } else {
      Collections.sort((ArrayList<EstimateVechicle>) listOfVehiclesResult,
          new Comparator<EstimateVechicle>() {
            @Override public int compare(EstimateVechicle o1, EstimateVechicle o2) {
              if (o1.getTotalPrice() > o2.getTotalPrice()) {
                return 1;
              } else if (o2.getTotalPrice() > o1.getTotalPrice()) return -1;
              return 0;
            }
          });
    }
    mEstimationView.renderResults((ArrayList<EstimateVechicle>) listOfVehiclesResult);
  }

  public void addStop(Place place) {
    Stop start = new Stop();
    List<Double> locationStartList = new ArrayList<>();
    locationStartList.add(place.getLatLng().latitude);
    locationStartList.add(place.getLatLng().longitude);
    start.setLocationList(locationStartList);
    start.setHitAt(Utils.currentDateFormatted());
    start.setName(place.getName().toString());
    start.setAddr(place.getAddress().toString());
    String[] partsOfAddress = place.getAddress().toString().split(",");
    try {
      start.setCity(partsOfAddress[partsOfAddress.length - 2].trim().split(" ")[1]);
    } catch (Exception e) {
      try {
        start.setCity(partsOfAddress[partsOfAddress.length - 2].trim().split(" ")[0]);
      } catch (Exception exc) {
        start.setCity("");
      }
    }
    String num = "";
    try {
      int number = Integer.parseInt(partsOfAddress[1].trim());
      num = number + "";
    } catch (Exception e) {
      num = "S/N";
    }
    start.setNum(num);
    try {
      start.setCountry(partsOfAddress[partsOfAddress.length - 1].split(" ")[1]);
    } catch (IndexOutOfBoundsException e) {
      start.setCountry(partsOfAddress[partsOfAddress.length - 1].split(" ")[0]);
    }
    stopList.add(start);
    if (stopList.size() == 2) stopsRequest.setStops(stopList);
  }

  private void clearRequest() {
    stopsRequest = null;
    stopsRequest = new RequestStops();
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
      Place place = PlaceAutocomplete.getPlace((Activity) mEstimationView, data);
      addStop(place);
      switch (requestCode) {
        case Utils.PLACE_AUTOCOMPLETE_REQUEST_CODE_START:
          mEstimationView.updateOrigin(place.getName().toString());
          break;
        case Utils.PLACE_AUTOCOMPLETE_REQUEST_CODE_END:
          mEstimationView.updateDestiny(place.getName().toString());
          break;
      }
    } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
      Status status = PlaceAutocomplete.getStatus((Activity) mEstimationView, data);
      GetEstimationPresenter.this.mEstimationView.showError(status.getStatusMessage());
    } else if (resultCode == RESULT_CANCELED) {
      // The user canceled the operation.
      GetEstimationPresenter.this.mEstimationView.showError("Petición cancelada.");
    }
  }

  public void suggestSearchPlace(boolean typeOfSearch, int requestCodeForResult,
      PickActivity pickActivity) {
    if (!typeOfSearch) {
      try {
        Intent intent =
            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(
                pickActivity);
        pickActivity.startActivityForResult(intent, requestCodeForResult);
      } catch (GooglePlayServicesRepairableException e) {
        pickActivity.showError(e.getMessage());
      } catch (GooglePlayServicesNotAvailableException e) {
        pickActivity.showError(e.getMessage());
      }
      if (requestCodeForResult == Utils.PLACE_AUTOCOMPLETE_REQUEST_CODE_START) {
        suggestedStartSearch = true;
        suggestedEndSearch = false;
      } else {
        suggestedStartSearch = false;
        suggestedEndSearch = true;
      }
    }
  }

  private final class EstimationSuscriber
      extends DefaultSubscriber<List<DomainModelEstimateVehicle>> {

    @Override public void onCompleted() {
      GetEstimationPresenter.this.mEstimationView.hideLoading();
      GetEstimationPresenter.this.clearRequest();
    }

    @Override public void onError(Throwable e) {
      GetEstimationPresenter.this.mEstimationView.hideLoading();
      GetEstimationPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
      GetEstimationPresenter.this.mEstimationView.hideResults();
    }

    @Override public void onNext(List<DomainModelEstimateVehicle> estimationVehicleList) {
      listOfVehiclesResult =
          mDomainEstimationMapper.transformListOfEstimationVehicle(estimationVehicleList);
      if (listOfVehiclesResult.size() > 0) {
        GetEstimationPresenter.this.renderListOfEstimateVehicles(listOfVehiclesResult);
        GetEstimationPresenter.this.mEstimationView.showResults();
      } else {
        Exception noEstimateVehiclesException =
            new Exception("No hay Cabify disponibles para el trayecto que solicita");
        GetEstimationPresenter.this.showErrorMessage(
            new DefaultErrorBundle(noEstimateVehiclesException));
      }
    }
  }
}