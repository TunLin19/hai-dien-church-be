package com.example.hai_dien_church.entity;

import com.example.hai_dien_church.enums.PostStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String image;
    LocalDateTime datetime;
    String description;

    @JoinColumn(name = "account_id")
    @ManyToOne
    Account account;
    @Enumerated(EnumType.STRING)
    PostStatus postStatus;


}
