package com.example.DashBoard.mapper;

import com.example.DashBoard.dto.AdvertisementDto;
import com.example.DashBoard.entity.Advertisement;

public class AdvertisementMapper {

    public static Advertisement advertisementDtoToAdvertisement(AdvertisementDto advertisementDto){
        return new Advertisement(
                advertisementDto.getId(),
                advertisementDto.getName(),
                advertisementDto.getImpressions(),
                advertisementDto.getClicks(),
                advertisementDto.getCost()
        );
    }

    public static AdvertisementDto advertisementToAdvertisementDto(Advertisement advertisement){
        return new AdvertisementDto(
                advertisement.getId(),
                advertisement.getName(),
                advertisement.getImpressions(),
                advertisement.getClicks(),
                advertisement.getCost()
        );
    }

}
