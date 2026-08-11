package com.suhas.anivault.entity;

import com.suhas.anivault.enums.AnimeStatus;
import com.suhas.anivault.enums.WatchStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Set;

@Entity
@Table(name= "anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Studio cannot be blank")
    private String studio;

    private String imageUrl;

    @Min(value = 1, message = "Episodes must be at least 1")
    private int episodes;
    @Min(value = 0, message = "Watched episodes cannot be negative")
    private int watchedEpisodes;

    @NotNull(message = "Anime status is required")
    @Enumerated(EnumType.STRING)

    @NotNull(message = "Watch status is required")
    private AnimeStatus animeStatus;

    @Enumerated(EnumType.STRING)
    private WatchStatus watchStatus;

    @NotEmpty(message = "Genres cannot be empty")
    @ElementCollection
    private Set<String> genres;

    public Anime() {
    }

    public Anime(String title, Set<String> genres, String studio,
                 int episodes, int watchedEpisodes,
                 AnimeStatus animeStatus, WatchStatus watchStatus,
                 String imageUrl) {
        this.title = title;
        this.genres = genres;
        this.studio = studio;
        this.episodes = episodes;
        this.watchedEpisodes = watchedEpisodes;
        this.animeStatus = animeStatus;
        this.watchStatus = watchStatus;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AnimeStatus getAnimeStatus() {
        return animeStatus;
    }

    public void setAnimeStatus(AnimeStatus animeStatus) {
        this.animeStatus = animeStatus;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getWatchedEpisodes() {
        return watchedEpisodes;
    }

    public void setWatchedEpisodes(int watchedEpisodes) {
        this.watchedEpisodes = watchedEpisodes;
    }

    public WatchStatus getWatchStatus() {
        return watchStatus;
    }

    public void setWatchStatus(WatchStatus watchStatus) {
        this.watchStatus = watchStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}



