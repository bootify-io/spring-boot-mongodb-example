package io.bootify.mongodb.album;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Objects;


public class MetaData {

    @NotNull
    private Integer trackCount;

    @Size(max = 255)
    private String catalogNumber;

    private List<@Size(max = 255) String> labels;

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(final Integer trackCount) {
        this.trackCount = trackCount;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(final String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(final List<String> labels) {
        this.labels = labels;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        final MetaData metaData = ((MetaData)other);
        return Objects.equals(trackCount, metaData.trackCount) &&
                Objects.equals(catalogNumber, metaData.catalogNumber) &&
                Objects.equals(labels, metaData.labels);
    }

}
