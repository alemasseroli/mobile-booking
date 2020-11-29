package com.alemasseroli.mobilebooking.phone;

public class PhoneService {

    private PhoneRepository repository = new PhoneRepository();

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
}
