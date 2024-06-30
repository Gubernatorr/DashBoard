package com.example.DashBoard.serviceImpl;

import com.example.DashBoard.dto.AdvertisementDto;
import com.example.DashBoard.entity.Advertisement;
import com.example.DashBoard.exceptions.ResourceNotFoundException;
import com.example.DashBoard.mapper.AdvertisementMapper;
import com.example.DashBoard.repo.AdvertisementRepo;
import com.example.DashBoard.service.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementRepo advertisementRepository;


    @Override
    public AdvertisementDto createAdvertisement(AdvertisementDto advertisementDto) {
        Advertisement advertisement = AdvertisementMapper.advertisementDtoToAdvertisement(advertisementDto);
        Advertisement savedCustomer = advertisementRepository.save(advertisement);

        return AdvertisementMapper.advertisementToAdvertisementDto(savedCustomer);
    }

    @Override
    public AdvertisementDto getAdvertisement(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("" +
                "Advertisement with id: " + id + " was not found!"));
        return AdvertisementMapper.advertisementToAdvertisementDto(advertisement);
    }

    @Override
    public List<AdvertisementDto> getAllAdvertisements() {
        List<Advertisement> advertisement = advertisementRepository.findAll();
        return advertisement.stream().map(AdvertisementMapper::advertisementToAdvertisementDto).collect(Collectors.toList());
    }

    @Override
    public AdvertisementDto updateAdvertisement(Long id, AdvertisementDto advertisementDto) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("" +
                "Advertisement with id: " + id + " was not found!"));

        advertisement.setName(advertisementDto.getName());
        advertisement.setImpressions(advertisementDto.getImpressions());
        advertisement.setClicks(advertisementDto.getClicks());
        advertisement.setCost(advertisementDto.getCost());

        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);

        return AdvertisementMapper.advertisementToAdvertisementDto(advertisement);
    }

    @Override
    public void deleteAdvertisement(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("" +
                "Advertisement with id: " + id + " was not found!"));

        advertisementRepository.deleteById(id);
    }
}
