package io.bootify.mongodb.album;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class AlbumDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    private LocalDate releaseDate;

    @NotNull
    @Valid
    private MetaData metaData;

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(final MetaData metaData) {
        this.metaData = metaData;
    }

}
