package com.example.hai_dien_church.entity;

import com.example.hai_dien_church.enums.AccountEventStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne
    Event event;

    String note;

    @ManyToOne
    Account account;
    @Enumerated(EnumType.STRING)
    AccountEventStatus eventStatus;

}
