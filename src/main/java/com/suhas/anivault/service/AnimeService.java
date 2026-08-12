package com.suhas.anivault.service;

import com.suhas.anivault.dto.AnimeRequestDTO;
import com.suhas.anivault.dto.AnimeResponseDTO;
import com.suhas.anivault.entity.Anime;
import com.suhas.anivault.entity.User;
import com.suhas.anivault.enums.AnimeStatus;
import com.suhas.anivault.enums.WatchStatus;
import com.suhas.anivault.exception.ResourceNotFoundException;
import com.suhas.anivault.mapper.AnimeMapper;
import com.suhas.anivault.repository.AnimeRepository;
import com.suhas.anivault.repository.UserRepository;
import com.suhas.anivault.specification.AnimeSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AnimeService {

    private static final Logger logger =
            LoggerFactory.getLogger(AnimeService.class);

    private final AnimeRepository animeRepository;
    private final UserRepository userRepository;
    private final AnimeMapper animeMapper;
    private final AniListService aniListService;

    public AnimeService(
            AnimeRepository animeRepository,
            UserRepository userRepository,
            AnimeMapper animeMapper,
            AniListService aniListService) {

        this.animeRepository = animeRepository;
        this.userRepository = userRepository;
        this.animeMapper = animeMapper;
        this.aniListService = aniListService;
    }

    public AnimeResponseDTO addAnime(AnimeRequestDTO requestDTO) {

        User currentUser = getAuthenticatedUser();

        logger.info(
                "Adding anime with title: {} for user: {}",
                requestDTO.getTitle(),
                currentUser.getUsername()
        );

        Anime anime = animeMapper.toEntity(requestDTO);

        String imageUrl =
                aniListService.findPosterUrl(requestDTO.getTitle());

        anime.setImageUrl(imageUrl);

        // Ownership is assigned by the backend.
        anime.setUser(currentUser);

        Anime savedAnime = animeRepository.save(anime);

        logger.info(
                "Anime added successfully with id: {} for user: {}",
                savedAnime.getId(),
                currentUser.getUsername()
        );

        return animeMapper.toResponseDTO(savedAnime);
    }

    public Page<AnimeResponseDTO> getAllAnime(
            String title,
            String studio,
            String genre,
            AnimeStatus animeStatus,
            WatchStatus watchStatus,
            Pageable pageable) {

        User currentUser = getAuthenticatedUser();

        logger.info(
                "Fetching anime for user: {} with filters - title: {}, genre: {}, studio: {}, animeStatus: {}, watchStatus: {}",
                currentUser.getUsername(),
                title,
                genre,
                studio,
                animeStatus,
                watchStatus
        );

        /*
         * Ownership is always the first filter.
         *
         * This ensures that all other filters operate only
         * on the authenticated user's anime.
         */
        Specification<Anime> specification =
                AnimeSpecification.hasUser(currentUser.getId());

        if (title != null && !title.isBlank()) {
            specification = specification.and(
                    AnimeSpecification.hasTitle(title)
            );
        }

        if (studio != null && !studio.isBlank()) {
            specification = specification.and(
                    AnimeSpecification.hasStudio(studio)
            );
        }

        if (animeStatus != null) {
            specification = specification.and(
                    AnimeSpecification.hasAnimeStatus(animeStatus)
            );
        }

        if (watchStatus != null) {
            specification = specification.and(
                    AnimeSpecification.hasWatchStatus(watchStatus)
            );
        }

        if (genre != null && !genre.isBlank()) {
            specification = specification.and(
                    AnimeSpecification.hasGenre(genre)
            );
        }

        Page<AnimeResponseDTO> result = animeRepository
                .findAll(specification, pageable)
                .map(animeMapper::toResponseDTO);

        logger.info(
                "Retrieved {} anime for user: {}",
                result.getNumberOfElements(),
                currentUser.getUsername()
        );

        return result;
    }

    public AnimeResponseDTO getAnimeById(Long id) {

        User currentUser = getAuthenticatedUser();

        logger.info(
                "Fetching anime with id: {} for user: {}",
                id,
                currentUser.getUsername()
        );

        Anime anime = animeRepository
                .findByIdAndUser(id, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Anime not found with id: " + id
                        )
                );

        logger.info("Anime found: {}", anime.getTitle());

        return animeMapper.toResponseDTO(anime);
    }

    public AnimeResponseDTO updateAnime(
            Long id,
            AnimeRequestDTO requestDTO) {

        User currentUser = getAuthenticatedUser();

        logger.info(
                "Updating anime with id: {} for user: {}",
                id,
                currentUser.getUsername()
        );

        Anime existingAnime = animeRepository
                .findByIdAndUser(id, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Anime not found with id: " + id
                        )
                );

        animeMapper.updateAnimeFromDTO(
                requestDTO,
                existingAnime
        );

        Anime updatedAnime =
                animeRepository.save(existingAnime);

        logger.info(
                "Anime updated successfully with id: {} for user: {}",
                updatedAnime.getId(),
                currentUser.getUsername()
        );

        return animeMapper.toResponseDTO(updatedAnime);
    }

    public void deleteAnime(Long id) {

        User currentUser = getAuthenticatedUser();

        logger.info(
                "Deleting anime with id: {} for user: {}",
                id,
                currentUser.getUsername()
        );

        Anime existingAnime = animeRepository
                .findByIdAndUser(id, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Anime not found with id: " + id
                        )
                );

        animeRepository.delete(existingAnime);

        logger.info(
                "Anime deleted successfully with id: {} for user: {}",
                id,
                currentUser.getUsername()
        );
    }

    private User getAuthenticatedUser() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();

        return userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Authenticated user not found"
                        )
                );
    }
}