package com.suhas.anivault.service;

import com.suhas.anivault.dto.AnimeRequestDTO;
import com.suhas.anivault.dto.AnimeResponseDTO;
import com.suhas.anivault.entity.Anime;
import com.suhas.anivault.enums.AnimeStatus;
import com.suhas.anivault.enums.WatchStatus;
import com.suhas.anivault.exception.ResourceNotFoundException;
import com.suhas.anivault.mapper.AnimeMapper;
import com.suhas.anivault.repository.AnimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import com.suhas.anivault.specification.AnimeSpecification;

import java.util.List;

@Service
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final AnimeMapper animeMapper;

    public AnimeService(AnimeRepository animeRepository,
                        AnimeMapper animeMapper) {

        this.animeRepository = animeRepository;
        this.animeMapper = animeMapper;
    }

    public AnimeResponseDTO addAnime(AnimeRequestDTO requestDTO) {

        Anime anime = animeMapper.toEntity(requestDTO);

        Anime savedAnime = animeRepository.save(anime);

        return animeMapper.toResponseDTO(savedAnime);
    }
    public Page<AnimeResponseDTO> getAllAnime(
            String title,
            String studio,
            String genre,
            AnimeStatus animeStatus,
            WatchStatus watchStatus,
            Pageable pageable) {

        Specification<Anime> specification = Specification.allOf();

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

        return animeRepository.findAll(specification, pageable)
                .map(animeMapper::toResponseDTO);
    }


    public AnimeResponseDTO getAnimeById(Long id) {

        Anime anime = animeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Anime not found with id: " + id));

        return animeMapper.toResponseDTO(anime);
    }

    public AnimeResponseDTO updateAnime(Long id, AnimeRequestDTO requestDTO) {

        Anime existingAnime = animeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Anime not found with id: " + id));

        animeMapper.updateAnimeFromDTO(requestDTO, existingAnime);

        Anime updatedAnime = animeRepository.save(existingAnime);

        return animeMapper.toResponseDTO(updatedAnime);
    }

    public void deleteAnime(Long id) {

        Anime existingAnime = animeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Anime not found with id: " + id));

        animeRepository.delete(existingAnime);
    }
}