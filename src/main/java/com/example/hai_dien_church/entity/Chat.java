package com.example.hai_dien_church.entity;

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
@Table(name = "chat")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String message;

    LocalDateTime datetime;

    @JoinColumn(name = "post_id")
    @ManyToOne
    Post post;


    @JoinColumn(name = "account_id")
    @ManyToOne
    Account account;

}
