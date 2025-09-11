package io.bootify.mongodb.events;


public class BeforeDeleteAlbum {

    private Long id;

    public BeforeDeleteAlbum(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
