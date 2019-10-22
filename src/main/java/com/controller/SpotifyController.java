package com.controller;

import com.model.Track;
import com.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class SpotifyController {

    @Autowired
    private TrackService trackService;

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

    @PutMapping("/api/tracks/favourites/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void insertTrack(@RequestBody Track track) {
        trackService.insertIntoDatabase(track);
    }

    @DeleteMapping("/api/tracks/favourites/remove/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") String trackId) {
        trackService.delete(trackId);

    }

    @GetMapping("/api/tracks/favourites/list")
    public List<Track> getAll() {
        return trackService.getAll();
    }


}
