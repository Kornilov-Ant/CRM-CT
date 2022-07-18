package first.crmct.repository;

import first.crmct.model.Order;
import first.crmct.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Long> {

//    @Query("SELECT c FROM Order c
//    LEFT JOIN StatusOrder st ON Order.status=st.id
//    LEFT JOIN Orders o ON Order.status=o.id
//    LEFT JOIN Manager m ON o.manager.id=m.id
//    LEFT JOIN Company com ON m.company.id=com.id
//    WHERE c.orders.manager.firstName like %:q%")
//    List<Order> findByQuery(@Param("q") String query);

    @Query("SELECT c FROM Order c " +
            "WHERE c.orders.manager.company.name LIKE %:q% " +
            "OR c.statusOrder.name LIKE %:q% " +
            "OR c.orders.manager.firstName LIKE %:q% " +
            "OR c.orders.manager.lastName LIKE %:q%")
    List<Order> findByQuery(@Param("q") String query);

//    @Query("SELECT c FROM Order c WHERE c.text LIKE %:q%")
//    List<Order> findByQuery(@Param("q") String query);

}
