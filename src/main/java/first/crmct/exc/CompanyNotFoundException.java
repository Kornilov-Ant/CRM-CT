package first.crmct.exc;

import lombok.Value;

@Value
public class CompanyNotFoundException extends RuntimeException{

    Long id;

}
