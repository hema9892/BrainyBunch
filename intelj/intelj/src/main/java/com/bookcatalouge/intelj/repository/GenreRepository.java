package com.bookcatalouge.intelj.repository;

import com.bookcatalouge.intelj.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
