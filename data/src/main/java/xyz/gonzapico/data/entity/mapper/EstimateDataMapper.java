package xyz.gonzapico.data.entity.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Response;
import xyz.gonzapico.data.entity.EstimateVehicle;
import xyz.gonzapico.data.entity.Icons;
import xyz.gonzapico.data.entity.Stop;
import xyz.gonzapico.data.entity.StopsBodyRequest;
import xyz.gonzapico.data.entity.VehicleType;
import xyz.gonzapico.domain.model.DomainModelBodyRequestStops;
import xyz.gonzapico.domain.model.DomainModelEstimateVehicle;
import xyz.gonzapico.domain.model.DomainModelLocation;
import xyz.gonzapico.domain.model.DomainModelStop;
import xyz.gonzapico.domain.model.DomainVehicleType;

/**
 * Created by gfernandez on 3/11/16.
 */

@Singleton public class EstimateDataMapper {

  @Inject public EstimateDataMapper() {
  }

  public StopsBodyRequest transformToDataBodyRequest(DomainModelBodyRequestStops bodyRequest) {
    StopsBodyRequest bodyRequestResult = new StopsBodyRequest();
    bodyRequestResult.stops = transformStops(bodyRequest.getStops());
    bodyRequestResult.startAt = bodyRequest.getStartAt();

    return bodyRequestResult;
  }

  private List<Stop> transformStops(List<DomainModelStop> stops) {
    List<Stop> stopsResult = new ArrayList<>();
    for (DomainModelStop domainModelStop : stops) {
      stopsResult.add(transformStop(domainModelStop));
    }
    return stopsResult;
  }

  private Stop transformStop(DomainModelStop domainModelStop) {
    Stop stopResult = new Stop();

    stopResult.loc = transformLocation(domainModelStop.getLocationList());

    return stopResult;
  }

  private List<Long> transformLocation(List<DomainModelLocation> locationList) {
    List<Long> floatListResult = new ArrayList<>();
    for (DomainModelLocation domainModelLocation : locationList) {
      floatListResult.add(domainModelLocation.getLatitude());
    }

    return floatListResult;
  }

  public List<DomainModelEstimateVehicle> transformToDomainVehicleList(
      Response<List<EstimateVehicle>> listResponse) {
    List<DomainModelEstimateVehicle> resultOfTransformation = new ArrayList<>();

    for (EstimateVehicle estimateVehicle : listResponse.body()){
      DomainModelEstimateVehicle domainModelEstimateVehicle = new DomainModelEstimateVehicle();

      domainModelEstimateVehicle.setCurrency(estimateVehicle.getCurrency());
      domainModelEstimateVehicle.setCurrencySymbol(estimateVehicle.getCurrencySymbol());
      domainModelEstimateVehicle.setPriceFormatted(estimateVehicle.getPriceFormatted());
      domainModelEstimateVehicle.setTotalPrice(estimateVehicle.getTotalPrice());
      domainModelEstimateVehicle.setVehicleType(transformVehicleType(estimateVehicle.getVehicleType()));
      resultOfTransformation.add(domainModelEstimateVehicle);
    }

    return resultOfTransformation;
  }

  private DomainVehicleType transformVehicleType(VehicleType vehicleType) {
    DomainVehicleType resultOfTransformation = new DomainVehicleType();

    // TODO left fields
    if (vehicleType != null) {
      resultOfTransformation.setCurrency(vehicleType.getCurrency());
      resultOfTransformation.setAsapOnly(vehicleType.getAsapOnly());
      resultOfTransformation.setDescription(vehicleType.getDescription());
      resultOfTransformation.setIcon(vehicleType.getIcon());
      resultOfTransformation.setShortName(vehicleType.getShortName());
      resultOfTransformation.setName(vehicleType.getName());
    }

    return resultOfTransformation;
  }
}
