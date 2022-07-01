package first.crmct.service;

import first.crmct.model.dto.CompanyDTO;

import java.util.Optional;

public interface CompanyService {

    Optional<CompanyDTO> findById(Long id);
    Long save(CompanyDTO companyDTO);

}
