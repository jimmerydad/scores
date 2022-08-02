package org.romaine.scores.repository;

import org.romaine.scores.model.Game;
import org.romaine.scores.model.golf.Hole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoleRepository extends CrudRepository<Hole, Integer> {}