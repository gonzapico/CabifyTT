package xyz.gonzapico.cabifytt.getEstimation.model;

import java.util.ArrayList;
import java.util.List;
import xyz.gonzapico.cabifytt.getEstimation.model.Location;

/**
 * Created by gfernandez on 8/11/16.
 */

public class Stop {

  private List<Location> locationList = new ArrayList<>();

  public List<Location> getLocationList() {
    return locationList;
  }

  public void setLocationList(List<Location> locationList) {
    this.locationList = locationList;
  }
}
