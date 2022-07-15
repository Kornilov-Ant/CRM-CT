package first.crmct.repository;

import first.crmct.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Long> {

    @Query("select c from Order c where c.text like %:q%")
    List<Orders> findByQuery(@Param("q") String query);

}
