package com.repository;

import com.model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtistsRepository extends MongoRepository<Track.Artists, String> {
}
