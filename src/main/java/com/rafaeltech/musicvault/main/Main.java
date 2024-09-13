package com.rafaeltech.musicvault.main;

import com.rafaeltech.musicvault.MusicvaultApplication;
import com.rafaeltech.musicvault.modules.Artist;
import com.rafaeltech.musicvault.modules.ArtistCategory;
import com.rafaeltech.musicvault.modules.Music;
import com.rafaeltech.musicvault.repository.ArtistRepository;
import com.rafaeltech.musicvault.service.ConsultChatGPT;
import org.springframework.expression.spel.ast.ValueRef;
import org.yaml.snakeyaml.representer.BaseRepresenter;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.StreamHandler;

public class Main {
    private final ArtistRepository artistRepository;
    Scanner scanner = new Scanner(System.in);

    public Main(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void displayMenu() {
        var option = -1;
        while (option != 0) {
            var menu = """
                    *** Music Vault ***
                    
                    1 - Register artist
                    2 - Register music
                    3 - show musics
                    4 - Search musics for artists
                    5 - search for data about an artist
                    
                    0 - exit
                    """;

            System.out.println(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    registerArtist();
                    break;
                case 2:
                    registerMusic();
                    break;
                case 3:
                    showMusics();
                    break;
                case 4:
                    searchMusicsFromArtist();
                    break;
                case 5:
                    searchDateArtist();
                case 0:

                    System.out.println("exit...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void searchDateArtist() {
        System.out.println("Type the name of Artist: ");
        var name = scanner.nextLine();
        var response = ConsultChatGPT.getInformation(name);
        System.out.println(response.trim());
    }


    private void registerArtist() {

        var registerNew = "S";
        while (registerNew.equalsIgnoreCase("s")) {
            System.out.println("Name: ");
            var name = scanner.nextLine();
            System.out.println("Category (solo, duo, band)");
            var category = scanner.nextLine();
            Artist artist = new Artist(name, ArtistCategory.fromString(category));
            artistRepository.save(artist);
            System.out.println("Want to Register one more Artist? (S/N)");
            registerNew = scanner.nextLine();
        }

    }

    private void registerMusic() {
        var registerNew = "S";
        while (registerNew.equalsIgnoreCase("s")) {
            System.out.println("The artist of that Music: ");
            var artistName = scanner.nextLine();
            Optional<Artist> artist = artistRepository.findByNameContainingIgnoreCase(artistName);
            if (artist.isPresent()){
                System.out.println("Music Title");
                var musicTitle = scanner.nextLine();
                Music music = new Music(musicTitle);
                music.setArtist(artist.get());
                artist.get().getMusics().add(music);
                artistRepository.save(artist.get());
            } else{
                System.out.println("Artist not Found");
            }

            System.out.println("Want to Register one more Music (S/N)? ");
            registerNew = scanner.nextLine();
        }


    }

    private void showMusics() {
        List<Artist> artists = artistRepository.findAll();
        artists.forEach(a -> a.getMusics().forEach(m ->
                System.out.println("Music: " + m.getName() + " (" + m.getArtist().getName() + ")")));

    }

    private void searchMusicsFromArtist() {
        System.out.println("Type a Artist: ");
        var artistName = scanner.nextLine();
        Optional<Artist> artistSearch = artistRepository.findByNameContainingIgnoreCase(artistName);
        if(artistSearch.isPresent()){
            Artist artist = artistSearch.get();
            List<Music> musics = artistRepository.searchMusicsFromArtists(artist);
            musics.forEach(m ->
                    System.out.printf("Musica: %s (%s)\n", m.getName(), m.getArtist().getName()));
        }

    }
}
