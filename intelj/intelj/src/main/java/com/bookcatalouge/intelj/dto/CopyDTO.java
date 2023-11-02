package com.bookcatalouge.intelj.dto;

import com.bookcatalouge.intelj.entity.Book;
import com.bookcatalouge.intelj.entity.Copy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CopyDTO {

    private Long bookId;

    private String location;

    private String status;
}
