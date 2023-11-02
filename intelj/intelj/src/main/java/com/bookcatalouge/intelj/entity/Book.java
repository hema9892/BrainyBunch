package com.bookcatalouge.intelj.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(nullable = false)
private String title;

@ManyToMany(fetch = FetchType.EAGER)
@JoinColumn(name = "author_id",nullable = false)
private Author author;

@Column(nullable = false, unique = true)
private String ISBN;

@ManyToMany(fetch = FetchType.EAGER)
@JoinColumn(name = "genre_id", nullable = false)
private Genre genre;

@ManyToMany(fetch = FetchType.EAGER)
@JoinColumn(name ="publisher_id", nullable = false)
private Publisher publisher;

@Column(name = "publication_date")
private Date publicationDate;

@Column(length = 1000)
private String summary;

@Column(name ="cover_image")
private String coverImage;

private Double rating;
@Enumerated(EnumType.STRING)
private BookStatus status;



}

