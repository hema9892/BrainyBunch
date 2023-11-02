package com.bookcatalouge.intelj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorDTO {
    private String name;
    private String biography;
    private String profileImage;
}
