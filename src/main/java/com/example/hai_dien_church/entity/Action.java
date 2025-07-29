package com.example.hai_dien_church.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "action")
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String image;
    @Lob
    String description;


    @JoinColumn(name = "event_id")
    @ManyToOne
    Event event;

    String location;


}
