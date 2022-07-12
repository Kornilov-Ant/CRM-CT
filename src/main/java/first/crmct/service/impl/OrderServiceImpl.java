package first.crmct.service.impl;

import first.crmct.model.*;
import first.crmct.model.dto.OrderDTO;
import first.crmct.repository.*;
import first.crmct.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CompanyRepository companyRepository;
    private final ManagerRepository managerRepository;
    private final OrdersRepository ordersRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final StatusOrderRepository statusOrderRepository;

    @Override
    public Optional<OrderDTO> findById(Long id) {
        return orderRepository.findById(id).map(this::map);
    }

    @Override
    public Long save(OrderDTO orderDTO) {
        Order order = new Order();
        Manager manager = managerRepository.findById(orderDTO.getManagerId()).orElseThrow(); // Добавить выбор из списка!!!!
        Orders orders = ordersRepository.findById(manager.getOrders()).orElseThrow();

        order.setStatusOrder(statusOrderRepository.findById(1L).orElseThrow()); // Добавить выбор из списка!!!!

        order.setSum(orderDTO.getSum());
        order.setText(orderDTO.getText());
        List<Order> list = orders.getOrderList();
        list.add(order);
        orders.setOrderList(list);
        order = orderRepository.save(order);
        ordersRepository.save(orders);
        managerRepository.save(manager);
        return order.getId();
    }

    @Override
    public List<OrderDTO> findAll() {
        return Streamable.of(orderRepository.findAll()).map(or -> map(or)).map(or -> addText(or)).toList();
    }

    @Override
    public void update(Long id, OrderDTO dto) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setText(dto.getText());
        order.setStatusOrder(statusOrderRepository.findById(dto.getStatus().getId()).orElseThrow());
        order.setSum(dto.getSum());
        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> findByQuery(String query) {
        return null;
    }

    private OrderDTO map(Order order) {

        return modelMapper.map(order, OrderDTO.class);
    }

    private OrderDTO addText(OrderDTO dto) {
        Order order = orderRepository.findById(dto.getOrderId()).orElseThrow();
        Orders orders = order.getOrders();
        Manager manager = orders.getManager();
        Company company = manager.getCompany();
        dto.setCompany(company.toString());
        dto.setManager(manager.toString());
        return dto;
    }

}
