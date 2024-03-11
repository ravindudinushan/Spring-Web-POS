package lk.ijse.gdse66.spring.repo;

import lk.ijse.gdse66.spring.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepo extends JpaRepository<Item, String> {
    @Query(value = "SELECT code FROM Item ORDER BY code DESC LIMIT 1", nativeQuery = true)
    String getLastIndex();

    @Query(value = "SELECT COUNT(code) FROM Item", nativeQuery = true)
    int getSumItem();

}
