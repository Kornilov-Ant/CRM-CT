package first.crmct.service;

import first.crmct.model.dto.CompanyDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Optional<CompanyDTO> findById(Long id);
    Long save(CompanyDTO companyDTO);
    List<CompanyDTO> findAll();
    String update(Long id, CompanyDTO dto);

}
