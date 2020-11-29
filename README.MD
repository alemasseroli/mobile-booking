# Mobile Devices Booking
Simple API tool to book and return phone devices for testing purposes.


## Starting the application

1) Clone this repository.

```sh
git clone git@github.com:alemasseroli/mobile-booking.git
```

2) Enter project's directory.

```sh
cd mobile-booking
```

3) Start web application running jar file.

```sh
java -jar phone-booking.jar
```

## Usage

- Getting list of all mobile phones:

```sh
curl -X GET localhost:8080/booking
```

- Booking a phone:

```sh
curl -H 'Content-Type: application/json' -s -X POST localhost:8080/booking -d '{"device_name": <DEVICE_NAME>, "user_name": <USER_NAME>}'
```

- Returning a phone:

```sh
curl -H 'Content-Type: application/json' -s -X DELETE localhost:8080/booking -d '{"device_name": <DEVICE_NAME>, "user_name": <USER_NAME>}'
```
