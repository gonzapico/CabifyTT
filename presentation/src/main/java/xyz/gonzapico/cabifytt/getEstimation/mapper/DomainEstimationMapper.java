package xyz.gonzapico.cabifytt.getEstimation.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import xyz.gonzapico.cabifytt.di.PerActivity;
import xyz.gonzapico.cabifytt.getEstimation.Utils;
import xyz.gonzapico.cabifytt.getEstimation.model.EstimateVechicle;
import xyz.gonzapico.cabifytt.getEstimation.model.RequestStops;
import xyz.gonzapico.cabifytt.getEstimation.model.Stop;
import xyz.gonzapico.domain.model.DomainModelBodyRequestStops;
import xyz.gonzapico.domain.model.DomainModelEstimateVehicle;
import xyz.gonzapico.domain.model.DomainModelStop;
import xyz.gonzapico.domain.model.DomainVehicleType;

/**
 * Created by gfernandez on 8/11/16.
 */

@PerActivity public class DomainEstimationMapper {

  @Inject public DomainEstimationMapper() {

  }

  public List<EstimateVechicle> transformListOfEstimationVehicle(
      List<DomainModelEstimateVehicle> estimationVehicleList) {
    List<EstimateVechicle> resultOfEstimateVehicles = new ArrayList<>();

    for (DomainModelEstimateVehicle domainModelEstimateVehicle : estimationVehicleList) {
      resultOfEstimateVehicles.add(transformEstimateVehicle(domainModelEstimateVehicle));
    }

    return resultOfEstimateVehicles;
  }

  private EstimateVechicle transformEstimateVehicle(
      DomainModelEstimateVehicle domainModelEstimateVehicle) {
    EstimateVechicle resultOfEstimateVehicle = new EstimateVechicle();

    resultOfEstimateVehicle.setCurrency(domainModelEstimateVehicle.getCurrency());
    resultOfEstimateVehicle.setCurrencySymbol(domainModelEstimateVehicle.getCurrencySymbol());
    resultOfEstimateVehicle.setPriceFormatted(domainModelEstimateVehicle.getPriceFormatted());
    resultOfEstimateVehicle.setTotalPrice(domainModelEstimateVehicle.getTotalPrice());
    resultOfEstimateVehicle.setVehicleType(
        transformVehicleType(domainModelEstimateVehicle.getVehicleType()));

    return resultOfEstimateVehicle;
  }

  private xyz.gonzapico.cabifytt.getEstimation.model.VehicleType transformVehicleType(
      DomainVehicleType vehicleType) {
    xyz.gonzapico.cabifytt.getEstimation.model.VehicleType resultOfVehicleType =
        new xyz.gonzapico.cabifytt.getEstimation.model.VehicleType();

    resultOfVehicleType.setIcon(vehicleType.getIcon());
    resultOfVehicleType.setShortName(vehicleType.getShortName());
    resultOfVehicleType.setDescription(vehicleType.getDescription());
    resultOfVehicleType.setAsapOnly(vehicleType.isAsapOnly());
    resultOfVehicleType.setId(vehicleType.getId());
    resultOfVehicleType.setReservedOnly(vehicleType.isReservedOnly());
    resultOfVehicleType.setIcons(vehicleType.getIcons());
    resultOfVehicleType.setCurrency(vehicleType.getCurrency());
    resultOfVehicleType.setName(vehicleType.getName());

    return resultOfVehicleType;
  }

  public DomainModelBodyRequestStops trasnformToBodyRequest(RequestStops stopsRequested) {
    DomainModelBodyRequestStops domainModelBodyRequestStops = new DomainModelBodyRequestStops();

    domainModelBodyRequestStops.setStartAt(Utils.currentDateFormatted());
    domainModelBodyRequestStops.setStops(transformStops(stopsRequested.getStops()));

    return domainModelBodyRequestStops;
  }

  private List<DomainModelStop> transformStops(List<Stop> stops) {
    List<DomainModelStop> resultListOfStops = new ArrayList<>();

    for (Stop stop : stops) {
      resultListOfStops.add(transformStop(stop));
    }

    return resultListOfStops;
  }

  private DomainModelStop transformStop(Stop stop) {
    DomainModelStop domainModelStop = new DomainModelStop();

    domainModelStop.setLocationList(stop.getLocationList());
    domainModelStop.setAddr(stop.getAddr());
    domainModelStop.setName(stop.getName());
    domainModelStop.setNum(stop.getNum());
    domainModelStop.setCountry(stop.getCountry());
    domainModelStop.setCity(stop.getCity());
    domainModelStop.setHitAt(stop.getHitAt());
    return domainModelStop;
  }
}
