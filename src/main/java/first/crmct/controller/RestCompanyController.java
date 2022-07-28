package first.crmct.controller;

/*

Запрос-контролллер для страницы Компании.
Передает в web лист с компаниями по запросу в фильтре.

 */

import first.crmct.model.dto.CompanyDTO;
import first.crmct.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/company")
@RequiredArgsConstructor
public class RestCompanyController {

    private final CompanyService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CompanyDTO> index(@RequestParam(value = "q", required = false) String query) {
        if (query == null) {
            return service.findAll();
        }
        return service.findByQuery(query);
    }

    @PutMapping
    public String update(CompanyDTO dto) {
        return null;
    }

}
