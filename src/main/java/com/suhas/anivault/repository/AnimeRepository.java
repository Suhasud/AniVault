package com.suhas.anivault.repository;

import com.suhas.anivault.entity.Anime;
import com.suhas.anivault.enums.AnimeStatus;
import com.suhas.anivault.enums.WatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimeRepository extends
        JpaRepository<Anime, Long>,
        JpaSpecificationExecutor<Anime>{
}
