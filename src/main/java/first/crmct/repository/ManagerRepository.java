package first.crmct.repository;

import first.crmct.model.Manager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends CrudRepository<Manager, Long> {

    @Query("select c from Manager c where c.lastName like %:q% or c.firstName like %:q%")
    List<Manager> findByQuery(@Param("q") String query);

}
