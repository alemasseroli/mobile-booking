package com.alemasseroli.mobilebooking.phone;

import java.util.List;

public abstract class Phone {
    String deviceName;
    String technology;
    List<String> twoGBands;
    List<String> threeGBands;
    List<String> fourGBands;

    public abstract boolean isAvailable();

}
