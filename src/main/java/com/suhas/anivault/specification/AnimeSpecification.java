package com.suhas.anivault.specification;

import com.suhas.anivault.entity.Anime;
import com.suhas.anivault.enums.AnimeStatus;
import com.suhas.anivault.enums.WatchStatus;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public final class AnimeSpecification {

    private AnimeSpecification() {
    }

    public static Specification<Anime> hasTitle(String title) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                );
    }

    public static Specification<Anime> hasStudio(String studio) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("studio")),
                        studio.toLowerCase()
                );
    }

    public static Specification<Anime> hasAnimeStatus(AnimeStatus animeStatus) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("animeStatus"),
                        animeStatus
                );
    }

    public static Specification<Anime> hasWatchStatus(WatchStatus watchStatus) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("watchStatus"),
                        watchStatus
                );
    }

    public static Specification<Anime> hasGenre(String genre) {

        return (root, query, criteriaBuilder) -> {

            Join<Anime, String> genres = root.join("genres");

            return criteriaBuilder.equal(
                    criteriaBuilder.lower(genres),
                    genre.toLowerCase()
            );
        };
    }

    public static Specification<Anime> hasUser(Long userId) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("user").get("id"),
                        userId
                );
    }
}
