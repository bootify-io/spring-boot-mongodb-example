package io.bootify.mongodb.song;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class SongDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Double duration;

    @NotNull
    private Long album;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(final Double duration) {
        this.duration = duration;
    }

    public Long getAlbum() {
        return album;
    }

    public void setAlbum(final Long album) {
        this.album = album;
    }

}
