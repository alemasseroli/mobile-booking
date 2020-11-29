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
        this._2g_bands = phone._2g_bands;
        this._3g_bands = phone._3g_bands;
        this._4g_bands = phone._4g_bands;
        final LocalDateTime dateTime = LocalDateTime.now();
        this.bookedDate = dateTime.format(DATE_FORMATTER);
        this.bookedBy = userName;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

}
