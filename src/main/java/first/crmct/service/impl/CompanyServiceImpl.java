package first.crmct.service.impl;

import first.crmct.exc.ObjectNotFoundException;
import first.crmct.model.Company;
import first.crmct.model.Manager;
import first.crmct.model.Orders;
import first.crmct.model.dto.CompanyDTO;
import first.crmct.repository.ManagerRepository;
import first.crmct.repository.CompanyRepository;
import first.crmct.repository.OrdersRepository;
import first.crmct.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ManagerRepository managerRepository;
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<CompanyDTO> findById(Long id) {
        return companyRepository.findById(id).map(this::map);
    }

    @Override
    public Long save(CompanyDTO companyDTO) {
        Company company = new Company();
        Manager manager = new Manager();
        Orders orders = new Orders();

        company.setName(companyDTO.getCompanyName());

        manager.setFirstName(companyDTO.getManagerFirstName());
        manager.setLastName(companyDTO.getManagerLastName());
        manager.setContactNumber(companyDTO.getContactNumberManager());
        manager.setEmail(companyDTO.getEmail());
        manager = managerRepository.save(manager);

        company.setManager(manager.getId());

        orders.setManager(manager);
        orders = ordersRepository.save(orders);

        company = companyRepository.save(company);

        manager.setCompany(company);
        manager.setOrders(orders.getId());

        managerRepository.save(manager);

        return company.getId();
    }

    @Override
    public List<CompanyDTO> findAll() {
        return Streamable.of(companyRepository.findAll()).map(entity -> map(entity)).map(dto -> addText(dto)).toList();
    }

    @Override
    public void update(Long id, CompanyDTO dto) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        company.setName(dto.getCompanyName());
        companyRepository.save(company);
    }

    @Override
    public List<CompanyDTO> findByQuery(String query) {
        return companyRepository.findByQuery(query).stream().map(entity -> map(entity)).collect(Collectors.toList());
    }

    private CompanyDTO map(Company company) {
        return modelMapper.map(company, CompanyDTO.class);
    }

    private CompanyDTO addText(CompanyDTO dto) {
        Company company = companyRepository.findById(dto.getId()).orElseThrow();
        Manager manager = managerRepository.findById(company.getManager()).orElseThrow();
        dto.setManagerFirstName(manager.getFirstName());
        dto.setManagerLastName(manager.getLastName());
        dto.setContactNumberManager(manager.getContactNumber());
        return dto;
    }

}
