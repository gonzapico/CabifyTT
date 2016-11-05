package xyz.gonzapico.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gfernandez on 4/11/16.
 */

public class DomainModelBodyRequestStops {
  private List<DomainModelStop> stops = new ArrayList<>();
  private String startAt = "";

  public List<DomainModelStop> getStops() {
    return stops;
  }

  public void setStops(List<DomainModelStop> stops) {
    this.stops = stops;
  }

  public String getStartAt() {
    return startAt;
  }

  public void setStartAt(String startAt) {
    this.startAt = startAt;
  }
}
