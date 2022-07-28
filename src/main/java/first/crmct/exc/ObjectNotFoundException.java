package first.crmct.exc;

/*

Объект ошибки с запрашиваемым id объекта

 */

import lombok.Value;

@Value
public class ObjectNotFoundException extends RuntimeException {

    Long id;

}
