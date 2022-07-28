package first.crmct.service.impl;

import first.crmct.model.Manager;
import first.crmct.model.Orders;
import first.crmct.model.dto.ManagerDTO;
import first.crmct.repository.ManagerRepository;
import first.crmct.repository.CompanyRepository;
import first.crmct.repository.OrdersRepository;
import first.crmct.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<ManagerDTO> findById(Long id) {
        return managerRepository.findById(id).map(this::map);
    }

    @Override
    public Long save(ManagerDTO managerDTO) {
        Manager manager = new Manager();
        Orders orders = new Orders();
        orders.setManager(manager);
        manager.setOrders(orders.getId());
        manager.setFirstName(managerDTO.getFirstName());
        manager.setLastName(managerDTO.getLastName());
        manager.setContactNumber(managerDTO.getContactNumber());
        manager.setEmail(managerDTO.getEmail());

        manager = managerRepository.save(manager);
        ordersRepository.save(orders);
        return manager.getId();
    }

    @Override
    public List<ManagerDTO> findAll() {
        return Streamable.of(managerRepository.findAll()).map(man -> map(man)).toList();
    }

    @Override
    public void update(Long id, ManagerDTO dto) {
        Manager manager = managerRepository.findById(id).orElseThrow();
        manager.setFirstName(dto.getFirstName());
        manager.setLastName(dto.getLastName());
        manager.setContactNumber(dto.getContactNumber());
        manager.setEmail(dto.getEmail());
        managerRepository.save(manager);
    }

    @Override
    public List<ManagerDTO> findByQuery(String query) {
        return managerRepository.findByQuery(query).stream().map(entity -> map(entity)).collect(Collectors.toList());
    }

    private ManagerDTO map(Manager manager) {
        return modelMapper.map(manager, ManagerDTO.class);
    }
}
