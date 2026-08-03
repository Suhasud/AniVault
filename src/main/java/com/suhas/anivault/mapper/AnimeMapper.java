package com.suhas.anivault.mapper;

import com.suhas.anivault.dto.AnimeRequestDTO;
import com.suhas.anivault.dto.AnimeResponseDTO;
import com.suhas.anivault.entity.Anime;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimeMapper {

    Anime toEntity(AnimeRequestDTO requestDTO);

    AnimeResponseDTO toResponseDTO(Anime anime);

    List<AnimeResponseDTO> toResponseDTOList(List<Anime> animeList);

    void updateAnimeFromDTO(AnimeRequestDTO requestDTO,
                            @MappingTarget Anime anime);
}