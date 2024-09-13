package com.rafaeltech.musicvault.repository;

import com.rafaeltech.musicvault.MusicvaultApplication;
import com.rafaeltech.musicvault.modules.Artist;
import com.rafaeltech.musicvault.modules.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameContainingIgnoreCase(String artistName);

    @Query("SELECT m FROM Artist a JOIN a.musics m WHERE a = :artist ORDER BY m.name")
    List<Music> searchMusicsFromArtists(Artist artist);
}
