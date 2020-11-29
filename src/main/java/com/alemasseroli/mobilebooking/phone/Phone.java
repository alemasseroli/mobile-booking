package com.alemasseroli.mobilebooking.phone;

public abstract class Phone {
    String deviceName;
    String technology;
    String _2g_bands;
    String _3g_bands;
    String _4g_bands;

    public abstract boolean isAvailable();

}
