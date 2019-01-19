package io.github.marmer.sworhm.web.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController {
    @GetMapping
    String getDefaultSite() {
        return "redirect:/days/:today/bookings";
    }
}
