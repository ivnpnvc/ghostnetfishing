package com.ghostnetfishing.Bean.ControllerRequests;

import com.ghostnetfishing.Bean.DB.UserObj.Detector;
import com.ghostnetfishing.Bean.DB.UserObj.User;


import javax.inject.Inject;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;


public class CreateNetRequest {



    public CreateNetRequest(Detector detector) {
        this.detector = detector;
    }

    @DecimalMin(value ="-180.0")
    @DecimalMax( value = "180.0")
    private double latitude;

    @DecimalMin(value ="-180.0")
    @DecimalMax( value = "180.0")
    private double longitude;

    @Min(0)
    private int estimatedSizeinm2;


    private Detector detector;

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

    public int getEstimatedSizeinm2() {
        return estimatedSizeinm2;
    }

    public void setEstimatedSizeinm2(int estimatedSizeinm2) {
        this.estimatedSizeinm2 = estimatedSizeinm2;
    }

    public Detector getDetector() {
        return detector;
    }

    public void setDetector(Detector detector) {
        this.detector = detector;
    }
}
