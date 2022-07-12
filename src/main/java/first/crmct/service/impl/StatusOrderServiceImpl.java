package first.crmct.service.impl;

import first.crmct.model.StatusOrder;
import first.crmct.model.dto.OrderDTO;
import first.crmct.model.dto.StatusOrderDTO;
import first.crmct.repository.StatusOrderRepository;
import first.crmct.service.StatusOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusOrderServiceImpl implements StatusOrderService {

    private final StatusOrderRepository statusOrderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StatusOrderDTO> findAll() {
        return Streamable.of(statusOrderRepository.findAll()).map(st ->map(st)).toList();
    }

    private StatusOrderDTO map(StatusOrder statusOrder) {
        return modelMapper.map(statusOrder, StatusOrderDTO.class);
    }

}
