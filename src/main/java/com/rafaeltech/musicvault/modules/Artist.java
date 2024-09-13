package com.rafaeltech.musicvault.modules;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    @Enumerated(EnumType.STRING)
    private ArtistCategory ArtistCategory;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Music> musics = new ArrayList<>();

    public Artist() {}

    public Artist(String name, ArtistCategory artistCategory) {
        this.name = name;
        ArtistCategory = artistCategory;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArtistCategory getArtistCategory() {
        return ArtistCategory;
    }

    public void setArtistCategory(ArtistCategory artistCategory) {
        ArtistCategory = artistCategory;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    @Override
    public String toString() {
        return "Artist{" +
                ", name='" + name + '\'' +
                ", ArtistCategory=" + ArtistCategory +
                ", musics=" + musics +
                '}';
    }
}
