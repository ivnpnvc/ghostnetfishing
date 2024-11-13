package com.ghostnetfishing.Bean.ControllerRequests;


public enum Role {
     SALVOR("Bergende Person", true),
    DETECTOR("Meldende Person", false);


    private String label;

    private boolean phoneRequired;

    private Role (String label, boolean phoneRequired) {
        this.label = label;
        this.phoneRequired = phoneRequired;
    }

    public String getLabel() {
        return label;
    }

    public boolean isPhoneRequired() {
        return phoneRequired;
    }
}

