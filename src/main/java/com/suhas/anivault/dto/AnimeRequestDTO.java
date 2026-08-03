package com.suhas.anivault.dto;

import com.suhas.anivault.enums.AnimeStatus;
import com.suhas.anivault.enums.WatchStatus;
import jakarta.validation.constraints.*;
import java.util.Set;

public class AnimeRequestDTO {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotEmpty(message = "Genres cannot be empty")
    private Set<String> genres;

    @NotBlank(message = "Studio cannot be blank")
    private String studio;

    @Min(value = 1, message = "Episodes must be at least 1")
    private int episodes;

    @Min(value = 0, message = "Watched episodes cannot be negative")
    private int watchedEpisodes;

    @NotNull(message = "Anime status is required")
    private AnimeStatus animeStatus;

    @NotNull(message = "Watch status is required")
    private WatchStatus watchStatus;

    public AnimeRequestDTO() {
    }

    public AnimeRequestDTO(AnimeStatus animeStatus, int episodes, Set<String> genres, String studio, String title, int watchedEpisodes, WatchStatus watchStatus) {
        this.animeStatus = animeStatus;
        this.episodes = episodes;
        this.genres = genres;
        this.studio = studio;
        this.title = title;
        this.watchedEpisodes = watchedEpisodes;
        this.watchStatus = watchStatus;
    }

    public void setAnimeStatus(AnimeStatus animeStatus) {
        this.animeStatus = animeStatus;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWatchedEpisodes(int watchedEpisodes) {
        this.watchedEpisodes = watchedEpisodes;
    }

    public void setWatchStatus(WatchStatus watchStatus) {
        this.watchStatus = watchStatus;
    }

    public AnimeStatus getAnimeStatus() {
        return animeStatus;
    }

    public int getEpisodes() {
        return episodes;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public String getStudio() {
        return studio;
    }

    public String getTitle() {
        return title;
    }

    public int getWatchedEpisodes() {
        return watchedEpisodes;
    }

    public WatchStatus getWatchStatus() {
        return watchStatus;
    }
}

