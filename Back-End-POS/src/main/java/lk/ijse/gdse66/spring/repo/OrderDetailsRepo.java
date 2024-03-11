package lk.ijse.gdse66.spring.repo;

import lk.ijse.gdse66.spring.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, String> {
}
