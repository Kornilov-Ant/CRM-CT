package first.crmct.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderDTO {

    private Long orderId;

    private String manager;

    private Long managerId;

    private String company;

    private Long companyId;

    @NotBlank
    private Long status;

    @NotBlank
    private String text;

    @NotBlank
    private Long sum;

}
