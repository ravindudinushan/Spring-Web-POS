package lk.ijse.gdse66.spring.repo;

import lk.ijse.gdse66.spring.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaceOrderRepo extends JpaRepository<Orders, String> {
    @Query(value = "SELECT oid FROM Orders ORDER BY oid DESC LIMIT 1", nativeQuery = true)
    String getLastIndex();

    @Query(value = "SELECT COUNT(oid) FROM Orders", nativeQuery = true)
    int getSumOrders();
}
