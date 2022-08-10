package org.romaine.scores.repository;

import org.romaine.scores.model.Game;
import org.romaine.scores.model.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {}