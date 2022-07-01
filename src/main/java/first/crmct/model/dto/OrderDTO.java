package first.crmct.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderDTO {

    @NotBlank
    private Long status;

    @NotBlank
    private String text;

    @NotBlank
    private Long sum;

}
