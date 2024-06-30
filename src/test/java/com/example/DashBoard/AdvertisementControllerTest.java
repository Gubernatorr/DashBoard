package com.example.DashBoard;

import com.example.DashBoard.controlles.AdvertisementController;
import com.example.DashBoard.dto.AdvertisementDto;
import com.example.DashBoard.service.AdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AdvertisementControllerTest {

    @Mock
    private AdvertisementService advertisementService;

    @InjectMocks
    private AdvertisementController advertisementController;

    private AdvertisementDto testAdvertisementDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testAdvertisementDto = new AdvertisementDto(1L, "Test Ad", 100, 10, 50.0);
    }

    @Test
    void getAllAdvertisements_ReturnsListOfAdvertisementDtos() {
        when(advertisementService.getAllAdvertisements()).thenReturn(Collections.singletonList(testAdvertisementDto));

        ResponseEntity<List<AdvertisementDto>> responseEntity = advertisementController.getAllAdvertisements();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertFalse(responseEntity.getBody().isEmpty());
        AdvertisementDto retrievedAdvertisement = responseEntity.getBody().get(0);
        assertEquals(testAdvertisementDto.getName(), retrievedAdvertisement.getName());
        assertEquals(testAdvertisementDto.getImpressions(), retrievedAdvertisement.getImpressions());
        assertEquals(testAdvertisementDto.getClicks(), retrievedAdvertisement.getClicks());
        assertEquals(testAdvertisementDto.getCost(), retrievedAdvertisement.getCost());
    }

    @Test
    void getAdvertisementById_ExistingId_ReturnsAdvertisementDto() {
        when(advertisementService.getAdvertisement(1L)).thenReturn(testAdvertisementDto);

        ResponseEntity<AdvertisementDto> responseEntity = advertisementController.getAdvertisementById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(testAdvertisementDto.getName(), responseEntity.getBody().getName());
        assertEquals(testAdvertisementDto.getImpressions(), responseEntity.getBody().getImpressions());
        assertEquals(testAdvertisementDto.getClicks(), responseEntity.getBody().getClicks());
        assertEquals(testAdvertisementDto.getCost(), responseEntity.getBody().getCost());
    }

    @Test
    void createAdvertisement_ValidAdvertisementDto_ReturnsCreatedAdvertisementDto() {
        when(advertisementService.createAdvertisement(any(AdvertisementDto.class))).thenReturn(testAdvertisementDto);

        ResponseEntity<AdvertisementDto> responseEntity = advertisementController.createAdvertisement(testAdvertisementDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(testAdvertisementDto.getName(), responseEntity.getBody().getName());
        assertEquals(testAdvertisementDto.getImpressions(), responseEntity.getBody().getImpressions());
        assertEquals(testAdvertisementDto.getClicks(), responseEntity.getBody().getClicks());
        assertEquals(testAdvertisementDto.getCost(), responseEntity.getBody().getCost());
    }

    @Test
    void updateAdvertisement_ExistingIdAndValidAdvertisementDto_ReturnsUpdatedAdvertisementDto() {
        AdvertisementDto updatedAdvertisementDto = new AdvertisementDto(1L, "Updated Test Ad", 200, 20, 100.0);
        when(advertisementService.updateAdvertisement(any(Long.class), any(AdvertisementDto.class))).thenReturn(updatedAdvertisementDto);

        ResponseEntity<AdvertisementDto> responseEntity = advertisementController.updateAdvertisement(1L, updatedAdvertisementDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedAdvertisementDto.getName(), responseEntity.getBody().getName());
        assertEquals(updatedAdvertisementDto.getImpressions(), responseEntity.getBody().getImpressions());
        assertEquals(updatedAdvertisementDto.getClicks(), responseEntity.getBody().getClicks());
        assertEquals(updatedAdvertisementDto.getCost(), responseEntity.getBody().getCost());
    }

    @Test
    void deleteAdvertisement_ExistingId_ReturnsNoContent() {
        ResponseEntity<Void> responseEntity = advertisementController.deleteAdvertisement(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
        verify(advertisementService, times(1)).deleteAdvertisement(1L);
    }
}
