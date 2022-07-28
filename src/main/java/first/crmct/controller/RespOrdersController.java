package first.crmct.controller;

/*

Запрос-контролллер для страницы Заказы.
Передает в web лист с заказами по запросу в фильтре.
Используется и в запросах с главной странице для вывода

 */

import first.crmct.model.dto.OrderDTO;
import first.crmct.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data/orders")
@RequiredArgsConstructor
public class RespOrdersController {

    private final OrderService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> index(@RequestParam(value = "q", required = false) String query) {
        if (query == null) {
            return service.findAll();
        }
        return service.findByQuery(query);
    }

    @PutMapping
    public String update(OrderDTO dto) {
        return null;
    }

}
