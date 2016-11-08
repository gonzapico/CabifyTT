package xyz.gonzapico.cabifytt.getEstimation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gfernandez on 8/11/16.
 */

public class RequestStops {

  private List<Stop> stops = new ArrayList<>();
  private String startAt = "";

  public List<Stop> getStops() {
    return stops;
  }

  public void setStops(List<Stop> stops) {
    this.stops = stops;
  }

  public String getStartAt() {
    return startAt;
  }

  public void setStartAt(String startAt) {
    this.startAt = startAt;
  }
}
