package first.crmct.repository;

import first.crmct.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyManagerRepository extends CrudRepository<Company, Long> {
}
