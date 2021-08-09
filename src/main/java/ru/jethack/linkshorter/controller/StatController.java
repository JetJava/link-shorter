package ru.jethack.linkshorter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jethack.linkshorter.model.Link;
import ru.jethack.linkshorter.service.StatService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("stat")
public class StatController {

    final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping
    public List<Map<String, Object>> getAllLinkStat(){
        List<Link> links = statService.getAllLinkRank();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Link link: links) {
            result.add(new HashMap<>(){{
                put("link", "/l/".concat(link.getShortLink()));
                put("original", link.getOriginalLink());
                put("rank", statService.getLinkRank(link.getShortLink()));
                put("count", link.getRedirectCount());
            }});
        }
        return result;
    }

    @GetMapping("{shortLink}")
    public Map<String, Object> getLinkStat(@PathVariable String shortLink) {
        Link link = statService.getLink(shortLink);

        Map<String, Object> result = new HashMap<>(){{
            put("link", "/l/".concat(link.getShortLink()));
            put("original", link.getOriginalLink());
            put("rank", statService.getLinkRank(shortLink));
            put("count", link.getRedirectCount());
        }};

        return result;
    }
}
