package com.example.yeogiduk.api;

import com.example.yeogiduk.dto.RestaurantDto;
import com.example.yeogiduk.entity.Restaurant;
import com.example.yeogiduk.entity.Rtype;
import com.example.yeogiduk.repository.RtypeRepository;
import com.example.yeogiduk.service.RestaurantService;
import com.example.yeogiduk.service.RtypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
//@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantApiController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RtypeRepository rtypeRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadRestaurant(@RequestBody final RestaurantDto restaurantDto) {
        restaurantService.uploadRestaurant(restaurantDto);
        return ResponseEntity.status(HttpStatus.OK).body("식당 업로드 완료");
    }

    @GetMapping("/list")
    public ResponseEntity<List<RestaurantDto>> getRestaurantList() {
        List<RestaurantDto> restaurantDtoList = restaurantService.getRestaurantListDto();
        return new ResponseEntity<>(restaurantDtoList, HttpStatus.OK);
    }

    @GetMapping("/list/{typeId}")
    public ResponseEntity<List<RestaurantDto>> getRestaurantListByType(@PathVariable Long typeId) {
        List<RestaurantDto> restaurantDtoList = restaurantService.getRestaurantListByType(typeId);
        return new ResponseEntity<>(restaurantDtoList, HttpStatus.OK);
    }

    @GetMapping("/detail/{rstId}")
    public ResponseEntity<RestaurantDto> getRestaurantDetail(@PathVariable Long rstId) {
        RestaurantDto restaurantDto = restaurantService.getRestaurantDetail(rstId);
        return (restaurantDto != null) ?
                ResponseEntity.status(HttpStatus.OK).body(restaurantDto) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/search/{word}")
    public ResponseEntity<List<RestaurantDto>> getRestaurantListBySearch(@PathVariable String word) {
        List<RestaurantDto> list = restaurantService.getRestaurantListBySearch(word);
        return (list != null) ?
                ResponseEntity.status(HttpStatus.OK).body(list) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //전체 찜 목록
    @GetMapping("/likes/list/{rstId}")
    public ResponseEntity<Integer> getLikesNumber(@PathVariable Long rstId) {
        int num = restaurantService.getLikesNumber(rstId);
        return ResponseEntity.status(HttpStatus.OK).body(num);
    }

    @GetMapping("/list/rank/star")
    public ResponseEntity<List<Restaurant>> getStarRank() {
        List<Restaurant> list = restaurantService.getRankStar();
        return (list != null) ?
                ResponseEntity.status(HttpStatus.OK).body(list) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/list/rank/like")
    public ResponseEntity<List<Restaurant>> getLikeRank() {
        List<Restaurant> list = restaurantService.getRankLike();
        return (list != null) ?
                ResponseEntity.status(HttpStatus.OK).body(list) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/list/rank/review")
    public ResponseEntity<List<Restaurant>> getReviewRank() {
        List<Restaurant> list = restaurantService.getRankReview();
        return (list != null) ?
                ResponseEntity.status(HttpStatus.OK).body(list) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/list/sort/{sort}")
    public ResponseEntity<List<Restaurant>> getSortList(@PathVariable String sort, @RequestParam(value="type_id", defaultValue="0") Long typeId){
        List<Restaurant> list = restaurantService.getSortList(sort, typeId);
        return (list != null) ?
                ResponseEntity.status(HttpStatus.OK).body(list) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

