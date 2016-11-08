package xyz.gonzapico.cabifytt.getEstimation;

import java.util.List;

/**
 * Created by gfernandez on 8/11/16.
 */

public class VehicleType {
  private boolean asapOnly;
  private boolean reservedOnly;
  private String id;
  private String name;
  private String shortName;
  private String description;
  private String currency;
  private String icon;
  private List<String> icons;

  public boolean isAsapOnly() {
    return asapOnly;
  }

  public void setAsapOnly(boolean asapOnly) {
    this.asapOnly = asapOnly;
  }

  public boolean isReservedOnly() {
    return reservedOnly;
  }

  public void setReservedOnly(boolean reservedOnly) {
    this.reservedOnly = reservedOnly;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public List<String> getIcons() {
    return icons;
  }

  public void setIcons(List<String> icons) {
    this.icons = icons;
  }
}
