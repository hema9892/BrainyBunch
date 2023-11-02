package com.bookcatalouge.intelj.repository;

import com.bookcatalouge.intelj.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
