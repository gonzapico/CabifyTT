
package xyz.gonzapico.data.entity;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class EstimateVehicle {

  private VehicleType vehicleType;
  private Object totalPrice;
  private Object currency;
  private Object currencySymbol;
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
  public Object getTotalPrice() {
    return totalPrice;
  }

  /**
   *
   * @param totalPrice
   *     The total_price
   */
  public void setTotalPrice(Object totalPrice) {
    this.totalPrice = totalPrice;
  }

  /**
   *
   * @return
   *     The currency
   */
  public Object getCurrency() {
    return currency;
  }

  /**
   *
   * @param currency
   *     The currency
   */
  public void setCurrency(Object currency) {
    this.currency = currency;
  }

  /**
   *
   * @return
   *     The currencySymbol
   */
  public Object getCurrencySymbol() {
    return currencySymbol;
  }

  /**
   *
   * @param currencySymbol
   *     The currency_symbol
   */
  public void setCurrencySymbol(Object currencySymbol) {
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
