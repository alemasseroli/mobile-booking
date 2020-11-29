package com.alemasseroli.mobilebooking.phone;

public class AvailablePhone extends Phone {

    AvailablePhone(String deviceName) {
        this.deviceName = deviceName;
        // TODO: initialize information provided by FONOAPI.
    }

    AvailablePhone(Phone phone) {
        this.deviceName = phone.deviceName;
        this.technology = phone.technology;
        this.twoGBands = phone.twoGBands;
        this.threeGBands = phone.threeGBands;
        this.fourGBands = phone.fourGBands;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

}
