package io.bootify.mongodb.song;

import io.bootify.mongodb.album.Album;
import io.bootify.mongodb.album.AlbumRepository;
import io.bootify.mongodb.events.BeforeDeleteAlbum;
import io.bootify.mongodb.util.NotFoundException;
import io.bootify.mongodb.util.ReferencedException;
import java.util.List;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public SongService(final SongRepository songRepository, final AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    public List<SongDTO> findAll() {
        final List<Song> songs = songRepository.findAll(Sort.by("id"));
        return songs.stream()
                .map(song -> mapToDTO(song, new SongDTO()))
                .toList();
    }

    public SongDTO get(final Long id) {
        return songRepository.findById(id)
                .map(song -> mapToDTO(song, new SongDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SongDTO songDTO) {
        final Song song = new Song();
        mapToEntity(songDTO, song);
        return songRepository.save(song).getId();
    }

    public void update(final Long id, final SongDTO songDTO) {
        final Song song = songRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(songDTO, song);
        songRepository.save(song);
    }

    public void delete(final Long id) {
        final Song song = songRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        songRepository.delete(song);
    }

    private SongDTO mapToDTO(final Song song, final SongDTO songDTO) {
        songDTO.setId(song.getId());
        songDTO.setName(song.getName());
        songDTO.setDuration(song.getDuration());
        songDTO.setAlbum(song.getAlbum() == null ? null : song.getAlbum().getId());
        return songDTO;
    }

    private Song mapToEntity(final SongDTO songDTO, final Song song) {
        song.setName(songDTO.getName());
        song.setDuration(songDTO.getDuration());
        final Album album = songDTO.getAlbum() == null ? null : albumRepository.findById(songDTO.getAlbum())
                .orElseThrow(() -> new NotFoundException("album not found"));
        song.setAlbum(album);
        return song;
    }

    @EventListener(BeforeDeleteAlbum.class)
    public void on(final BeforeDeleteAlbum event) {
        final ReferencedException referencedException = new ReferencedException();
        final Song albumSong = songRepository.findFirstByAlbumId(event.getId());
        if (albumSong != null) {
            referencedException.setKey("album.song.album.referenced");
            referencedException.addParam(albumSong.getId());
            throw referencedException;
        }
    }

}
