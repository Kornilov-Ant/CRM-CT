package first.crmct.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CompanyDTO {

    @NotBlank
    private String companyName;

    @NotBlank
    private String managerFirstName;

    private String managerLastName;
    private String email;

    @NotBlank
    private String contactNumberManager;

}
