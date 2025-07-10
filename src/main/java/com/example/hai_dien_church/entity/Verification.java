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
@Table(name = "verification")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Verification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String otp;
    String email;

    LocalDateTime expireTime;

}
