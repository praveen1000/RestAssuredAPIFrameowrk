/*
Author -> Praveen Kumar
 */

package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    @JsonProperty("lat")
    private Double lat;

    @JsonProperty("lng")
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
