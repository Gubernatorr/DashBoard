package com.example.DashBoard.controlles;

import com.example.DashBoard.dto.AdvertisementDto;
import com.example.DashBoard.entity.Advertisement;
import com.example.DashBoard.repo.AdvertisementRepo;
import com.example.DashBoard.service.AdvertisementService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/advertisements")
@AllArgsConstructor
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @GetMapping("/getAll")
    public ResponseEntity<List<AdvertisementDto>> getAllAdvertisements() {
        List<AdvertisementDto> advertisementDtoList = advertisementService.getAllAdvertisements();
        return ResponseEntity.ok(advertisementDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisementById(@PathVariable Long id) {
        AdvertisementDto advertisementDto = advertisementService.getAdvertisement(id);
        return ResponseEntity.ok(advertisementDto);
    }

    @PostMapping("/add")
    public ResponseEntity<AdvertisementDto> createAdvertisement(@RequestBody AdvertisementDto advertisementDto) {
        AdvertisementDto createdAdvertisementDto = advertisementService.createAdvertisement(advertisementDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdvertisementDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@PathVariable Long id, @RequestBody AdvertisementDto updatedAdvertisementDto) {
        AdvertisementDto advertisementDto = advertisementService.updateAdvertisement(id, updatedAdvertisementDto);

        return ResponseEntity.ok(advertisementDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable Long id) {
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.noContent().build();
    }
}
