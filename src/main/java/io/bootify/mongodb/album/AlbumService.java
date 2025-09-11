package io.bootify.mongodb.album;

import io.bootify.mongodb.events.BeforeDeleteAlbum;
import io.bootify.mongodb.util.NotFoundException;
import java.util.List;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ApplicationEventPublisher publisher;

    public AlbumService(final AlbumRepository albumRepository,
            final ApplicationEventPublisher publisher) {
        this.albumRepository = albumRepository;
        this.publisher = publisher;
    }

    public List<AlbumDTO> findAll() {
        final List<Album> albums = albumRepository.findAll(Sort.by("id"));
        return albums.stream()
                .map(album -> mapToDTO(album, new AlbumDTO()))
                .toList();
    }

    public AlbumDTO get(final Long id) {
        return albumRepository.findById(id)
                .map(album -> mapToDTO(album, new AlbumDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final AlbumDTO albumDTO) {
        final Album album = new Album();
        mapToEntity(albumDTO, album);
        return albumRepository.save(album).getId();
    }

    public void update(final Long id, final AlbumDTO albumDTO) {
        final Album album = albumRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(albumDTO, album);
        albumRepository.save(album);
    }

    public void delete(final Long id) {
        final Album album = albumRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        publisher.publishEvent(new BeforeDeleteAlbum(id));
        albumRepository.delete(album);
    }

    private AlbumDTO mapToDTO(final Album album, final AlbumDTO albumDTO) {
        albumDTO.setId(album.getId());
        albumDTO.setName(album.getName());
        albumDTO.setReleaseDate(album.getReleaseDate());
        albumDTO.setMetaData(album.getMetaData());
        return albumDTO;
    }

    private Album mapToEntity(final AlbumDTO albumDTO, final Album album) {
        album.setName(albumDTO.getName());
        album.setReleaseDate(albumDTO.getReleaseDate());
        album.setMetaData(albumDTO.getMetaData());
        return album;
    }

}
