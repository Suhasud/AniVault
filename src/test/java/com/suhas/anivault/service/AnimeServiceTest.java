package com.suhas.anivault.service;

import com.suhas.anivault.dto.AnimeRequestDTO;
import com.suhas.anivault.dto.AnimeResponseDTO;
import com.suhas.anivault.entity.Anime;
import com.suhas.anivault.exception.ResourceNotFoundException;
import com.suhas.anivault.mapper.AnimeMapper;
import com.suhas.anivault.repository.AnimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimeServiceTest {
    @Mock
    private AnimeRepository animeRepository;

    @Mock
    private AnimeMapper animeMapper;

    @InjectMocks
    private AnimeService animeService;

    @Test
    void shouldAddAnime() {

        AnimeRequestDTO requestDTO = new AnimeRequestDTO();
        requestDTO.setTitle("Naruto");

        Anime anime = new Anime();
        anime.setTitle("Naruto");

        Anime savedAnime = new Anime();
        savedAnime.setId(1L);
        savedAnime.setTitle("Naruto");

        AnimeResponseDTO responseDTO = new AnimeResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Naruto");

        when(animeMapper.toEntity(requestDTO))
                .thenReturn(anime);

        when(animeRepository.save(anime))
                .thenReturn(savedAnime);

        when(animeMapper.toResponseDTO(savedAnime))
                .thenReturn(responseDTO);

        AnimeResponseDTO result = animeService.addAnime(requestDTO);

        assertEquals(responseDTO, result);

        verify(animeMapper).toEntity(requestDTO);

        verify(animeRepository).save(anime);

        verify(animeMapper).toResponseDTO(savedAnime);
    }
    @Test
    void shouldReturnAnimeWhenIdExists() {

        Long id = 1L;

        Anime anime = new Anime();
        anime.setId(id);
        anime.setTitle("Naruto");

        AnimeResponseDTO responseDTO = new AnimeResponseDTO();
        responseDTO.setId(id);
        responseDTO.setTitle("Naruto");

        when(animeRepository.findById(id))
                .thenReturn(Optional.of(anime));

        when(animeMapper.toResponseDTO(anime))
                .thenReturn(responseDTO);

        AnimeResponseDTO result = animeService.getAnimeById(id);

        assertEquals(responseDTO, result);

        verify(animeRepository).findById(id);
        verify(animeMapper).toResponseDTO(anime);
    }
    @Test
    void shouldThrowExceptionWhenAnimeNotFound() {

        Long id = 100L;

        when(animeRepository.findById(id))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> animeService.getAnimeById(id)
        );

        assertEquals(
                "Anime not found with id: 100",
                exception.getMessage()
        );

        verify(animeRepository).findById(id);
    }
    @Test
    void shouldDeleteAnimeWhenIdExists() {

        Long id = 1L;

        Anime anime = new Anime();
        anime.setId(id);
        anime.setTitle("Naruto");

        when(animeRepository.findById(id))
                .thenReturn(Optional.of(anime));

        animeService.deleteAnime(id);

        verify(animeRepository).findById(id);
        verify(animeRepository).delete(anime);
    }
    @Test
    void shouldThrowExceptionWhenDeletingNonExistingAnime() {

        Long id = 100L;

        when(animeRepository.findById(id))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> animeService.deleteAnime(id)
        );

        assertEquals(
                "Anime not found with id: 100",
                exception.getMessage()
        );

        verify(animeRepository).findById(id);
    }
    @Test
    void shouldUpdateAnimeWhenIdExists() {

        Long id = 1L;

        AnimeRequestDTO requestDTO = new AnimeRequestDTO();
        requestDTO.setTitle("One Piece");

        Anime existingAnime = new Anime();
        existingAnime.setId(id);
        existingAnime.setTitle("Naruto");

        AnimeResponseDTO responseDTO = new AnimeResponseDTO();
        responseDTO.setId(id);
        responseDTO.setTitle("One Piece");

        when(animeRepository.findById(id))
                .thenReturn(Optional.of(existingAnime));

        doNothing().when(animeMapper)
                .updateAnimeFromDTO(requestDTO, existingAnime);

        when(animeRepository.save(existingAnime))
                .thenReturn(existingAnime);

        when(animeMapper.toResponseDTO(existingAnime))
                .thenReturn(responseDTO);

        AnimeResponseDTO result =
                animeService.updateAnime(id, requestDTO);

        assertEquals(responseDTO, result);

        verify(animeRepository).findById(id);

        verify(animeMapper)
                .updateAnimeFromDTO(requestDTO, existingAnime);

        verify(animeRepository)
                .save(existingAnime);

        verify(animeMapper)
                .toResponseDTO(existingAnime);
    }
    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingAnime() {

        Long id = 100L;

        AnimeRequestDTO requestDTO = new AnimeRequestDTO();
        requestDTO.setTitle("One Piece");

        when(animeRepository.findById(id))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> animeService.updateAnime(id, requestDTO)
        );

        assertEquals(
                "Anime not found with id: 100",
                exception.getMessage()
        );

        verify(animeRepository).findById(id);
    }
    @Test
    void shouldReturnPagedAnime() {

        Pageable pageable = PageRequest.of(0, 10);

        Anime anime = new Anime();
        anime.setId(1L);
        anime.setTitle("Naruto");

        AnimeResponseDTO responseDTO = new AnimeResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Naruto");

        Page<Anime> animePage = new PageImpl<>(List.of(anime));

        when(animeRepository.findAll(
                any(Specification.class),
                eq(pageable)))
                .thenReturn(animePage);

        when(animeMapper.toResponseDTO(anime))
                .thenReturn(responseDTO);

        Page<AnimeResponseDTO> result = animeService.getAllAnime(
                null,
                null,
                null,
                null,
                null,
                pageable
        );

        assertEquals(1, result.getTotalElements());
        assertEquals("Naruto", result.getContent().getFirst().getTitle());

        verify(animeRepository).findAll(
                any(Specification.class),
                eq(pageable)
        );
    }
}
