package com.alemasseroli.mobilebooking.fonoapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Optional;

public class FonoApiService {

    private static String FONO_API_URL = "https://fonoapi.freshpixl.com/v1/{0}?token=myToken";
    private RestTemplate restTemplate = new RestTemplate();
    private final static Logger logger = LoggerFactory.getLogger(FonoApiService.class);

    public Optional<FonoApiResponse> getPhoneInfo(String deviceName) {
        final String url = MessageFormat.format(FONO_API_URL, deviceName);
        try {
            ResponseEntity<FonoApiResponse> fonoApiResponse = restTemplate.getForEntity(url, FonoApiResponse.class);
            final HttpStatus statusCode = fonoApiResponse.getStatusCode();
            if (HttpStatus.OK.equals(statusCode) && fonoApiResponse.getBody() != null) {
                return Optional.of(fonoApiResponse.getBody());
            } else {
                logger.warn(MessageFormat.format("Error in Fono Api. Status code: {0}", statusCode.toString()));
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    public static class FonoApiResponse {
        @JsonProperty("technology")
        public String technology;
        @JsonProperty("_2g_bands")
        public String _2g_bands;
        @JsonProperty("_3g_bands")
        public String _3g_bands;
        @JsonProperty("_4g_bands")
        public String _4g_bands;
    }

}
