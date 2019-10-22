package com.controller;

import com.model.Track;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class SpotifyController {

    private RestTemplate restTemplate;

    public SpotifyController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/api/tracks/{id}/token/{token}")
    public Track getTrack(@PathVariable String id, @PathVariable String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Track> entity = new HttpEntity<>(headers);
        return restTemplate.exchange("https://api.spotify.com/v1/tracks/" + id, HttpMethod.GET, entity, Track.class).getBody();
    }


}
