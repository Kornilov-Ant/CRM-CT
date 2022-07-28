package first.crmct.model.dto;

/*



 */

import lombok.Data;

@Data
public class StatusOrderDTO {

    private Long id;

    private String name;

    @Override
    public String toString() {
        return name;
    }
}
