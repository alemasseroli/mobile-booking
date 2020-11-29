package com.alemasseroli.mobilebooking.phone;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.alemasseroli.mobilebooking.phone.BookedPhone.DATE_FORMATTER;
import static org.assertj.core.api.Assertions.assertThat;

class PhoneServiceTest {

    private PhoneService service = new PhoneService();

    @Test
    void testCorrectBookPhone() {
        assertThat(service.bookPhone("Samsung Galaxy S9", "Alejandro")).isTrue();
    }

    @Test
    void testNonExistantDeviceShouldReturnFalse() {
        assertThat(service.bookPhone("Best Device", "Alejandro")).isFalse();
    }

    @Test
    void testBookedDeviceShouldReturnFalse() {
        boolean booked = service.bookPhone("Samsung Galaxy S9", "Alejandro");
        assertThat(booked).isTrue();
        assertThat(service.bookPhone("Samsung Galaxy S9", "Juan")).isFalse();
    }

    @Test
    void testCorrectReturnPhone() {
        boolean booked = service.bookPhone("Apple iPhone X", "Alejandro");
        assertThat(booked).isTrue();
        assertThat(service.returnPhone("Apple iPhone X", "Alejandro")).isTrue();
    }

    @Test
    void testReturnNonExistantPhoneShouldReturnFalse() {
        assertThat(service.returnPhone("Best Device", "Alejandro")).isFalse();
    }

    @Test
    void testReturnPhoneNotBookedShouldReturnFalse() {
        assertThat(service.returnPhone("Apple iPhone X", "Alejandro")).isFalse();
    }

    @Test
    void testReturnPhoneBookedBySomeoneElseShouldReturnFalse() {
        boolean booked = service.bookPhone("Apple iPhone X", "Alejandro");
        assertThat(booked).isTrue();
        assertThat(service.returnPhone("Apple iPhone X", "Juan")).isFalse();
    }

    @Test
    void testListPhones() {
        String phones = service.listPhones();
        assertThat(phones).contains("\"Apple iPhone X\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"Apple iPhone 8\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"Apple iPhone 4s\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"Samsung Galaxy S7\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"Samsung Galaxy S8\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"Samsung Galaxy S9\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"Huawei Honor 7X\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"LG Nexus 5X\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"Motorola Nexus 6\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"Nokia 3310\" : {\n    \"available\" : true\n  }");

        String bookedDate = LocalDateTime.now().format(DATE_FORMATTER);
        boolean booked = service.bookPhone("Apple iPhone X", "Alejandro");
        assertThat(booked).isTrue();

        phones = service.listPhones();
        assertThat(phones).doesNotContain("\"Apple iPhone X\" : {\n    \"available\" : true\n  }");
        assertThat(phones).contains("\"Apple iPhone X\" : {\n    \"bookedDate\" : \"" + bookedDate +
                "\",\n    \"bookedBy\" : \"Alejandro\",\n    \"available\" : false\n  }");
    }

}