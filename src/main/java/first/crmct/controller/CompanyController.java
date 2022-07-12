package first.crmct.controller;


import first.crmct.exc.ObjectNotFoundException;
import first.crmct.model.dto.CompanyDTO;
import first.crmct.model.dto.SearchIdDTO;
import first.crmct.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Log4j2
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
        return "new-company";
    }

    @PostMapping("/newCompany")
    public String createCompany(@Valid CompanyDTO companyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-company";
        }
        Long id = companyService.save(companyDTO);
        return "redirect:/company/" + id;
    }

    @GetMapping("/edit")
    public String editionId(SearchIdDTO searchIdDTO) {
        return "idCompany";
    }

    @PostMapping("/edit")
    public String updateId(@Valid SearchIdDTO searchIdDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "idCompany";
        }
        Long id = searchIdDTO.getIdCompany();
        return "redirect:/company/" + id;
    }

    @GetMapping("/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model) {
        CompanyDTO dto = companyService.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        model.addAttribute("companyDTO", dto);
        return "edit-company";
    }

    @PostMapping("/{id}")
    public String updateCompany(@PathVariable("id") Long id, CompanyDTO dto) {
        companyService.update(id, dto);
        return "redirect:/company/";
    }

}
