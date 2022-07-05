package first.crmct.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ManagerDTO {

    private Long id;
    @NotBlank
    private String firstName;

    private String lastName;
    private String email;

    @NotBlank
    private String contactNumber;

    private Long orders;

    private Long companyId;

    private String company;

}
