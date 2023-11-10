package com.example.yeogiduk.service;

import com.example.yeogiduk.dto.RestaurantDto;
import com.example.yeogiduk.entity.Restaurant;
import com.example.yeogiduk.repository.RestaurantRepository;
import com.example.yeogiduk.repository.RtypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RtypeRepository rtypeRepository;

    public void uploadRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = convertDtoToEntity(restaurantDto);
        restaurantRepository.save(restaurant);
    }

    public List<RestaurantDto> getRestaurantListDto() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

<<<<<<< HEAD
    // Rtype으로 검색해야하는데 어떻게 해야하는지 모르겠음
=======
    // 여기 어떻게 처리해야할지 잘 모르겠어...
>>>>>>> 0e9361502b27172d4a68dc789773ebcefb241cf3
    public List<RestaurantDto> getRestaurantListByType(Long typeId) {
        List<Restaurant> restaurantList = restaurantRepository.findByTypeTypeId(typeId);
        return restaurantList.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public RestaurantDto getRestaurantDetail(Long rstId) {
        Restaurant restaurant = restaurantRepository.findById(rstId)
                .orElse(null);

        return (restaurant != null) ? convertEntityToDto(restaurant) : null;
    }

    private Restaurant convertDtoToEntity(RestaurantDto restaurantDto) {
        return Restaurant.builder()
                .rName(restaurantDto.getRName())
<<<<<<< HEAD
                //.rtype(restaurantDto.getRtype().getTypeId())
=======
                .rtype(rtypeRepository.getById(restaurantDto.getTypeId()))
>>>>>>> 0e9361502b27172d4a68dc789773ebcefb241cf3
                .loc(restaurantDto.getLoc())
                .startTime(restaurantDto.getStartTime())
                .endTime(restaurantDto.getEndTime())
                .intro(restaurantDto.getIntro())
                .build();
    }

    private RestaurantDto convertEntityToDto(Restaurant restaurant) {
        return RestaurantDto.builder()
                .RstId(restaurant.getRstId())
                //.typeId(restaurant.getRtype().getTypeId())
                .rName(restaurant.getRName())
                .loc(restaurant.getLoc())
                .startTime(restaurant.getStartTime())
                .endTime(restaurant.getEndTime())
                .intro(restaurant.getIntro())
                .build();
    }
}
