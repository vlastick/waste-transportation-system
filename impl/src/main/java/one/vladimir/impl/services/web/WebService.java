package one.vladimir.impl.services.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebService {

    @GetMapping("/")
    public String mainPage() {
        return "main_page";
    }
}
