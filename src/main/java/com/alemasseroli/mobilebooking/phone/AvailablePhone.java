package com.alemasseroli.mobilebooking.phone;

public class AvailablePhone extends Phone {

    AvailablePhone(String deviceName) {
        this.deviceName = deviceName;
    }

    AvailablePhone(String deviceName, String technology, String _2g_bands, String _3g_bands, String _4g_bands) {
        this.deviceName = deviceName;
        this.technology = technology;
        this._2g_bands = _2g_bands;
        this._3g_bands = _3g_bands;
        this._4g_bands = _4g_bands;
    }

    AvailablePhone(Phone phone) {
        this(phone.deviceName, phone.technology, phone._2g_bands, phone._3g_bands, phone._4g_bands);
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

}
