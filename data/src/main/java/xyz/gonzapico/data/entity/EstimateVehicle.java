
package xyz.gonzapico.data.entity;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class EstimateVehicle {

  private VehicleType vehicleType;
  private long totalPrice;
  private String currency;
  private String currencySymbol;
  private String priceFormatted;

  /**
   *
   * @return
   *     The vehicleType
   */
  public VehicleType getVehicleType() {
    return vehicleType;
  }

  /**
   *
   * @param vehicleType
   *     The vehicle_type
   */
  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  /**
   *
   * @return
   *     The totalPrice
   */
  public long getTotalPrice() {
    return totalPrice;
  }

  /**
   *
   * @param totalPrice
   *     The total_price
   */
  public void setTotalPrice(long totalPrice) {
    this.totalPrice = totalPrice;
  }

  /**
   *
   * @return
   *     The currency
   */
  public String getCurrency() {
    return currency;
  }

  /**
   *
   * @param currency
   *     The currency
   */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   *
   * @return
   *     The currencySymbol
   */
  public String getCurrencySymbol() {
    return currencySymbol;
  }

  /**
   *
   * @param currencySymbol
   *     The currency_symbol
   */
  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  /**
   *
   * @return
   *     The priceFormatted
   */
  public String getPriceFormatted() {
    return priceFormatted;
  }

  /**
   *
   * @param priceFormatted
   *     The price_formatted
   */
  public void setPriceFormatted(String priceFormatted) {
    this.priceFormatted = priceFormatted;
  }

}
