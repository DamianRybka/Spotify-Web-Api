package com.service;

import com.model.Track;
import com.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public void insertIntoDatabase(Track track) {
        track.setId(null); // zerowanie id pozwoli na dodanie nowego a nie na zaktualizowanie już istniejącego.
        trackRepository.save(track);
    }

    public void delete(String trackId) {
        if (trackRepository.existsById(trackId)) {
            trackRepository.deleteById(trackId);
        }
    }

    public List<Track> getAll() {
        return trackRepository.findAll();
    }
}

