package ku.cs.restaurantapplication.controller;

import ku.cs.restaurantapplication.entity.Restaurant;
import ku.cs.restaurantapplication.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
public class RestaurantController {
    @Autowired
    private RestaurantService service;

    @GetMapping("/restaurant")
    public List<Restaurant> getAllRestaurants() {
        return service.getAll();
    }

    @PostMapping("/restaurant")
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return service.create(restaurant);
    }

    @GetMapping("/restaurant/{id}")
    public Restaurant getById(@PathVariable UUID id) {
        try {
            return service.getById(id);
        } catch (AttributeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/restaurant")
    public Restaurant update(@RequestBody Restaurant restaurant) {
        try {
            return service.update(restaurant);
        } catch (AttributeNotFoundException | IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/restaurant/{id}")
    public Restaurant delete(@PathVariable UUID id) {
        try {
            return service.delete(id);
        } catch (AttributeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/restaurant/name/{name}")
    public Restaurant getRestaurantByName(@PathVariable String name) {
        try {
            return service.getRestaurantByName(name);
        } catch (AttributeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/restaurant/location/{location}")
    public List<Restaurant> getRestaurantByLocation(@PathVariable String location) {
        try {
            return service.getRestaurantByLocation(location);
        } catch (AttributeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
