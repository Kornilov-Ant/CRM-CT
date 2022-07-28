package first.crmct.repository;

/*

Репозиторий с SQL запросом в базу, который строится на основе полученной строки из главноименной ....ServiceIml
Запрашивается ИМЯ, ФАМИЛИЯ (lastName, firstName)

 */

import first.crmct.model.Manager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends CrudRepository<Manager, Long> {

    @Query("SELECT c FROM Manager c WHERE c.lastName LIKE %:q% OR c.firstName LIKE %:q%")
    List<Manager> findByQuery(@Param("q") String query);

}
