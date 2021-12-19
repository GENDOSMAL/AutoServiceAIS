package auto_service.server.repository;

import auto_service.server.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByOrderTime(LocalDateTime orderTime);

    Order findStoreProductById(Long id);
}
