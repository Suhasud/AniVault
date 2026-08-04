package com.suhas.anivault.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suhas.anivault.dto.AnimeRequestDTO;
import com.suhas.anivault.dto.AnimeResponseDTO;
import com.suhas.anivault.enums.AnimeStatus;
import com.suhas.anivault.enums.WatchStatus;
import com.suhas.anivault.service.AnimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimeController.class)
class AnimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AnimeService animeService;

    // Spring Boot 4.x fix
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldAddAnime() throws Exception {

        AnimeRequestDTO requestDTO = new AnimeRequestDTO();
        requestDTO.setTitle("Naruto");
        requestDTO.setGenres(Set.of("Action", "Adventure"));
        requestDTO.setStudio("Studio Pierrot");
        requestDTO.setEpisodes(220);
        requestDTO.setWatchedEpisodes(220);
        requestDTO.setAnimeStatus(AnimeStatus.COMPLETED);
        requestDTO.setWatchStatus(WatchStatus.COMPLETED);

        AnimeResponseDTO responseDTO = new AnimeResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Naruto");
        responseDTO.setGenres(Set.of("Action", "Adventure"));
        responseDTO.setStudio("Studio Pierrot");
        responseDTO.setEpisodes(220);
        responseDTO.setWatchedEpisodes(220);
        responseDTO.setAnimeStatus(AnimeStatus.COMPLETED);
        responseDTO.setWatchStatus(WatchStatus.COMPLETED);

        when(animeService.addAnime(any(AnimeRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/anime")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Naruto"));

        verify(animeService).addAnime(any(AnimeRequestDTO.class));
    }
    @Test
    void shouldGetAnimeById() throws Exception {

        AnimeResponseDTO responseDTO = new AnimeResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Naruto");
        responseDTO.setGenres(Set.of("Action", "Adventure"));
        responseDTO.setStudio("Studio Pierrot");
        responseDTO.setEpisodes(220);
        responseDTO.setWatchedEpisodes(220);
        responseDTO.setAnimeStatus(AnimeStatus.COMPLETED);
        responseDTO.setWatchStatus(WatchStatus.COMPLETED);

        when(animeService.getAnimeById(1L))
                .thenReturn(responseDTO);

        mockMvc.perform(get("/anime/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Naruto"))
                .andExpect(jsonPath("$.studio").value("Studio Pierrot"));

        verify(animeService).getAnimeById(1L);
    }
    @Test
    void shouldGetAllAnime() throws Exception {

        AnimeResponseDTO responseDTO = new AnimeResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Naruto");
        responseDTO.setGenres(Set.of("Action", "Adventure"));
        responseDTO.setStudio("Studio Pierrot");
        responseDTO.setEpisodes(220);
        responseDTO.setWatchedEpisodes(220);
        responseDTO.setAnimeStatus(AnimeStatus.COMPLETED);
        responseDTO.setWatchStatus(WatchStatus.COMPLETED);

        Page<AnimeResponseDTO> page =
                new PageImpl<>(List.of(responseDTO), PageRequest.of(0, 5), 1);

        when(animeService.getAllAnime(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(get("/anime"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].title").value("Naruto"));

        verify(animeService).getAllAnime(
                any(),
                any(),
                any(),
                any(),
                any(),
                any(Pageable.class));
    }
    @Test
    void shouldUpdateAnime() throws Exception {

        AnimeRequestDTO requestDTO = new AnimeRequestDTO();
        requestDTO.setTitle("Naruto Shippuden");
        requestDTO.setGenres(Set.of("Action", "Adventure"));
        requestDTO.setStudio("Studio Pierrot");
        requestDTO.setEpisodes(500);
        requestDTO.setWatchedEpisodes(500);
        requestDTO.setAnimeStatus(AnimeStatus.COMPLETED);
        requestDTO.setWatchStatus(WatchStatus.COMPLETED);

        AnimeResponseDTO responseDTO = new AnimeResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setTitle("Naruto Shippuden");
        responseDTO.setGenres(Set.of("Action", "Adventure"));
        responseDTO.setStudio("Studio Pierrot");
        responseDTO.setEpisodes(500);
        responseDTO.setWatchedEpisodes(500);
        responseDTO.setAnimeStatus(AnimeStatus.COMPLETED);
        responseDTO.setWatchStatus(WatchStatus.COMPLETED);

        when(animeService.updateAnime(anyLong(), any(AnimeRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(put("/anime/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Naruto Shippuden"));

        verify(animeService).updateAnime(anyLong(), any(AnimeRequestDTO.class));
    }
    @Test
    void shouldDeleteAnime() throws Exception {

        mockMvc.perform(delete("/anime/1"))
                .andExpect(status().isOk());

        verify(animeService).deleteAnime(1L);
    }
}