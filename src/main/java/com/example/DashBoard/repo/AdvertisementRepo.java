package com.example.DashBoard.repo;

import com.example.DashBoard.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepo extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findByNameContainingIgnoreCase(String name);

    List<Advertisement> findAllByOrderByClicksDesc();

}
