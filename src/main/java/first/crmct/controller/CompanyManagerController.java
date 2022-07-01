package first.crmct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companyManager")
public class CompanyManagerController {

    @GetMapping
    public String index() {
        return "companyManager";
    }

}
