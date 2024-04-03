package com.ali.persistence.repo;

import com.ali.persistence.model.PlayerDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerDataRepository extends JpaRepository<PlayerDataEntity, Integer> {
    boolean existsByUsername(String username);

    boolean existsByPassword(String password);

    PlayerDataEntity findByUsername(String username);

    PlayerDataEntity findByPassword(String password);

    PlayerDataEntity findByUsernameAndPassword(String username, String password);

    List<PlayerDataEntity> findAllByBs(boolean isPlayed);

    List<PlayerDataEntity> findAllByLol(boolean isPlayed);

    List<PlayerDataEntity> findAllByFort(boolean isPlayed);

    List<PlayerDataEntity> findAllByValo(boolean isPlayed);
}
