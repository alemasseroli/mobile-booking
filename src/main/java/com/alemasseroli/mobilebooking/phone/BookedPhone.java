package com.alemasseroli.mobilebooking.phone;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookedPhone extends Phone {

    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public String bookedDate;
    public String bookedBy;

    public BookedPhone(Phone phone, String userName) {
        this.deviceName = phone.deviceName;
        this.technology = phone.technology;
        this.twoGBands = phone.twoGBands;
        this.threeGBands = phone.threeGBands;
        this.fourGBands = phone.fourGBands;
        final LocalDateTime dateTime = LocalDateTime.now();
        this.bookedDate = dateTime.format(DATE_FORMATTER);
        this.bookedBy = userName;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

}
