package com.example.hai_dien_church.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String title;
    String url;
    Boolean type; //image or video
    @Lob
    String description;


}
