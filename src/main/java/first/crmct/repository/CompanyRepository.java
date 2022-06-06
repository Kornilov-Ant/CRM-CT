package first.crmct.repository;

import first.crmct.model.CompanyManager;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<CompanyManager, Long> {
}
