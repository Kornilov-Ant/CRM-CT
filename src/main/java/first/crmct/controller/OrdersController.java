package first.crmct.controller;

import first.crmct.exc.ObjectNotFoundException;
import first.crmct.model.dto.OrderDTO;
import first.crmct.service.OrderService;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrderService orderService;

    @GetMapping
    public String index(Model model) {
        List<OrderDTO> list = orderService.findAll();
        model.addAttribute("list", list);
        return "orders";
    }

    @GetMapping("/newOrder")
    public String newOrder(OrderDTO orderDTO) {
        return "edit-order";
    }

    @PostMapping("/newOrder")
    public String createOrder(@Valid OrderDTO dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "edit-order";
        }
        Long id = orderService.save(dto);
        return "redirect:/orders";
    }

    @GetMapping("/{id}")
    public String editOrder(@PathVariable("id") Long id, Model model) {
        OrderDTO orderDTO = orderService.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        model.addAttribute(orderDTO);
        return "edit-order";
    }

    @PostMapping("/{id}")
    public String updateOrder(@PathVariable("id") Long id, OrderDTO orderDTO) {
        orderService.update(id, orderDTO);
        return "redirect:/orders";
    }
}
