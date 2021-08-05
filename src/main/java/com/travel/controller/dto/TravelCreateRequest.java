package com.travel.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelCreateRequest {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 15)
    private String username;
    private LocalDate travelDate;
    @Size(max = 5000)
    private String description;
    @NotBlank(message = "Country is mandatory")
    private String country;
}
