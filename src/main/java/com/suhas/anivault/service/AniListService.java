package com.suhas.anivault.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Service
public class AniListService {

    private static final Logger log =
            LoggerFactory.getLogger(AniListService.class);

    private final RestClient restClient;

    public AniListService() {
        this.restClient = RestClient.builder()
                .baseUrl("https://graphql.anilist.co")
                .build();
    }

    public String findPosterUrl(String title) {

        String query = """
                query ($search: String!) {
                    Page(page: 1, perPage: 3) {
                        media(search: $search, type: ANIME) {
                            id
                            title {
                                romaji
                                english
                                native
                            }
                            coverImage {
                                large
                            }
                        }
                    }
                }
                """;

        Map<String, Object> requestBody = Map.of(
                "query", query,
                "variables", Map.of(
                        "search", title
                )
        );

        try {

            Map<?, ?> response = restClient.post()
                    .uri("/")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .body(requestBody)
                    .retrieve()
                    .body(Map.class);

            log.info("========== ANILIST RESPONSE ==========");
            log.info("{}", response);
            log.info("======================================");

            if (response == null) {
                log.warn("AniList returned a null response for: {}", title);
                return null;
            }

            Object dataObject = response.get("data");

            if (!(dataObject instanceof Map<?, ?> data)) {
                log.warn("AniList response does not contain valid data for: {}", title);
                return null;
            }

            Object pageObject = data.get("Page");

            if (!(pageObject instanceof Map<?, ?> page)) {
                log.warn("AniList response does not contain a valid Page for: {}", title);
                return null;
            }

            Object mediaObject = page.get("media");

            if (!(mediaObject instanceof List<?> mediaList)
                    || mediaList.isEmpty()) {

                log.warn("No anime found on AniList for: {}", title);
                return null;
            }

            Object firstMediaObject = mediaList.get(0);

            if (!(firstMediaObject instanceof Map<?, ?> media)) {
                log.warn("Invalid media result from AniList for: {}", title);
                return null;
            }

            log.info("AniList anime result: {}", media);

            Object coverImageObject = media.get("coverImage");

            if (!(coverImageObject instanceof Map<?, ?> coverImage)) {
                log.warn("No coverImage found for: {}", title);
                return null;
            }

            Object largeImage = coverImage.get("large");

            if (largeImage == null) {
                log.warn("No large cover image found for: {}", title);
                return null;
            }

            String imageUrl = largeImage.toString();

            log.info("Poster found for {}: {}", title, imageUrl);

            return imageUrl;

        } catch (Exception exception) {

            log.error(
                    "Could not fetch anime poster from AniList for: {}",
                    title,
                    exception
            );

            return null;
        }
    }
}