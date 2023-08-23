package ku.cs.restaurantapplication.service;

import ku.cs.restaurantapplication.entity.Restaurant;
import ku.cs.restaurantapplication.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository repository;

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant update(Restaurant requestBody) throws AttributeNotFoundException, IllegalArgumentException {
        UUID id = requestBody.getId();
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        Optional<Restaurant> record = repository.findById(id);
        if (record.isPresent()) {
            record.get().setName(requestBody.getName());
            record.get().setRating(requestBody.getRating());
            record.get().setLocation(requestBody.getLocation());
            return repository.save(record.get());
        } else {
            throw new AttributeNotFoundException("Id not found");
        }
    }


    public Restaurant getById(UUID id) throws AttributeNotFoundException {
        Optional<Restaurant> record = repository.findById(id);
        if (record.isPresent()) {
            return record.get();
        } else {
            throw new AttributeNotFoundException("Id not found");
        }
    }

    public Restaurant delete(UUID id) throws AttributeNotFoundException {
        Optional<Restaurant> record = repository.findById(id);
        if (record.isPresent()) {
            repository.delete(record.get());
            return record.get();
        } else {
            throw new AttributeNotFoundException("Id not found");
        }
    }

    public Restaurant getRestaurantByName(String name) throws AttributeNotFoundException {
        Restaurant record = repository.findByName(name);
        if (record == null) {
            throw new AttributeNotFoundException("No this name");
        }
        return record;
    }

    public List<Restaurant> getRestaurantByLocation(String location) throws AttributeNotFoundException {
        List<Restaurant> records = repository.findByLocation(location);
        if (records.isEmpty()) {
            throw new AttributeNotFoundException("No this location");
        }
        return records;
    }

}
