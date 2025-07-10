package com.example.hai_dien_church.entity;

import com.example.hai_dien_church.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String image;
    String time;
    LocalDate date;
    @Lob
    String description;

    @Enumerated(EnumType.STRING)
    EventStatus eventStatus;

    @ManyToMany
    Set<Account> account;


}
