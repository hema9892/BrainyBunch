package com.bookcatalouge.intelj.repository;

import com.bookcatalouge.intelj.entity.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Long> {
}
