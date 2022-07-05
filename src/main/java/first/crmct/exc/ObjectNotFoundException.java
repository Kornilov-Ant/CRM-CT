package first.crmct.exc;

import lombok.Value;

@Value
public class ObjectNotFoundException extends RuntimeException {

    Long id;

}
