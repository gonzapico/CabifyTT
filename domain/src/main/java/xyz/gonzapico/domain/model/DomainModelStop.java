package xyz.gonzapico.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gfernandez on 4/11/16.
 */

public class DomainModelStop {
  private List<DomainModelLocation> locationList = new ArrayList<>();

  public List<DomainModelLocation> getLocationList() {
    return locationList;
  }

  public void setLocationList(List<DomainModelLocation> locationList) {
    this.locationList = locationList;
  }
}
