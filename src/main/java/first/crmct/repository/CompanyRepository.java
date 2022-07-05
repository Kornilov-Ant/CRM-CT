package first.crmct.repository;

import first.crmct.model.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    @Query("select c from Company c where c.name like %:q%")
    List<Company> findByQuery(@Param("q") String query);

}
