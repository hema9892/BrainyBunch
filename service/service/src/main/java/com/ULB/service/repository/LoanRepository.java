package com.ULB.service.repository;

import com.ULB.service.entity.LoanRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanRecord, Long> {
    List<LoanRecord> findByUserId(Long userId);

    LoanRecord findByBookId(Long bookId);
}
