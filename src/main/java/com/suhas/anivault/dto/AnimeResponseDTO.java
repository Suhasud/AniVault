package com.suhas.anivault.dto;

import com.suhas.anivault.enums.AnimeStatus;
import com.suhas.anivault.enums.WatchStatus;

import java.util.Set;

public class AnimeResponseDTO {

    private Long id;
    private String title;
    private Set<String> genres;
    private String studio;
    private int episodes;
    private int watchedEpisodes;
    private AnimeStatus animeStatus;
    private WatchStatus watchStatus;

    private String imageUrl;

    public AnimeResponseDTO() {
    }

    public AnimeResponseDTO(AnimeStatus animeStatus, int episodes, Set<String> genres, Long id, String studio, String title, int watchedEpisodes, WatchStatus watchStatus,String imageUrl) {
        this.animeStatus = animeStatus;
        this.episodes = episodes;
        this.genres = genres;
        this.id = id;
        this.studio = studio;
        this.title = title;
        this.watchedEpisodes = watchedEpisodes;
        this.watchStatus = watchStatus;
        this.imageUrl = imageUrl;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
