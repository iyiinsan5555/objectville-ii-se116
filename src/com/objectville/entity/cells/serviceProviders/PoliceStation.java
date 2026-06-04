package com.objectville.entity.cells.serviceProviders;

public class PoliceStation extends ServiceProvider {
    private static final int radius = 5; //fixed size which is designated before

    //Parameterized constructor
    public PoliceStation(int x, int y) {
        super(x, y, radius);
    }

    @Override
    public String getServiceType() {
        return "Security";
    }

    @Override
    public String getServiceProviderType() {
        return "PoliceStation";
    }
}

