package ru.jethack.linkshorter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.service.RedirectService;

@Controller
@RequestMapping("/l/")
public class RedirectController {

    final RedirectService redirectService;

    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("{shortLink}")
    public String redirect(@PathVariable String shortLink){

        Link link = redirectService.getOriginalLink(shortLink);

        return "redirect:http://".concat(link.getOriginalLink());
    }
}
