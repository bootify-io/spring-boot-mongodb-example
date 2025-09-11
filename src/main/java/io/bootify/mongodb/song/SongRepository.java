package io.bootify.mongodb.song;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface SongRepository extends MongoRepository<Song, Long> {

    Song findFirstByAlbumId(Long id);

}
