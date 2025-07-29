package com.example.hai_dien_church.entity;

import com.example.hai_dien_church.enums.EventStatus;
import com.example.hai_dien_church.enums.EventType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String date;
    @Enumerated(EnumType.STRING)
    EventType eventType;
    String institute;
    @Enumerated(EnumType.STRING)
    EventStatus eventStatus;
    @Lob
    String description;


}
