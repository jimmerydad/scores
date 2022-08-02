package org.romaine.scores.repository;

import org.romaine.scores.model.Game;
import org.romaine.scores.model.golf.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {}