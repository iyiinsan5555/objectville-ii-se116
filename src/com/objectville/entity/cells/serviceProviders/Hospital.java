package com.objectville.entity.cells.serviceProviders;

public class Hospital extends ServiceProvider {
    private static final int radius = 3;

    public Hospital(int x, int y) {
        super(x, y, radius);
    }

    @Override
    public String getServiceType() {
        return "Health";
    }

    @Override
    public String getServiceProviderType() {
        return "Hospital";
    }
}
