package xyz.gonzapico.domain.model;

/**
 * Created by gfernandez on 4/11/16.
 */

public class DomainModelLocation {
  private long latitude;
  private long longitude;

  public long getLatitude() {
    return latitude;
  }

  public void setLatitude(long latitude) {
    this.latitude = latitude;
  }

  public long getLongitude() {
    return longitude;
  }

  public void setLongitude(long longitude) {
    this.longitude = longitude;
  }
}
