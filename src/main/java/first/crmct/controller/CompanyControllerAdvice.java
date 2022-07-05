package first.crmct.controller;

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
