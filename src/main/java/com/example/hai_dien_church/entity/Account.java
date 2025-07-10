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
@Table(name = "account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String fullName;
    String email;
    String password;
    String phone;
    String address;

    @JoinColumn(name = "role_id")
    @ManyToOne
    Role role;

}
