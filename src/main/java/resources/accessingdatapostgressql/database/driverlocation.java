package resources.accessingdatapostgressql.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class driverlocation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private double longitude;
    private double latitude;
    private long h3index;
    private Integer driverid;
    private  boolean active;

    public driverlocation(){}

    public Integer getDriverid() {
        return driverid;
    }

//    public Integer getId() {
//        return id;
//    }

//    public long getH3index() {
//        return h3index;
//    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setDriverid(Integer driverid) {
        this.driverid = driverid;
    }

    public void setH3index(long h3index) {
        this.h3index = h3index;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public String getLatLongString(){
        return String.valueOf(longitude) + "," + String.valueOf(latitude);
    }
}
