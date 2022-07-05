package first.crmct.service;

import first.crmct.model.Company;
import first.crmct.model.CompanyManager;
import first.crmct.model.Orders;
import first.crmct.model.dto.CompanyDTO;
import first.crmct.repository.CompanyManagerRepository;
import first.crmct.repository.CompanyRepository;
import first.crmct.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final CompanyManagerRepository managerRepository;
    private final OrdersRepository ordersRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<CompanyDTO> findById(Long id) {
        return companyRepository.findById(id).map(this::map);
    }

    @Override
    public Long save(CompanyDTO companyDTO) {
        Company company = new Company();
        CompanyManager manager = new CompanyManager();
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

        company.setOrders(orders);
        company = companyRepository.save(company);

        manager.setCompany(company);
        manager.setBuyList(orders.getId());

        managerRepository.save(manager);

        return company.getId();
    }

    @Override
    public List<CompanyDTO> findAll() {
        return Streamable.of(companyRepository.findAll()).map(entity -> map(entity)).toList();
    }

    @Override
    public String update(Long id, CompanyDTO dto) {
        // ????
        return null;
    }

    private CompanyDTO map(Company company){
        return modelMapper.map(company, CompanyDTO.class);
    }

}
