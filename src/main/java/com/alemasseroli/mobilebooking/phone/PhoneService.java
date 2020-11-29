package com.alemasseroli.mobilebooking.phone;

import com.alemasseroli.mobilebooking.fonoapi.FonoApiService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PhoneService {

    private FonoApiService fonoApiService = new FonoApiService();
    private PhoneRepository repository = initPhoneRepository();

    public String listPhones() {
        return repository.toJsonString();
    }

    public boolean bookPhone(String deviceName, String userName) {
        Phone phone = repository.get(deviceName);
        if (phone == null || !phone.isAvailable()) {
            return false;
        }
        final BookedPhone booked = new BookedPhone(phone, userName);
        repository.put(deviceName, booked);
        return true;
    }

    public boolean returnPhone(String deviceName, String userName) {
        Phone phone = repository.get(deviceName);
        if (phone == null || phone.isAvailable() || !((BookedPhone) phone).bookedBy.equals(userName)) {
            return false;
        }
        final AvailablePhone available = new AvailablePhone(phone);
        repository.put(deviceName, available);
        return true;
    }

    private PhoneRepository initPhoneRepository() {
        Map<String, Phone> repository = new HashMap<>();
        for (String deviceName : PhoneRepository.DEVICE_NAMES) {
            repository.put(deviceName, phoneOf(deviceName));
        }
        return new PhoneRepository(repository);
    }

    private AvailablePhone phoneOf(String deviceName) {
        final Optional<FonoApiService.FonoApiResponse> phoneInfo = fonoApiService.getPhoneInfo(deviceName);
        return phoneInfo
                .map(info -> new AvailablePhone(deviceName, info.technology, info._2g_bands, info._3g_bands, info._4g_bands))
                .orElse(new AvailablePhone(deviceName));
    }

}
