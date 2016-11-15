package xyz.gonzapico.cabifytt.getEstimation.model;

import java.util.ArrayList;
import java.util.List;
import xyz.gonzapico.cabifytt.getEstimation.model.Location;

/**
 * Created by gfernandez on 8/11/16.
 */

public class Stop {
  private String name = "";
  private String addr = "";
  private String num = "";
  private String city = "";
  private String country = "";
  private String hitAt = "";
  private List<Double> locationList = new ArrayList<>();

  public List<Double> getLocationList() {
    return locationList;
  }

  public void setLocationList(List<Double> locationList) {
    this.locationList = locationList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getHitAt() {
    return hitAt;
  }

  public void setHitAt(String hitAt) {
    this.hitAt = hitAt;
  }
}
