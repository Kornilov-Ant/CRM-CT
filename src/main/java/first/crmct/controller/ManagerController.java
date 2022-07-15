package first.crmct.controller;

import first.crmct.exc.ObjectNotFoundException;
import first.crmct.model.dto.ManagerDTO;
import first.crmct.model.dto.SearchIdDTO;
import first.crmct.service.CompanyService;
import first.crmct.service.ManagerService;
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
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final CompanyService companyService;

    @GetMapping
    public String index(Model model) {
        List<ManagerDTO> list = managerService.findAll();
        model.addAttribute("list", list);
        return "manager";
    }

    @GetMapping("/newManager")
    public String newManager(ManagerDTO managerDTO, Model model) {
        model.addAttribute("company", companyService.findAll());
        return "new-manager";
    }

    @PostMapping("/newManager")
    public String createManager(@Valid ManagerDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "new-manager";
        }
        Long id = managerService.save(dto);
        return "redirect:/manager/";
    }

    @GetMapping("/edit")
    public String editionId(SearchIdDTO searchIdDTO) {
        return "idManager";
    }

    @PostMapping("/edit")
    public String updateId(@Valid SearchIdDTO searchIdDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "idManager";
        }
        Long id = searchIdDTO.getId();
        return "redirect:/manager/" + id;
    }

    @GetMapping("/{id}")
    public String editManager(@PathVariable("id") Long id, Model model) {
        ManagerDTO managerDTO = managerService.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        model.addAttribute(managerDTO);
        return "edit-manager";
    }

    @PostMapping("/{id}")
    public String updateManager(@PathVariable("id") Long id, ManagerDTO managerDTO) {
        managerService.update(id, managerDTO);
        return "redirect:/manager/" + id;
    }

}
