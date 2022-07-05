package first.crmct.service;

import first.crmct.model.dto.ManagerDTO;

import java.util.List;
import java.util.Optional;

public interface ManagerService {

    Optional<ManagerDTO> findById(Long id);
    Long save(ManagerDTO managerDTO);
    List<ManagerDTO> findAll();
    void update(Long id, ManagerDTO dto);

    List<ManagerDTO> findByQuery(String query);

}
