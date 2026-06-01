package com.objectville.entity.cells.serviceProviders;

public class School extends ServiceProvider {
    private static final int radius = 4;

    public School(int x, int y) {
        super(x, y, radius);
    }

    @Override
    public String getServiceType() {
        return "School";
    }
}
