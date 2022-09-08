package first.crmct.controller;

/*

Контроллер для получения id объекта, который не удалось найти.
Работает с компаниями, менеджерами и заказами

 */

import first.crmct.exc.ObjectNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class CompanyControllerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    public String handleCompanyNotFound(ObjectNotFoundException e, Model model) {
        model.addAttribute("id", e.getId());
        log.info("Поиск с ошибкой по id: " + e.getId());
        return "notFound";
    }

}
