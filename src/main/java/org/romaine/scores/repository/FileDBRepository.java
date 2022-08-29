package org.romaine.scores.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.romaine.scores.model.FileDB;
@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
}