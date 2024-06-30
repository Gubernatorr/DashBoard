package com.example.DashBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdvertisementDto {

    private Long id;
    private String name;
    private int impressions;
    private int clicks;
    private double cost;

}
