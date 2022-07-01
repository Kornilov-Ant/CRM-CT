package first.crmct.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CompanyManagerDTO {

    @NotBlank
    private String firstName;

    private String lastName;
    private String email;

    @NotBlank
    private String contactNumber;

}
