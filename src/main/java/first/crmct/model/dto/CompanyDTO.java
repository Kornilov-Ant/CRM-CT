package first.crmct.model.dto;

import first.crmct.model.Manager;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CompanyDTO {

    private Long id;

    @NotBlank
    private String companyName;

    @NotBlank
    private String managerFirstName;

    private String managerFullName;

    private String managerLastName;
    private String email;

    @NotBlank
    private String contactNumberManager;

    //
    private List<ManagerDTO> managerlist;

    @Override
    public String toString() {
        return companyName;
    }
}
