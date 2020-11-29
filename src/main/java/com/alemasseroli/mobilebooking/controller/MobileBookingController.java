package com.alemasseroli.mobilebooking.controller;

import com.alemasseroli.mobilebooking.phone.PhoneService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MobileBookingController {

    private PhoneService phoneService = new PhoneService();

    @GetMapping(value = "/booking")
    public ResponseEntity<Object> listPhones() {
        return new ResponseEntity<>(phoneService.listPhones(), HttpStatus.OK);
    }

    @PostMapping(value = "/booking")
    public ResponseEntity<Object> bookPhone(@RequestBody BookingRequest bookingRequest) {
        boolean booked = phoneService.bookPhone(bookingRequest.deviceName, bookingRequest.userName);
        if (booked) {
            return new ResponseEntity<>("Phone successfully booked", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Phone not available.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/booking")
    public ResponseEntity<Object> returnPhone(@RequestBody BookingRequest bookingRequest) {
        boolean returned = phoneService.returnPhone(bookingRequest.deviceName, bookingRequest.userName);
        if (returned) {
            return new ResponseEntity<>("Phone successfully returned", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Phone can not be returned by that user.", HttpStatus.BAD_REQUEST);
        }
    }

    public static class BookingRequest {
        @JsonProperty("device_name")
        public String deviceName;
        @JsonProperty("user_name")
        public String userName;
    }

}
