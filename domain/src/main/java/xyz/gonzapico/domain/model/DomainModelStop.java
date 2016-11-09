package xyz.gonzapico.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gfernandez on 4/11/16.
 */

public class DomainModelStop {
  private List<Double> locationList = new ArrayList<>();
  private String addr;
  private String name;
  private String num;
  private String country;
  private String hitAt;
  private String city;

  public List<Double> getLocationList() {
    return locationList;
  }

  public void setLocationList(List<Double> locationList) {
    this.locationList = locationList;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
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

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }
}
