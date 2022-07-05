package first.crmct.controller;

import first.crmct.exc.CompanyNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompanyControllerAdvice {

    @ExceptionHandler(CompanyNotFoundException.class)
    public String handleCompanyNotFound(CompanyNotFoundException e, Model model) {
        model.addAttribute("id", e.getId());
        return "notFound";
    }

}
