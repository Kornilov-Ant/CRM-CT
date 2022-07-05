package first.crmct.service;

import first.crmct.model.dto.OrderDTO;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Optional<OrderDTO> findById(Long id);
    Long save(OrderDTO orderDTO);
    List<OrderDTO> findAll();
    void update(Long id, OrderDTO dto);

    List<OrderDTO> findByQuery(String query);

}
