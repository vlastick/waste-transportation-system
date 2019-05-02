package one.vladimir.impl.services.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebService {

    @GetMapping("/request.html")
    public String requestPage() {
        return "request";
    }
    @GetMapping("/index.html")
    public String AboutPage() {
        return "index";
    }
    @GetMapping("/")
    public String touristPage() {
        return "tourist_page";
    }
}