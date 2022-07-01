package first.crmct.service;

import first.crmct.model.Company;
import first.crmct.model.CompanyManager;
import first.crmct.model.dto.CompanyDTO;
import first.crmct.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<CompanyDTO> findById(Long id) {
        return companyRepository.findById(id).map(this::map);
    }

    @Override
    public Long save(CompanyDTO companyDTO) {
        Company company = new Company();
        CompanyManager manager = new CompanyManager();
        company.setName(companyDTO.getCompanyName());
        manager.setFirstName(companyDTO.getManagerName());
        manager.setContactNumber(companyDTO.getContactNumberManager());

        company = companyRepository.save(company);
        return company.getId();
    }

    private CompanyDTO map(Company company){
        return modelMapper.map(company, CompanyDTO.class);
    }

}
