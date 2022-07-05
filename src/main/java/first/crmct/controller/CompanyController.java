package first.crmct.controller;


import first.crmct.exc.CompanyNotFoundException;
import first.crmct.model.dto.CompanyDTO;
import first.crmct.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public String index(Model model) {
        List<CompanyDTO> companyDTOList = companyService.findAll();
        model.addAttribute("companyDTOList", companyDTOList);
        return "company";
    }

    @GetMapping("/newCompany")
    public String newCompany(CompanyDTO companyDTO) {
        return "edit-company";
    }

    @PostMapping("/newCompany")
    public String createCompany(@Valid CompanyDTO companyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-company";
        }
        Long id = companyService.save(companyDTO);
        return "redirect:/company/" + id;
    }

    @GetMapping("/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model) {
        CompanyDTO dto = companyService.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
        model.addAttribute("companyDTO", dto);

        return "edit-company";
    }

    @PostMapping("/{id}")
    public String updateCompany(@PathVariable("id") Long id, CompanyDTO dto) {
        companyService.update(id, dto);
        return "edit-company";
    }

}
