package com.suhas.anivault.controller;

import com.suhas.anivault.dto.AnimeRequestDTO;
import com.suhas.anivault.dto.AnimeResponseDTO;
import com.suhas.anivault.entity.Anime;
import com.suhas.anivault.enums.AnimeStatus;
import com.suhas.anivault.enums.WatchStatus;
import com.suhas.anivault.service.AnimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springdoc.core.annotations.ParameterObject;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/anime")
@Tag(
        name = "Anime Management API",
        description = "APIs for managing anime in the AniVault application"
)
@SecurityRequirement(name = "bearerAuth")
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new anime",
            description = "Creates a new anime in the AniVault database."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Anime created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            )
    })
    public AnimeResponseDTO addAnime(@Valid @RequestBody AnimeRequestDTO requestDTO) {
        return animeService.addAnime(requestDTO);
    }

    @GetMapping
    @Operation(
            summary = "Get all anime",
            description = "Returns a paginated list of anime with optional sorting."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Anime list retrieved successfully"
            )
    })
    public Page<AnimeResponseDTO> getAllAnime(

            @RequestParam(required = false) String title,

            @RequestParam(required = false) String studio,

            @RequestParam(required = false) AnimeStatus animeStatus,

            @RequestParam(required = false) WatchStatus watchStatus,

            @RequestParam(required = false) String genre,

            @ParameterObject
            @PageableDefault(page = 0, size = 5, sort = "id")
            Pageable pageable) {

        return animeService.getAllAnime(
                title,
                studio,
                genre,
                animeStatus,
                watchStatus,
                pageable
        );
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Get anime by ID",
            description = "Retrieves an anime using its unique ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Anime retrieved successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anime not found"
            )
    })
    public AnimeResponseDTO getAnimeById(
            @Parameter(
                    description = "Unique ID of the anime",
                    example = "5",
                    required = true
            )
            @PathVariable Long id) {
        return animeService.getAnimeById(id);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update anime",
            description = "Updates an existing anime using its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Anime updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anime not found"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed"
            )
    })

    public AnimeResponseDTO updateAnime(@PathVariable Long id,
                                        @Valid @RequestBody AnimeRequestDTO requestDTO) {

        return animeService.updateAnime(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete anime",
            description = "Deletes an anime from the database using its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Anime deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Anime not found"
            )
    })
    public void deleteAnime(@PathVariable Long id) {
        animeService.deleteAnime(id);
    }
}