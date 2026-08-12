package com.suhas.anivault.repository;

import com.suhas.anivault.entity.Anime;
import com.suhas.anivault.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeRepository extends
        JpaRepository<Anime, Long>,
        JpaSpecificationExecutor<Anime> {

    Optional<Anime> findByIdAndUser(Long id, User user);
}
