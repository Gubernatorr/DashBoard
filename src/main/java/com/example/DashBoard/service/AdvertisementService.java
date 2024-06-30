package com.example.DashBoard.service;

import com.example.DashBoard.dto.AdvertisementDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdvertisementService {

    AdvertisementDto createAdvertisement(AdvertisementDto customerDto);

    AdvertisementDto getAdvertisement(Long id);

    List<AdvertisementDto> getAllAdvertisements();

    AdvertisementDto updateAdvertisement(Long id, AdvertisementDto customerDto);

    void deleteAdvertisement(Long id);

}
