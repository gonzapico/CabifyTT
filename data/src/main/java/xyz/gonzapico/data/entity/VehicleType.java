
package xyz.gonzapico.data.entity;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class VehicleType {

    private Boolean asapOnly;
    private Boolean reservedOnly;
    private String id;
    private String name;
    private String shortName;
    private String description;
    private String currency;
    private String icon;
    private Icons icons;

    /**
     * 
     * @return
     *     The asapOnly
     */
    public Boolean getAsapOnly() {
        return asapOnly;
    }

    /**
     * 
     * @param asapOnly
     *     The asap_only
     */
    public void setAsapOnly(Boolean asapOnly) {
        this.asapOnly = asapOnly;
    }

    /**
     * 
     * @return
     *     The reservedOnly
     */
    public Boolean getReservedOnly() {
        return reservedOnly;
    }

    /**
     * 
     * @param reservedOnly
     *     The reserved_only
     */
    public void setReservedOnly(Boolean reservedOnly) {
        this.reservedOnly = reservedOnly;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * 
     * @param shortName
     *     The short_name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
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
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The icons
     */
    public Icons getIcons() {
        return icons;
    }

    /**
     * 
     * @param icons
     *     The icons
     */
    public void setIcons(Icons icons) {
        this.icons = icons;
    }

}
