package com.alemasseroli.mobilebooking.phone;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;
import java.util.Map;

import static java.util.Arrays.asList;

class PhoneRepository {

    private Map<String, Phone> phones;

    PhoneRepository(Map<String, Phone> phones) {
        this.phones = phones;
    }

    static final Collection<String> DEVICE_NAMES = asList(
            "Samsung Galaxy S9", "Samsung Galaxy S8", "Samsung Galaxy S7", "Motorola Nexus 6", "LG Nexus 5X",
            "Huawei Honor 7X", "Apple iPhone X", "Apple iPhone 8", "Apple iPhone 4s", "Nokia 3310"
    );

    Phone get(String deviceName) {
        return phones.get(deviceName);
    }

    Phone put(String deviceName, Phone phone) {
        return phones.put(deviceName, phone);
    }

    String toJsonString() {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(phones);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return DEVICE_NAMES.toString();
        }
    }

}
