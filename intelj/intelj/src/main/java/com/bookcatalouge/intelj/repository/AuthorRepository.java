package com.bookcatalouge.intelj.repository;

import com.bookcatalouge.intelj.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
