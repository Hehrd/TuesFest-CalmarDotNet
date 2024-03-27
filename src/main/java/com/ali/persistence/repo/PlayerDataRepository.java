package com.ali.persistence.repo;

import com.ali.persistence.model.PlayerDataEntity;
import org.apache.catalina.util.Introspection;
import org.springframework.data.repository.CrudRepository;

public interface PlayerDataRepository extends CrudRepository<PlayerDataEntity, Integer> {
    boolean existsByUsername(String username);

    boolean existsByPassword(String password);

    PlayerDataEntity findByUsername(String username);

    PlayerDataEntity findByPassword(String password);
}
