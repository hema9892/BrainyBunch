package com.ULB.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class LoanDTO {

    public Long userId;
    public Long bookId;
    public LocalDate issueDate;

    public LocalDate dueDate;

    public Long lateFee;


}
