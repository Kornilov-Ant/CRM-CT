package first.crmct.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderDTO {

    private Long id;

    private String manager;

    private Long managerId;

    private String company;

    private Long companyId;

    private StatusOrderDTO status;

    private Long statusId;

    private String text;

    private Long sum;

}
