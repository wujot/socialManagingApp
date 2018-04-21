package rest.managing.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rest.managing.social.repository.UserRepository;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "index";
    }
}
