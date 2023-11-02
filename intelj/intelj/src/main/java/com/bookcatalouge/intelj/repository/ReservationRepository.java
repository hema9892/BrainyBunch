package com.bookcatalouge.intelj.repository;

import com.bookcatalouge.intelj.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
