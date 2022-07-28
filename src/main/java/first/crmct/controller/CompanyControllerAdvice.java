package first.crmct.controller;

/*

Контроллер для получения id объекта, который не удалось найти.
Работает с компаниями, менеджерами и заказами

 */

import first.crmct.exc.ObjectNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyControllerAdvice {

    @ExceptionHandler(ObjectNotFoundException.class)
    public String handleCompanyNotFound(ObjectNotFoundException e, Model model) {
        model.addAttribute("id", e.getId());
        return "notFound";
    }

}
