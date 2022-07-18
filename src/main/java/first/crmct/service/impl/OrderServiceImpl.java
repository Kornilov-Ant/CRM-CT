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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

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
        order.setOrders(orders);

        StatusOrder statusOrder = statusOrderRepository.findById(orderDTO.getStatusId()).get();
        order.setStatusOrder(statusOrder);

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
        order.setSum(dto.getSum());
        order.setText(dto.getText());
        order.setStatusOrder(statusOrderRepository.findById(dto.getStatusId()).get());
        order.setOrders(ordersRepository.findById(dto.getManagerId()).get());
        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> findByQuery(String query) {
        return ordersRepository.findByQuery(query).stream().map(entity -> map(entity)).map(or -> addText(or)).collect(Collectors.toList());
    }

    private OrderDTO map(Order order) {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setManagerId(order.getOrders().getManager().getId());
        return orderDTO;
    }

    private OrderDTO addText(OrderDTO dto) {
        Order order = orderRepository.findById(dto.getId()).orElseThrow();
        Manager manager = order.getOrders().getManager();
        Company company = manager.getCompany();
        dto.setCompany(company.toString());
        dto.setManager(manager.toString());
        return dto;
    }

}
