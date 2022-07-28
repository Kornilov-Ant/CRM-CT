package first.crmct.controller;

/*

Запрос-контролллер для главной страницы с канбаном.
Передает в базу, что заказ поменял свой статус..

 */

import first.crmct.model.dto.OrderDTO;
import first.crmct.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/kanban/rest")
@RequiredArgsConstructor
public class RestKanbanController {

    private final OrderService service;

    @RequestMapping("/status")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void statusNew(@RequestParam(value = "z", required = false) String query) {
        System.out.println(query);
        Long id = Long.valueOf(query.substring(0, query.indexOf("@")));
        Long statusId = Long.valueOf(query.substring(query.indexOf("@")+1, query.length()));
        OrderDTO dto = service.findById(id).orElseThrow();
        dto.setStatusId(statusId);
        service.update(id, dto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderDTO> index(@RequestParam(value = "q", required = false) String query) {
        if (query == null || query == "") {
            List<OrderDTO> list =
                    service.findAll().
                            stream().
                            filter(orderDTO -> orderDTO.getStatusId()!=9l).
                            collect(Collectors.toList());

            return list;
        }
        return service.findByQuery(query);
    }
}
