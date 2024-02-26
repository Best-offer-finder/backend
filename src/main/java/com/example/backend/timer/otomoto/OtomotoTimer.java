package com.example.backend.timer.otomoto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OtomotoTimer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OtomotoTimer.class);

    @Value("${otomoto.search.api.url}")
    private String destinationPath;

    public void getOtomotoSearch(String jsonData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(jsonData, headers);
        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(destinationPath, requestEntity, String.class);

        LOGGER.info("Response code: {}", responseEntity.getStatusCode());
    }

}
