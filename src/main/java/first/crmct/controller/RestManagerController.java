package first.crmct.controller;

/*

Запрос-контролллер для страницы Менеджеров.
Передает в web лист с менеджерами по запросу в фильтре.

 */

import first.crmct.model.dto.ManagerDTO;
import first.crmct.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/manager")
@RequiredArgsConstructor
public class RestManagerController {

    private final ManagerService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ManagerDTO> index(@RequestParam(value = "q", required = false) String query) {
        if (query == null) {
            return service.findAll();
        }
        return service.findByQuery(query);
    }

    @PutMapping
    public String update(ManagerDTO dto) {
        return null;
    }

}
