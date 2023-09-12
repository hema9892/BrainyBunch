package com.ULB.service.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan_Record")
public class LoanRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

public Long userId;
public Long bookId;
public LocalDate issueDate;

public LocalDate dueDate;

public Long lateFee;


}
