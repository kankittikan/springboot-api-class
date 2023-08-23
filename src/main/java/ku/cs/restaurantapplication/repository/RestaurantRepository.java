package ku.cs.restaurantapplication.repository;

import ku.cs.restaurantapplication.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    Restaurant findByName(String name);
    List<Restaurant> findByLocation(String location);
}
