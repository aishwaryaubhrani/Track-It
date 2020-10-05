package com.ibm.androidapp;

public class ConsignmentDetails {
    private String clientName;
    private String clientNumber;
    private String driverName;
    private String vehicleNumber;
    private String itemWeight;
    private String deliveryAddress;

    public ConsignmentDetails(String clientName, String clientNumber,
                              String driverName, String vehicleNumber,
                              String itemWeight, String deliveryAddress) {
        this.clientName = clientName;
        this.clientNumber = clientNumber;
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.itemWeight = itemWeight;
        this.deliveryAddress = deliveryAddress;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}
