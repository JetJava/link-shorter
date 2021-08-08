package ru.jethack.linkshorter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.service.StatService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("stat")
public class StatController {

    final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping
    @RequestMapping("{shortLink}")
    public Map<String, String> getLinkStat(@PathVariable String shortLink) {
        Link link = statService.getLink(shortLink);

        Map<String, String> result = new HashMap<>(){{
            put("link", "/l/".concat(link.getShortLink()));
            put("original", link.getOriginalLink());
            put("rank", "1");
            put("count", String.valueOf(link.getRedirectCount()));
        }};

        return result;
    }
}
