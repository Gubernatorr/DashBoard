package com.example.DashBoard;

import com.example.DashBoard.dto.AdvertisementDto;
import com.example.DashBoard.entity.Advertisement;
import com.example.DashBoard.exceptions.ResourceNotFoundException;
import com.example.DashBoard.mapper.AdvertisementMapper;
import com.example.DashBoard.repo.AdvertisementRepo;
import com.example.DashBoard.serviceImpl.AdvertisementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdvertisementServiceTest {

    @Mock
    private AdvertisementRepo advertisementRepo;

    @InjectMocks
    private AdvertisementServiceImpl advertisementService;

    private AdvertisementDto testAdvertisementDto;
    private Advertisement testAdvertisement;

    @BeforeEach
    void setUp() {
        testAdvertisementDto = new AdvertisementDto(1L, "Test Ad", 100, 10, 50.0);
        testAdvertisement = AdvertisementMapper.advertisementDtoToAdvertisement(testAdvertisementDto);
    }

    @Test
    void createAdvertisement_ValidAdvertisementDto_ReturnsCreatedAdvertisementDto() {
        when(advertisementRepo.save(any(Advertisement.class))).thenReturn(testAdvertisement);

        AdvertisementDto createdAdvertisement = advertisementService.createAdvertisement(testAdvertisementDto);

        assertNotNull(createdAdvertisement);
        assertEquals(testAdvertisementDto.getName(), createdAdvertisement.getName());
        assertEquals(testAdvertisementDto.getImpressions(), createdAdvertisement.getImpressions());
        assertEquals(testAdvertisementDto.getClicks(), createdAdvertisement.getClicks());
        assertEquals(testAdvertisementDto.getCost(), createdAdvertisement.getCost());
    }

    @Test
    void getAdvertisement_ExistingId_ReturnsAdvertisementDto() {
        when(advertisementRepo.findById(1L)).thenReturn(Optional.of(testAdvertisement));

        AdvertisementDto retrievedAdvertisement = advertisementService.getAdvertisement(1L);

        assertNotNull(retrievedAdvertisement);
        assertEquals(testAdvertisementDto.getName(), retrievedAdvertisement.getName());
        assertEquals(testAdvertisementDto.getImpressions(), retrievedAdvertisement.getImpressions());
        assertEquals(testAdvertisementDto.getClicks(), retrievedAdvertisement.getClicks());
        assertEquals(testAdvertisementDto.getCost(), retrievedAdvertisement.getCost());
    }

    @Test
    void getAllAdvertisements_NoParams_ReturnsListOfAdvertisementDtos() {
        when(advertisementRepo.findAll()).thenReturn(Collections.singletonList(testAdvertisement));

        List<AdvertisementDto> advertisementDtoList = advertisementService.getAllAdvertisements();

        assertNotNull(advertisementDtoList);
        assertFalse(advertisementDtoList.isEmpty());
        AdvertisementDto retrievedAdvertisement = advertisementDtoList.get(0);
        assertEquals(testAdvertisementDto.getName(), retrievedAdvertisement.getName());
        assertEquals(testAdvertisementDto.getImpressions(), retrievedAdvertisement.getImpressions());
        assertEquals(testAdvertisementDto.getClicks(), retrievedAdvertisement.getClicks());
        assertEquals(testAdvertisementDto.getCost(), retrievedAdvertisement.getCost());
    }

    @Test
    void updateAdvertisement_ExistingIdAndValidAdvertisementDto_ReturnsUpdatedAdvertisementDto() {
        when(advertisementRepo.findById(1L)).thenReturn(Optional.of(testAdvertisement));
        when(advertisementRepo.save(any(Advertisement.class))).thenReturn(testAdvertisement);

        AdvertisementDto updatedAdvertisementDto = new AdvertisementDto(1L, "Updated Test Ad", 200, 20, 100.0);
        AdvertisementDto updatedAdvertisement = advertisementService.updateAdvertisement(1L, updatedAdvertisementDto);

        assertNotNull(updatedAdvertisement);
        assertEquals(updatedAdvertisementDto.getName(), updatedAdvertisement.getName());
        assertEquals(updatedAdvertisementDto.getImpressions(), updatedAdvertisement.getImpressions());
        assertEquals(updatedAdvertisementDto.getClicks(), updatedAdvertisement.getClicks());
        assertEquals(updatedAdvertisementDto.getCost(), updatedAdvertisement.getCost());
    }

    @Test
    void deleteAdvertisement_ExistingId_VerifyDeletion() {
        when(advertisementRepo.findById(1L)).thenReturn(Optional.of(testAdvertisement));

        assertDoesNotThrow(() -> advertisementService.deleteAdvertisement(1L));
        verify(advertisementRepo, times(1)).deleteById(1L);
    }

    @Test
    void getAdvertisement_NonExistingId_ThrowsResourceNotFoundException() {
        when(advertisementRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> advertisementService.getAdvertisement(1L));
    }
}
