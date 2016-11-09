package xyz.gonzapico.cabifytt.getEstimation.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by gfernandez on 8/11/16.
 */

public class Location {

  private double latitude;
  private double longitude;

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public void setLatituteLongitude(LatLng latLong){
    this.latitude = latLong.latitude;
    this.longitude = latLong.longitude;
  }
}
