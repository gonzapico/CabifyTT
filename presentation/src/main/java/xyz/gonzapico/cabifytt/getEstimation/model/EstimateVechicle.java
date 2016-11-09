package xyz.gonzapico.cabifytt.getEstimation.model;

/**
 * Created by gfernandez on 8/11/16.
 */

public class EstimateVechicle {

  private xyz.gonzapico.cabifytt.getEstimation.model.VehicleType vehicleType;
  private long totalPrice;
  private String currency;
  private String currencySymbol;
  private String priceFormatted;

  public xyz.gonzapico.cabifytt.getEstimation.model.VehicleType getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(xyz.gonzapico.cabifytt.getEstimation.model.VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public long getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(long totalPrice) {
    this.totalPrice = totalPrice;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  public String getPriceFormatted() {
    return priceFormatted;
  }

  public void setPriceFormatted(String priceFormatted) {
    this.priceFormatted = priceFormatted;
  }
}
