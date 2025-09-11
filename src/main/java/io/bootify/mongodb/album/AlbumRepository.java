package io.bootify.mongodb.album;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AlbumRepository extends MongoRepository<Album, Long> {
}
