package com.example.hai_dien_church.dto.response;

import com.example.hai_dien_church.enums.AccountEventStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public class AccountEventResponse {

    String accountEventId;
    String fullName;
    String email;
    String phone;
    String address;
    AccountEventStatus accountEventStatus;
}
