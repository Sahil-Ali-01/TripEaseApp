package com.R_Uber.TripEaseApp.DTO.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {
    private String name;
    private int age;
    private String email;
}
